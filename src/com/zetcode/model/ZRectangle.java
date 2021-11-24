package com.zetcode.model;

import java.awt.geom.Rectangle2D;

public class ZRectangle extends Rectangle2D.Float {

    public ZRectangle(float x, float y, float width, float height) {

        setRect(x, y, width, height);
    }

    public boolean isHit(float x, float y) {
        return getBounds2D().contains(x, y);
    }

    public void addX(float x) {

        this.x += x;
    }

    public void addY(float y) {

        this.y += y;
    }

    public void addWidth(float w) {

        this.width += w;
    }

    public void addHeight(float h) {

        this.height += h;
    }
}