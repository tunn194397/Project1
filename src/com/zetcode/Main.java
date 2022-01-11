package com.zetcode;

import com.zetcode.view.Container;

import javax.swing.*;


public class Main extends JFrame {
    public Main() {
        this.setTitle("Happy Hospital");
        this.add(new Container());
        this.setSize(1500, 700);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        new Main();
    }

}
