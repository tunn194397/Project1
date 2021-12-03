package com.zetcode.model;
import java.io.FileWriter;
import java.io.IOException;

import com.zetcode.controller.buttoncontroller.SaveButtonController;
import com.zetcode.view.Board;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
      // TODO document why this method is empty
    }  
}

