package com.zetcode.validate;

public class Validate {
    public String sizeOfMap, sizeOfRoom, sizeOfAGV, sizeOfAgent;
    public int theme;
    // Validate Room
    public int maxNumberOfRoom;
    public int maxSizeOfRoom;

    // Validate AGV
    public int maxNumberOfAgv;
    public int velocityOfAgv;

    // Validate Agent
    public int agentOutFloor;
    public int agentInFloor;
    public int agentOutRoom;
    public int numberAgentInRoom;
    public int numberAgentInFloor;
    public int velocityOfAgent;

    //Validate audio
    public int volumeOfAudio;

    public Validate() {
        sizeOfMap = " 40 : 20 ";
        sizeOfRoom = " 7 : 5 ";
        sizeOfAGV = " 1 : 0.5";
        sizeOfAgent = " 0.5 : 0.5";
        theme = 1;

        maxNumberOfRoom = 8;
        maxSizeOfRoom = 400;

        maxNumberOfAgv = 6;
        velocityOfAgv = 1;

        agentOutFloor = 10;
        agentInFloor = 20;
        agentOutRoom = 20;
        numberAgentInRoom = 5;
        numberAgentInFloor = 50;
        velocityOfAgent = 1;

        volumeOfAudio = 40;
    }
    public void setValidate(int a,int b,int c,int d,int e,int f) {
        numberAgentInRoom = a;
        maxNumberOfRoom = b;
        maxNumberOfAgv = c;
        velocityOfAgv = d;
        velocityOfAgent = e;
        volumeOfAudio = f;
    }
    public void setValidateRoom(int a, int b) {
        maxNumberOfRoom = a;
        maxSizeOfRoom = b;
    }
    public void setValidateAgent(int a, int b, int c, int d, int e, int f) {
        agentOutFloor = a;
        agentInFloor = b;
        agentOutRoom = c;
        numberAgentInRoom = d;
        numberAgentInFloor = e;
        velocityOfAgent = f;
    }
    public void setValidateAGV(int a, int b) {
        maxNumberOfAgv = a;
        velocityOfAgv = b;
    }
    public void setValidateAudio(int a) {
        volumeOfAudio = a;
    }
}
