package com.zetcode.model;

import org.json.simple.JSONObject;

public class LiftJs extends JSONObject{
    public LiftJs(){}
    public LiftJs(Lift l){
        this.put("name", l.name);
        this.put("ID", l.ID);
        this.put("x", l.getX());
        this.put("y", l.getY());
    }
}
