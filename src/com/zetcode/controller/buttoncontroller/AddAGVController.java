package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.AGV;
import com.zetcode.model.Facility;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class AddAGVController extends ButtonController{
    public AddAGVController(Board board, JButton button) {
        super(board, button);
    }
    public int flag = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        int randomX = 5;
        int randomY = 3;
        while (checkNewLocated(new AGV(randomX*30, randomY*30))) {
            randomX = (new Random()).nextInt(33);
            randomY = (new Random()).nextInt(15);
            flag++;
            if (flag >= 200) break;
        }
        if (flag < 200 ){
            AGV agv = new AGV(randomX*30,randomY*30,board.nodeArray);
            if (board.agvArray.size() < board.container.validate.maxNumberOfAgv) {
                flag =0;
                board.agvArray.add(agv);
                board.updateFacilities();
            }
            else {
                JOptionPane.showMessageDialog(board,"Cannot add AGV because of max agv in the map is " + board.container.validate.maxNumberOfAgv);
            }
        }
        else {
            JOptionPane.showMessageDialog(board,"Cannot add AGV because of no space available");
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
        return false;
    }
}
