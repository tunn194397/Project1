package com.zetcode.algorithm;

import com.zetcode.model.Facility;
import com.zetcode.model.Path;
import com.zetcode.model.Point;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class ReadInput {
    List<Path> paths = new ArrayList<>();

    public void readInput() throws FileNotFoundException {
        paths.clear();
        try {
            Process process = Runtime.getRuntime().exec("src/com/zetcode/algorithm/a1.exe");
            while (process.isAlive()) {} // do nothing
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileInputStream input = new FileInputStream("src/com/zetcode/algorithm/test.txt");
        Scanner scanner = new Scanner(input);

        try {
            while (scanner.hasNextDouble()) {

                double distance = scanner.nextDouble();
                int point = scanner.nextInt();
                Path path = new Path(distance, point);
                for (int i = 0; i < point; i++) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    path.route[i] = new Point(x, y);
                }
                paths.add(path);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void changeRouteZigZac(Vector<Facility> facilities){
        for (Path path : paths) {
            path.changeRouteZigZac(facilities);
        }
    }



    public void drawRoute1(Graphics g) {
        for(int i = 0; i < paths.size() ;i++) {
            paths.get(i).drawRoute1(g);
        }
    }


    public void drawRoute(Graphics g ) {
//        for (Path path : paths) {
//            path.drawRoute(g);
//        }
//        for(int i = 0; i < paths.size() ;i++) {
//            paths.get(i).drawRoute(g);
//
//        }

    }

}