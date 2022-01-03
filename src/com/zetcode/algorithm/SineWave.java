package com.zetcode.algorithm;

import java.awt.*;

public class SineWave {
   public int wavelength, amplitude, phase;

   public SineWave(int w, int a, int p) {
      wavelength = w;
      amplitude = a;
      phase = p;
   }

   public int getY(int x) {
      double degreesPerPixel = 360.0 / wavelength;
      double degrees = (-phase + 180) + x * degreesPerPixel;
      return (int) (amplitude * Math.sin(degrees * Math.PI / 180.0));
   }

   // This function can draw a sine wave between any two points
   public void draw(Graphics g, Point start, Point stop) {
      float angle = MyMath.getAngle(start, stop);
      float length = MyMath.length(start.x, start.y, stop.x, stop.y);
      for (int i = 1; i <= length; i++) {
         Point p1 = new Point(start.x+i, start.y + getY(i));
         Point p2 = new Point(start.x+i+1, start.y + getY(i+1));
         Point p3 = MyMath.translate(start, p1, angle);
         Point p4 = MyMath.translate(start, p2, angle);
         g.drawLine(p3.x, p3.y, p4.x, p4.y);
      }
   }
}