package com.zetcode.model;

import com.zetcode.controller.buttoncontroller.SaveButtonController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Map extends JSONArray {
	private static final int B_WIDTH = 1200;
	private static final int B_HEIGHT = 600;
	String nameMap;
	Node[][] nodeArray = new Node[B_WIDTH/30][B_HEIGHT/30];
	Vector<Node> node = new Vector<>();
	//Vector<Agent> AG = new Vector<>();
    public void SaveMap() {
		this.nameMap = SaveButtonController.nameMap;
        try {
			FileWriter file = new FileWriter("src/com/filemap/"+this.nameMap+".json");
			file.write(this.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public Vector<Facility> LoadFacilities(String nameFile) {
      Object obj;	
	  Vector<Facility> facilities = new Vector<>();
	  try{
	  	obj = new JSONParser().parse(new FileReader("src/com/filemap/"+nameFile));
	  	JSONArray jsonArray = (JSONArray) obj;
	  	for (int i = 0; i < jsonArray.size(); i++) {
		  	JSONObject jobj = (JSONObject)jsonArray.get(i);
			String name = (String) jobj.get("name");
			//System.out.println(name);
			if ("Port".equals(name) ){
				Port p = new Port(0,0);
				setFacility(p, jobj);
				facilities.add(p);
			}
			if ("Lift".equals(name)) {
				Lift l = new Lift(0,0);
				setFacility(l, jobj);
				facilities.add(l);
			}
			if ("Room".equals(name)) {
				Room r = new Room(0,0);
				setFacility(r, jobj);


				facilities.add(r);
			}
			if ("Node".equals(name) ){
				Node n = new Node(0, 0);
				long X =( (Long) jobj.get("x")).longValue();
				n.x = (int)X;
				long Y =( (Long) jobj.get("y")).longValue();
				n.y = (int)Y;
				long updirec =( (Long) jobj.get("updirection")).longValue();
				n.direction.up = (int)updirec;
				long downdirec =( (Long) jobj.get("downdirection")).longValue();
				n.direction.down = (int)downdirec;
				long rightdirec =( (Long) jobj.get("rightdirection")).longValue();
				n.direction.right = (int)rightdirec;
				long leftdirec =( (Long) jobj.get("leftdirection")).longValue();
				n.direction.left = (int)leftdirec;
				node.add(n); 
	 		}
			if("Agent".equals(name)){
				Agent a = new Agent(0,0);
				long X =( (Long) jobj.get("x")).longValue();
				a.x = (int)X;
				long Y =( (Long) jobj.get("y")).longValue();
				a.y = (int)Y;
				facilities.add(a);
			}
		}
		
      } catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return facilities;
	}

	public Node[][] LoadNode(String nameFile) {
				Node[] nodeArray1 = node.toArray(new Node[1200]);

				for (int h = 0; h < B_WIDTH/30; h ++) {
					for (int k = 0; k < B_HEIGHT/30 ; k ++ ) {
						nodeArray[h][k] = nodeArray1[h*20+k];
						//System.out.println(nodeArray[h][k].direction);
					}
				}
		  return nodeArray;
	}

	/*public Vector<Agent> LoadAgent(String name){
		return this.AG;
	}*/
	private void setFacility(Facility f, JSONObject jobj) {
		long xL, yL, sx, sy;
		f.name = (String) jobj.get("name");
		f.ID = (String) jobj.get("ID");
		xL =( (Long) jobj.get("x")).longValue();
		f.x = (int)xL;
		yL = ( (Long) jobj.get("y")).longValue();
		f.y = (int) yL;
		sx = ( (Long) jobj.get("size_x")).longValue();
		f.size_x = (int) sx;
		sy = ( (Long) jobj.get("size_y")).longValue();
		f.size_y = (int)sy;
	}
}


