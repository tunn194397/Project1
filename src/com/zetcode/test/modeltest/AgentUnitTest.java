package com.zetcode.test.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.zetcode.model.Agent;

import java.awt.Color;
import java.awt.Graphics;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class AgentUnitTest {
public Graphics g;
public Agent a;

@Test
public void testAgent1(){
   a = new Agent(-1, -1);
   assertEquals("khong the tao doi tuong agent voi tham so am", null, a);
}

@Test
public void testAgent2(){
   a = new Agent(-1, 1);
   assertEquals("khong the tao doi tuong room voi tham so am", null, a);
}

@Test
public void testAgent3(){
   a = new Agent(1, -1);
   assertEquals("khong the tao doi tuong room voi tham so am", null, a);
}


@Test
public void testDrawAgent1(){
    Agent a = new Agent(100,100);
    Graphics g = Mockito.mock(Graphics.class);
    Color expectedColor = Color.cyan;
    a.drawAgent(g, a.getX(), a.getY(), 20);
    Mockito.verify(g).setColor(expectedColor);
}


@Test
public void testDrawAgent0(){
    Agent a = new Agent(100,100);
    Graphics g = a.getGraphics();
    a.drawAgent(g, a.getX(), a.getY(), 20);
    Color c = a.getBackground();
    assertEquals("Mau cua Agent sau khi draw phai la cyan",c,Color.cyan);
} 

@Test
public void testDrawAgent2(){
    Agent a = new Agent(100,100);
    Graphics g = null;
    a.drawAgent(g, a.getX(), a.getY(), 20);
    Color c = a.getBackground();
    assertEquals("Khi doi tuong Graphics null thi khong ve duoc",c,null);
}

}
