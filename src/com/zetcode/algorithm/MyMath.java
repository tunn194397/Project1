package com.zetcode.algorithm;

import java.awt.*;

public class MyMath {
   public final static float PI = (float)Math.PI;

   public static int round(double d) {
   return Math.round(Math.round(d));
}
   public static float toRadians(float AngleInDegrees) {
   return PI * AngleInDegrees/180;
}
   public static float toDegrees(float AngleInRadians) {
   return 180 * AngleInRadians/PI;
}

   public static Point translate(Point start, Point p1, float angle) {
      int dx = p1.x - start.x;
      int dy = p1.y - start.y;
      int R = MyMath.round(Math.sqrt(dx * dx + dy * dy));

      float lambda = (float) (Math.atan((float) dy / dx));
      float theta = PI / 180f * angle;
      Point p2 = new Point(0, 0);
      angle = theta + lambda;
      if (dx < 0) angle += PI;
      p2.x = start.x + Math.round(R * (float) Math.cos(angle));
      p2.y = start.y + Math.round(R * (float) Math.sin(angle));
      return p2;
   }

   public static float getAngle(Point start, Point stop) {
      int dx = stop.x - start.x;
      int dy = stop.y - start.y;
      float slope = dy / (float) dx;
      float angle = (float) Math.atan(slope);
      if (dx < 0) angle += PI;
      angle = toDegrees(angle);
      return angle;
   }

   public static int length(int x, int y, int x1, int y1) {
      float f = (float) Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
      return Math.round(f);
   }
}