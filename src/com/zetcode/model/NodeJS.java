package com.zetcode.model;

import org.json.simple.JSONObject;

public class NodeJS extends JSONObject {
    public NodeJS(){};
    public NodeJS(Node node){
        this.put("name", "Node");
        this.put("x", node.x);
        this.put("y", node.y);
        this.put("updirection", node.direction.up);
        this.put("downdirection", node.direction.down);
        this.put("rightdirection", node.direction.right);
        this.put("leftdirection", node.direction.left);
        this.put("isLine", node.isLine);
    }
}
