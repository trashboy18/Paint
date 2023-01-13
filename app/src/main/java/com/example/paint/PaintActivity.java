package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import top.defaults.colorpicker.ColorPickerPopup;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;
    Button mPickColorButton;
    Button fillOrNot;
    Button thickness;
    Button biggest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        frame = findViewById(R.id.frm);
        paintView = new PaintView(this);
        mPickColorButton = findViewById(R.id.mPickColorButton);
        frame.addView(paintView);
        fillOrNot = findViewById(R.id.btnFill);
        thickness = findViewById(R.id.btnThickness);
        biggest = findViewById(R.id.btnBiggest);
        biggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.big();
            }
        });
        fillOrNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.toggleFill();
            }
        });
        thickness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.toggleThickness();
            }
        });
        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new ColorPickerPopup.Builder(PaintActivity.this).initialColor(
                                Color.RED) // set initial color
                                // of the color
                                // picker dialog
                                .enableBrightness(
                                        true) // enable color brightness
                                // slider or not
                                .enableAlpha(
                                        true) // enable color alpha
                                // changer on slider or
                                // not
                                .okTitle(
                                        "Choose") // this is top right
                                // Choose button
                                .cancelTitle(
                                        "Cancel") // this is top left
                                // Cancel button which
                                // closes the
                                .showIndicator(
                                        true) // this is the small box
                                // which shows the chosen
                                // color by user at the
                                // bottom of the cancel
                                // button
                                .showValue(
                                        true) // this is the value which
                                // shows the selected
                                // color hex code
                                // the above all values can be made
                                // false to disable them on the
                                // color picker dialog.
                                .build()
                                .show(v,
                                        new ColorPickerPopup.ColorPickerObserver() {
                                            @Override
                                            public void
                                            onColorPicked(int color) {
                                                paintView.setColor("#" + Integer.toHexString(color));
                                            }
                                        });

                    }


                    public void addLine(View view) {
                        paintView.addLine();
                    }
                    public void addRect(View view) {
                        paintView.addRect();
                    }
                    public void addPath(View view) {
                        paintView.addPath();
                    }
                    public void addCircle(View view) {
                        paintView.addCircle();
                    }

                    public void changeColor(View view)
                    {
                        String color = view.getTag().toString();
                        paintView.setColor(color);
                    }

                    public void clear(View view) {
                        paintView.undo();
                    }
                });}


    public void addLine(View view)
        {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }

    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }
    
    public void clear(View view) {
        paintView.undo();
    }
}
