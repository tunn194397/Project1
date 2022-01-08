package com.zetcode.test.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zetcode.model.Direction;

import org.junit.Test;

public class DirectionUnitTest {
public Direction d;

@Test
public void testDirection(){
    d = new Direction(1,1,0,0);
    assertEquals("Mot Node khong duoc co hai huong nguoc chieu nhau", null, d);
}

@Test
public void testDirection1(){
    d = new Direction(0,0,1,1);
    assertEquals("Mot Node khong duoc co hai huong nguoc chieu nhau", null, d);
}

@Test
public void testDirection2(){
    d = new Direction(1,1,1,0);
    assertEquals("Mot Node khong duoc co hai huong nguoc chieu nhau", null, d);
}

@Test
public void testDirection3(){
    d = new Direction(1,1,0,1);
    assertEquals("Mot Node khong duoc co hai huong nguoc chieu nhau", null, d);
}

@Test
public void testDirection4(){
    d = new Direction(1,1,1,1);
    assertEquals("Mot Node khong duoc co hai huong nguoc chieu nhau", null, d);
}

@Test
public void testDirection5(){
    d = new Direction(0,1,1,1);
    assertEquals("Mot Node khong duoc co hai huong nguoc chieu nhau", null, d);
}

@Test
public void testDirection6(){
    d = new Direction(1,0,1,1);
    assertEquals("Mot Node khong duoc co hai huong nguoc chieu nhau", null, d);
}


}
