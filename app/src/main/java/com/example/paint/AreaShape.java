package com.example.paint;

public class AreaShape extends Shape{
    double area;
    public AreaShape(int x, int y, String color) {
        super(x, y, color);

    }

    @Override
    public void updatePoint(int xe, int ye) {

    }

    public double getArea() {
        return area;
    }
}
