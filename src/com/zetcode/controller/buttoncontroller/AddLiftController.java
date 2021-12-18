package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.Facility;
import com.zetcode.model.Lift;
import com.zetcode.model.Port;
import com.zetcode.model.Room;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class AddLiftController extends ButtonController {
    public AddLiftController(Board board, JButton button) {
        super(board, button);
    }
    public int flag = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        int randomX = 5;
        int randomY = 3;
        while (checkNewLocated(new Lift(randomX*30, randomY*30))) {
            randomX = (new Random()).nextInt(33);
            randomY = (new Random()).nextInt(15);
            flag++;
            if (flag >= 50) break;
        }
        if (flag < 50 ){
            Lift lift = new Lift(randomX*30,randomY*30);
                flag =0;
                board.liftArray.add(lift);
                board.updateFacilities();
        }
        else {
            JOptionPane.showMessageDialog(board,"Cannot add Room because of no space available");
            flag = 0;
        }
    }
    public boolean checkNewLocated(Facility z) {
        for (Facility facility: board.facilities){
            if (z.checkCollision(facility)) return true;
        }
        return false;
    }
}
