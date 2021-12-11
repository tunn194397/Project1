package com.zetcode.model;

import com.zetcode.controller.buttoncontroller.SaveButtonController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Map extends JSONArray {
	String nameMap;
    public void SaveMap() {
		this.nameMap = SaveButtonController.nameMap;
        try {
			FileWriter file = new FileWriter("F:\\Project1\\src\\com\\filemap\\"+this.nameMap+".json");
			file.write(this.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public Facility[] LoadMap(String nameFile) {
      Object obj;	
	  Facility[] facilities = new Facility[30];
	  try{
	  	obj = new JSONParser().parse(new FileReader("F:\\Project1\\src\\com\\filemap\\"+nameFile));
	  	JSONArray jsonArray = (JSONArray) obj;
	    System.out.println(jsonArray.size());
	  	for (int i = 0; i < jsonArray.size(); i++) {
		  	JSONObject jobj = (JSONObject)jsonArray.get(i);
			System.out.println(jobj);
			String name = (String) jobj.get("name");
			
			if ("Port".equals(name) ){
				Port p = new Port(0,0);
				setFacility(p, jobj);
				facilities[i] = p;
				System.out.println(facilities[i]);
			}
			if ("Lift".equals(name)) {
				Lift l = new Lift(0,0);
				setFacility(l, jobj);
				facilities[i] = l;
			}
			if ("Room".equals(name)) {
				Room r = new Room(0,0);
				setFacility(r, jobj);
				facilities[i] = r;
				//System.out.println(r);
			}
		System.out.println(facilities[i]);	
	 	}
		
      } catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return facilities;
	}
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
	//	f.x = (int) jobj.get("x");
	//	f.y = (int) jobj.get("y");
	}
}


