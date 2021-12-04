package com.zetcode.model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import com.zetcode.controller.buttoncontroller.SaveButtonController;
import com.zetcode.view.Board;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class Map extends JSONArray {
	String nameMap;
    public void SaveMap() {
		this.nameMap = SaveButtonController.nameMap;
        try {
			FileWriter file = new FileWriter(this.nameMap+".json");
			file.write(this.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void LoadMap(String nameFile) {
      Object obj;
	  try{
	  	obj = new JSONParser().parse(new FileReader(nameMap + ".json"));
	  	JSONArray jsonArray = (JSONArray) obj;
	  	Facility[] facilities = new Facility[30];
	  	for (int i = 0; i < jsonArray.size(); i++) {
		  	JSONObject jobj = (JSONObject)jsonArray.get(i);
			String name = (String) jobj.get("name");
			if (name == "Port") {
				Port p = new Port(0,0);
				setFacility(p, jobj);
				facilities[i] = p;
			}
			if (name == "Lift") {
				Lift l = new Lift(0,0);
				setFacility(l, jobj);
				facilities[i] = l;
			}
			if (name == "Room") {
				Room r = new Room(0,0);
				setFacility(r, jobj);
				facilities[i] = r;
			}
	 	}
      } catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
}
	private void setFacility(Facility f, JSONObject jobj) {
		f.name = (String) jobj.get("name");
		f.ID = (String) jobj.get("ID");
		f.x = (int) jobj.get("x");
		f.y = (int) jobj.get("y");
		f.size_x = (int) jobj.get("size_x");
		f.size_y = (int) jobj.get("size_y");

	}
}


