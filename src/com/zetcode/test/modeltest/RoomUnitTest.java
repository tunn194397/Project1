package com.zetcode.test.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics;

import com.zetcode.model.Agent;
import com.zetcode.model.Door;
import com.zetcode.model.Room;

import org.junit.Test;

public class RoomUnitTest {
Graphics g;
public Room r; 
public Room r1 = new Room(1,1);

@Test
public void testRoom1(){
   r = new Room(-1, -1);
   assertEquals("khong the tao doi tuong room voi tham so am", null, r);
}

@Test
public void testRoom2(){
   r = new Room(-1, 1);
   assertEquals("khong the tao doi tuong room voi tham so am", null, r);
}

@Test
public void testRoom3(){
   r = new Room(1, -1);
   assertEquals("khong the tao doi tuong room voi tham so am", null, r);
}

@Test
public void testRoom4(){
   r = new Room(1, 601);
   assertEquals("khong the tao doi tuong room voi toa do y > 600", null, r);
}

@Test
public void testRoom5(){
   r = new Room(1201, 1);
   assertEquals("khong the tao doi tuong room voi toa do x > 1200", null, r);
}

@Test
public void testRoom6(){
   r = new Room(1201, 601);
   assertEquals("khong the tao doi tuong room voi với toa do nam ngoai kich thuoc cua board", null, r);
}

@Test
public void testRoom7(){
   r = new Room(100,100);
   assertEquals("Khi tham so hop le thì phai tao duoc doi tuong theo yeu cau", new Room(100,100), r);
}

@Test
public void testSetDoor1(){
    assertEquals("so cua phong trong 1 room phai bang 4", 4, r1.doorArray.length);
}

@Test
public void testSetDoor2(){
    for(Door d : r1.doorArray){
        assertTrue("Vi tri cua cac door phai nam trong room", (d.x >= r1.x) && (d.x <= r1.x - d.size_x) && (d.y >= r1.y) && (d.y <= r1.y - d.size_y));
    }
}

@Test
public void testSetDoor3(){
    boolean trunglap = false;
    for(int i = 0; i <= 2; i++){
        for(int j = i + 1; j <= 3; j++){
            if(r1.doorArray[i].x == r1.doorArray[j].x && r1.doorArray[i].y == r1.doorArray[j].y)
            trunglap = true;
            break;
        }
        if(trunglap) break;
    }
    assertTrue("hai door bat ki khong duoc trung nhau toa do (x,y)", !trunglap);
}

@Test
public void testSetDoor4(){
    boolean doorisnull = false;
    for(Door d: r1.doorArray){
        if(d == null)doorisnull =  true;
        break;
    }
    assertTrue("cac door trong doorArray khong duoc null", !doorisnull);
}

@Test
public void testAgentPosInRoom1(){
    boolean trungnhau = false;
    for(int i = 0; i < r1.agentNum; i++){
        for(int j = i + 1; j <= r1.agentNum; j++){
            if(r1.agentArray[i].x == r1.agentArray[j].x && r1.agentArray[i].y == r1.agentArray[j].y)
            trungnhau = true;
            break;
        }
        if(trungnhau) break;
    }
    assertTrue("hai agent bat ki khong duoc trung nhau toa do (x,y)", !trungnhau);
}

@Test
public void testAgentPosInRoom2(){
    boolean agentisnull = false;
    for(Door d: r1.doorArray){
        if(d == null)agentisnull =  true;
        break;
    }
    assertTrue("cac agent trong agentArray khong duoc null", !agentisnull);
}

@Test
public void testAgentPosInRoom3(){
    for(Agent a : r1.agentArray){
        assertTrue("Ban dau vi tri cua cac agent phai nam trong room", (a.x >= r1.x) && (a.x <= r1.x - a.size_x) && (a.y >= r1.y) && (a.y <= r1.y - a.size_y));
    } 
}



@Test
public void testDraw(){
    Room r2 = new Room(100,100);
    r2.agentNum = 5;
    r2.agentArray = new Agent[5];
    r2.agentPosInRoom();
    String s1 = null, s2 = null;
    for(Agent a : r2.agentArray){
        s1 += String.valueOf(a.x-r1.x) + String.valueOf(a.y-r2.y);
    }
    r2.update(500, 500);
    for(Agent a : r2.agentArray){
        s2 += String.valueOf(a.x-r2.x) + String.valueOf(a.y-r2.y);
    }
    assertEquals("Vi tri cua agent phai co dinh so voi room khi room thay doi", s1,s2);
}


}