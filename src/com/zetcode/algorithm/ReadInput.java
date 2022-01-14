package com.zetcode.algorithm;

import com.zetcode.model.Path;
import com.zetcode.model.Point;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadInput {
    public List<Path> paths = new ArrayList<>();

    public void readInput() throws FileNotFoundException {
        paths.clear();
        try {
            Process process = Runtime.getRuntime().exec("src/com/zetcode/algorithm/a.exe");
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

    public void drawSine(Graphics g) {
        for (Path path : paths) {
            for (int i = 0; i < path.getPoint() - 1; i++) {
                java.awt.Point point1 = new java.awt.Point(path.route[i].getX(), path.route[i].getY());
                java.awt.Point point2 = new java.awt.Point(path.route[i+1].getX(), path.route[i+1].getY());
                int w = MyMath.length(point1.x, point1.y, point2.x, point2.y);
                int a = 10;
                int p = 0;
                SineWave sineWave = new SineWave(w, a, p);
                sineWave.draw(g, point1, point2);
            }
        }
    }

    public void drawRoute(Graphics g) {
        for (Path path : paths) {
            path.drawRoute(g);
        }
    }
}