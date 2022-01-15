package com.zetcode.test.modeltest;

import com.zetcode.model.Agent;
import com.zetcode.model.Door;
import com.zetcode.model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomUnitTest {
    Room room = new Room(150, 360,5);

    @Test
    void testSetDoorReturnNull() {
        assertNull(room.doorArray);
    }

    @Test
    void testSetDoorNotNull() {
        assertNotNull(room.doorArray);
    }

    @Test
    void testAgentPosInRoomReturnNull() {
        assertNull(room.agentArray);
    }

    @Test
    void testAgentPosInRoomNotNull() {
        assertNotNull(room.agentArray);
    }

    @Test
    void testSetDoor() {
        Door d1 = new Door(175, 80);
        Door d2 = new Door(280, 80);
        Door d3 = new Door(175, 230);
        Door d4 = new Door(280, 230);
         Door[] doorArray = new Door[] {d1, d2, d3, d4};
        assertEquals(doorArray, room.doorArray);
    }

    @Test
    void testAgentNum() {
        assertNotNull(room.agentArray.size());
    }

    @Test
    void testAgentArray() {
        Agent a1 = new Agent(160, 100);
        Agent a2 = new Agent(-100, -100);
        Agent[] agentArray = new Agent[]{a1, a2};
        assertNotEquals(agentArray, room.agentArray);
    }
}
