package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.*;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class AddPortController extends ButtonController{
    public AddPortController(Board board, JButton button) {
        super(board, button);
    }
    public int flag = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = 0;
        int randomX = 0;
        int randomY = 6;
        while (checkNewLocated(new Port(x*30, randomY*30))) {
            randomX = (new Random()).nextInt(37);
            if (randomX < 18) {x = 0;} else x= 37;
            randomY = (new Random()).nextInt(18);
            if (randomY % 2 != 0) randomY ++;
            flag++;
            if (flag >= 200) break;
        }
        if (flag < 200 ){
            Port port = new Port(x*30,randomY*30);
            if (board.portArray.size() < board.MAX_PORT_QUANTITY) {
                flag =0;
                board.portArray.add(port);
                board.updateFacilities();
            }
            else {
                JOptionPane.showMessageDialog(board,"Cannot add Port because of max port in the map is " + board.MAX_PORT_QUANTITY);
            }
        }
        else {
            JOptionPane.showMessageDialog(board,"Cannot add Port because of no space available");
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
