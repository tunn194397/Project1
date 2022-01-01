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
            assertTrue(testFacility.moveAbility);
    }

    @Test
    void testUpdate() {
        testFacility.update(-1, -1);
        assertEquals(testFacility.x,testFacility.y);
    }

    @Test
    void testUpdateSize1() {
        testFacility.updateSize(-10, -10);
        //assertEquals(testFacility.size_x, testFacility.size_y);
        assertTrue(testFacility.size_x >= 0 && testFacility.size_y >= 0);
    }

    @Test
    void testCheckCollisionInCaseNull() {
        assertFalse(testFacility.checkCollision(null));
    }

    @Test
    void testCheckCollisionInGeneral() {
        assertFalse(testFacility.checkCollision(null));
    }

    @Test
    void testCheckCollisionWhen2FacilitiesIntersect() {
        Facility testFacility2 = new Facility();
        testFacility.x = 70;
        testFacility.y = 70;
        testFacility.size_x = 30;
        testFacility.size_y = 30;
        testFacility2.x = 85;
        testFacility2.y = 85;
        testFacility2.size_x = 30;
        testFacility2.size_y = 30;
        assertTrue(testFacility.checkCollision(testFacility2));
        /*
        Ở đây ta sẽ test trường hợp 2 facilitíes có chạm nhau. Khi đó hàm này phải gán các giá trị thuộc tính cho các
        facilities để chúng chạm nhau.
        Và lúc này ta gọi hàm assertTrue(testFacility.checkCollision(testFacility2));
         */
    }

    @Test
    void testGetBound() {
        /*
        Test cho hàm getBound() sẽ có các kịch bản:
            1. Các tọa độ phải đảm bảo tạo thành hình chữ nhật
            2. Giá trị trả về getBound là không Null trong trường hợp dữ liệu hợp lệ
            3. Giá trị trả về của getBound là null trong trường hợp không hợp lệ
         */
        testFacility.x = 10;
        testFacility.y = 20;
        testFacility.size_x = 30;
        testFacility.size_y = 40;
        Rectangle test = new Rectangle(10, 20, 30, 40);
        assertEquals(test, testFacility.getBound());
    }

    @Test
    void testGetBoundIsNotNullWithValidInput() {
        testFacility.x = 10;
        testFacility.y = 20;
        testFacility.size_x = 30;
        testFacility.size_y = 40;
        assertNotNull(testFacility.getBound());
    }

    @Test
    void testGetBoundIsNullWithNegativeCoordinate() {
        testFacility.x = 10;
        testFacility.y = -20;
        testFacility.size_x = 30;
        testFacility.size_y = 40;
        assertNull(testFacility.getBound());
    }

    @Test
    void testGetBoundIsNullWithNegativeWidth() {
        testFacility.x = 10;
        testFacility.y = 20;
        testFacility.size_x = -30;
        testFacility.size_y = 40;
        assertNull(testFacility.getBound());
    }

    @Test
    void testGetBoundIsNullWithNegativeHeight() {
        testFacility.x = 10;
        testFacility.y = 20;
        testFacility.size_x = 30;
        testFacility.size_y = -40;
        assertNull(testFacility.getBound());
    }

    @Test
    void testGetBoundIsNullWithZeroWidth() {
        testFacility.x = 10;
        testFacility.y = 20;
        testFacility.size_x = 0;
        testFacility.size_y = 40;
        assertNull(testFacility.getBound());
    }

    @Test
    void testGetBoundIsNullWithZeroHeight() {
        testFacility.x = 10;
        testFacility.y = 20;
        testFacility.size_x = 30;
        testFacility.size_y = 0;
        assertNull(testFacility.getBound());
    }

    @Test
    void testSwitchSide() {
        testFacility.size_x = 10;
        testFacility.size_y = 20;
        testFacility.switchSide();
        assertEquals(20, testFacility.size_x);
    }
}
