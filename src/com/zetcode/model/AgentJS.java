package com.zetcode.model;

import org.json.simple.JSONObject;

public class AgentJS extends JSONObject {
    public AgentJS(){};
    public AgentJS(Agent agent){
        this.put("name", "Agent");
        this.put("x", agent.x);
        this.put("y", agent.y);
        
    }
}
