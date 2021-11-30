package com.zetcode.model;
import java.io.FileWriter;
import java.io.IOException;

import com.zetcode.view.Board;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Map extends JSONArray {

    public void SaveMap() {
        try {
			FileWriter file = new FileWriter("Map.json");
			file.write(this.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void LoadMap() {
      // TODO document why this method is empty
    }  
}

