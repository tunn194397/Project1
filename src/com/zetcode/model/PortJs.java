package com.zetcode.model;

import org.json.simple.JSONObject;

public class PortJs extends JSONObject{
    public PortJs(){}
    public PortJs(Port p){
        this.put("name", p.name);
        this.put("ID", p.ID);
        this.put("x", p.getX());
        this.put("y", p.getY());
    }

}