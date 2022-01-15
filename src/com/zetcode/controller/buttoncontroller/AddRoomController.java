package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.AGV;
import com.zetcode.model.Facility;
import com.zetcode.model.Node;
import com.zetcode.model.Room;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class AddRoomController extends ButtonController {
    public AddRoomController(Board board, JButton button) {
        super(board, button);
    }
    public int flag = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        int randomX = 5;
        int randomY = 3;
        while (checkNewLocated(new Room(randomX*30, randomY*30))) {
            randomX = (new Random()).nextInt(33);
            randomY = (new Random()).nextInt(15);
            flag++;
            if (flag >= 200) break;
        }
        if (flag < 200 ){
            Room room = new Room(randomX*30,randomY*30);
            if (board.roomArray.size() < board.MAX_ROOM_QUANTITY) {
                flag =0;
                board.roomArray.add(room);
                board.turnOnMusic1(7);
                board.updateFacilities();
            }
            else {
                board.turnOnMusic1(2);
                JOptionPane.showMessageDialog(board,"Cannot add Room because of max room in the map is " + board.MAX_ROOM_QUANTITY);
            }
        }
        else {
            board.turnOnMusic1(2);
            JOptionPane.showMessageDialog(board,"Cannot add Room because of no space available");
            flag = 0;
        }
    }
    public boolean checkNewLocated(Facility z) {
        for (Facility facility: board.facilities){
            if (facility.checkCollision(z)) return true;
        }
        for (AGV agv: board.agvArray ) {
            if (agv.checkCollision(z)) return true;
        }
        for (Node node: board.lineArray) {
            if (node.isBelongTo(z)) return true;
        }
        return false;
    }
}
