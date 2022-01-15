package com.zetcode.test.modeltest;

import com.zetcode.model.ZRectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZRectangleUnitTest {

    @Test
    void testIsHitReturnTrue() {
        ZRectangle testZR1 = new ZRectangle(10, 10, 10, 10);
        ZRectangle testZR2 = new ZRectangle(10, 10,-100, -100);
        assertTrue(testZR1.isHit(testZR2.x, testZR2.y));
    }

    @Test
    void testIsHitReturnFalse() {
        ZRectangle testZR1 = new ZRectangle(-10, 10, 10, 10);
        ZRectangle testZR2 = new ZRectangle(10, 10,-100, 100);
        assertFalse(testZR1.isHit(testZR2.x, testZR2.y));
    }

}
