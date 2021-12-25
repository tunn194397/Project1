package com.zetcode.test.modeltest;

import com.zetcode.model.Facility;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class FacilityUnitTest {
    Facility testFacility = new Facility();

    @Test
    void testSetMovable() {
        testFacility.setMovable(true);
        assertFalse(testFacility.moveAbility);
    }

    @Test
    void testUpdate() {
        testFacility.update(-1, -1);
        assertEquals(testFacility.x,testFacility.y);
    }

    @Test
    void testUpdateSize1() {
        testFacility.updateSize(-10, -10);
        assertEquals(testFacility.size_x, testFacility.size_y);
    }

    @Test
    void testCheckCollision() {
        assertFalse(testFacility.checkCollision(null));
    }

    @Test
    void testGetBound() {
        testFacility.x = 10;
        testFacility.y = 20;
        testFacility.size_x = 30;
        testFacility.size_y = 40;
        Rectangle test = new Rectangle(10, 20, 30, 40);
        assertEquals(test, testFacility.getBound());
    }

    @Test
    void testSwitchSide() {
        testFacility.size_x = 10;
        testFacility.size_y = 20;
        testFacility.switchSide();
        assertEquals(20, testFacility.size_x);
    }
}
