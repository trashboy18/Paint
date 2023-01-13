package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleShape extends AreaShape {

    private int xEnd;
    private int yEnd;
    private float radius = (float) Math.sqrt((Math.pow(xEnd, 2) - Math.pow(x, 2)) + Math.pow(yEnd, 2) - Math.pow(y, 2));

    public CircleShape(int x, int y, String color) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }
    public double getArea()
    {

        return (double) (this.radius*this.radius*Math.PI);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        canvas.drawCircle( x, y, (float) Math.sqrt((Math.pow(xEnd,2)-Math.pow(x,2))+Math.pow(yEnd,2)-Math.pow(y,2)), paint);
    }
}