package com.zetcode.model;

import org.json.simple.JSONObject;

public class FacilityJS extends JSONObject{
    public FacilityJS(){};
    public FacilityJS(Facility facility){
        this.put("name", facility.name);
        this.put("ID", facility.ID);
        this.put("x", facility.getX());
        this.put("y", facility.getY());
        this.put("size_x", facility.getSize_x());
        this.put("size_y", facility.getSize_y());

    }
    
}
