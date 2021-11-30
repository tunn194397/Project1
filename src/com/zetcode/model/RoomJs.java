package com.zetcode.model;

import org.json.simple.JSONObject;

public class RoomJs extends JSONObject{
    public RoomJs(){}
    public RoomJs(Room r){
        this.put("name", r.name);
        this.put("ID", r.ID);
        this.put("x", r.getX());
        this.put("y", r.getY());
        this.put("size_x", r.getSize_x());
        this.put("size_y", r.getSize_y());

    }
}