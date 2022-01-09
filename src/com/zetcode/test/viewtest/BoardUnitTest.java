package com.zetcode.test.viewtest;

import com.zetcode.model.Facility;
import com.zetcode.model.Room;
import com.zetcode.view.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardUnitTest {
    Board testBoard = new Board();

    @Test
    void testCheckRoomOverTotalSizeWithNegativeInput() {
        Room room = new Room(10, 10);
        testBoard.roomArray.add(room);
        room.size_x = -100;
        room.size_y = -100;
        assertTrue(testBoard.checkRoomOverTotalSize());
    }
/*
    @Test
    void testSetUpFacilities() {
        testBoard.roomArray = null;
        testBoard.liftArray = null;
        testBoard.portArray = null;
        testBoard.setUpFacilities();
        assertNull(testBoard.facilities);
    }


    @Test
    void testDoRoundWithZero() {
        Facility collector = new Facility();
        collector.x = 0;
        collector.y = 0;
        testBoard.doRound();
    }
     */

}
