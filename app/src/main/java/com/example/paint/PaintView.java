package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class PaintView extends View {

    private Paint paint;
    private Paint bgPaint;
    private String currentShape = "Rect";
    private String currentColor = "#FFFFFFFF";
    private boolean currentFill = false;
    private boolean currentThickness = false;
    private LinkedList<Shape> areas = new LinkedList<Shape>();
    private Stack<Shape> shapes;

    public PaintView(Context context) {
        super(context);
        shapes = new Stack<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        bgPaint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(12);
        bgPaint.setColor(Color.rgb(255,255,255));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        for (int i = 0; i < shapes.size(); i++)
            shapes.get(i).draw(canvas, paint);
    }

    Shape shape;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor);
            }
            if(currentShape.equals("Circle"))
            {
                shape = new CircleShape((int)event.getX(),(int)event.getY(),currentColor);
            }
            if(currentShape.equals("Line"))
            {
                shape = new LineShape((int)event.getX(),(int)event.getY(),currentColor);
            }
            if(currentShape.equals("Path"))
            {
                shape = new PathShape((int)event.getX(),(int)event.getY(),currentColor);
            }
            shape.setFill(currentFill);
            shape.setThickness(currentThickness);
            shapes.push(shape);
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }

        return true;
    }
    public void big()
    {
        Shape biggest = new AreaShape(0,0,"#FFFFFFFF");
        Shape current;

        while(!shapes.empty())
        {
            current = shapes.pop();
            if(current instanceof AreaShape)
                if(((AreaShape) current).getArea()>=((AreaShape)biggest).getArea())
                    biggest = current;

        }
        shapes.push(biggest);
        invalidate();


    }

    public void addLine() {
        currentShape = "Line";
    }

    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }

    public void undo()
    {
        shapes.pop();
        invalidate();
    }
    public void toggleFill() {
        currentFill = !currentFill;
    }
    public void toggleThickness(){
        currentThickness=!currentThickness;
    }
}
