package com.zetcode.algorithm;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.zetcode.model.Path;
import com.zetcode.model.Point;

public class ReadInput {
    List<Path> paths = new ArrayList<>();

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

    public void drawRoute(Graphics g) {
        for (Path path : paths) {
            path.drawRoute(g);
        }
    }
}