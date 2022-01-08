package com.zetcode.model;
import org.junit.Test;
import com.zetcode.configuration.Config;
import com.zetcode.controller.buttoncontroller.PlayButtonController;

import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
public class Room extends Facility {
    public Door[] doorArray = new Door[4];
    public Agent[] agentArray = new Agent[Config.getAgentRoom()];
    public int agentNum = ThreadLocalRandom.current().nextInt(0, Config.getAgentRoom() + 1);
    public Room(int i, int j) {
        super(i, j);
        size_x = 210;
        size_y = 150;
        maxsize = 100;
        imagePath = "src/resources/images/";
        moveAbility = false;
        name = "Room";
        ID = "Room" + Integer.toString(i) + Integer.toString(j);
        color = Color.yellow;
        setDoor();
        agentPosInRoom();
    }
    public void setDoor() {
        Door d1 = new Door(this.x + 25, this.y );
        Door d2 = new Door(this.x + size_x -55 , this.y );
        Door d3 = new Door(this.x + 25, this.y + this.size_y - 10);
        Door d4 = new Door(this.x + size_x -55 , this.y+ this.size_y - 10);
        this.doorArray = new Door[]{d1,d2,d3,d4};
    }
    public void agentPosInRoom() {
        for (int i = 0; i < agentNum; i++) {
            int a_pos_x = ThreadLocalRandom.current().nextInt( this.x + 10, this.x + 200 + 1);
            int a_pos_y = ThreadLocalRandom.current().nextInt(this.y +10, this.y + 140 + 1);
            this.agentArray[i] = new Agent(a_pos_x, a_pos_y);
        }
    }
    public void draw(Graphics g) {
        super.draw(g);
        setDoor();
        for (int i = 0; i < doorArray.length; i ++) {
            doorArray[i].draw(g);
        }
        if (PlayButtonController.isGameStart()) {
            for (int i = 0; i < agentNum; i++) {
                agentArray[i].drawAgent(g, agentArray[i].x, agentArray[i].y, 20);
            }
        }
    }
}    
/*Room r;
@Test
public void agentPosInRoomTest(){ 
    
try {
     r = new Room(-1, -1);

} catch (Exception e) {
    System.out.println("Voi tham so -1, -1 thi khong khoi tao duoc doi tuong");
    assertEquals(null, r );
}*/
/*
Cac kich ban test cho phuong thuc khoi dung co the co la:
1. Tham so am
2. Tham so nam ngoai kich thuoc cua board
3. Tham so = MAX_INT

Các kịch bản test dành cho phương thức setDoor:
1. So luong phan tu cua doorArray = 4
2. Vi tri cac phan tu cua doorArray phai nam trong phong
3. 2 door bat ki khong duoc trung nhau ca ve toa do x va y
4. Trong tat ca phan tu cua mang doorArray khong co phan tu nao bi null

Cac kich ban test danh cho phuong thuc agentPosInRoom:
1. Toa do cua 2 agent bat ki khong duoc trung nhau
2. Ban dau vi tri cac agent phai o trong room
Cac kich ban cho phuong thuc draw
1. Khi mà ham draw duoc goi xong thi vi tri cua agent phai co dinh so voi room
*/ 





