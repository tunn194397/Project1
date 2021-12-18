package com.zetcode.model;

import org.json.JSONObject;

public class NodeJS extends JSONObject {
    public NodeJS(){};
    public NodeJS(Node node){
        this.put("name", "Node");
        this.put("x", node.x);
        this.put("y", node.y);
        this.put("direction", node.direction);
    }
}
