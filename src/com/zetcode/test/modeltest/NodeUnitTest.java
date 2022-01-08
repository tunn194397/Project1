package com.zetcode.test.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.zetcode.model.Direction;
import com.zetcode.model.Node;

import org.junit.Test;

public class NodeUnitTest {
   public Node n;

@Test
public void testNode1(){
   n = new Node(-1, -1);
   assertEquals("khong the tao doi tuong Node voi tham so am", null, n);
}

@Test
public void testNode2(){
   n = new Node(-1, 1);
   assertEquals("khong the tao doi tuong Node voi tham so am", null, n);
}

@Test
public void testNode3(){
   n = new Node(1, -1);
   assertEquals("khong the tao doi tuong Node voi tham so am", null, n);
}

@Test
public void testNode4(){
   n = new Node(1, 650);
   assertEquals("khong the tao doi tuong Node voi toa do y > 600", null, n);
}

@Test
public void testNode5(){
   n = new Node(1201, 1);
   assertEquals("khong the tao doi tuong Node voi toa do x > 1200", null, n);
}

@Test
public void testNode6(){
   n = new Node(1201, 601);
   assertEquals("khong the tao doi tuong Node voi với toa do nam ngoai kich thuoc cua board", null, n);  
}    

@Test
public void testUpdateNode(){
    n = new Node(1,1);
    n.updateNode(100,100);
    assertEquals("Update khong thanh cong", new Node(100,100),n);
}

@Test
public void testDirection(){
    n = new Node(1,1);
    n.updateDirection("up");
    n.updateDirection("down");
    n.updateDirection("left");
    n.updateDirection("right");
    assertEquals("Update direction khong thanh cong", new Direction(1,1,1,1), n.direction);
}

@Test
public void testUpdateIsLine(){
    n = new Node(1,1);
    n.isLine = false;
    assertFalse("Update IsLine khong thanh cong", n.isLine);
}
}
