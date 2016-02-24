package com.team610.scouting.masterapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


/**
 * Created by vkobay on 2/6/2016.
 */
public class DrawView extends View {
    public static Paint brushBlue = new Paint();
    public static Paint brushGreen = new Paint();
    public static Paint brushBlack = new Paint();
    public static Paint brushRed = new Paint();

    public static Path pathBlue = new Path();
    public static Path pathGreen = new Path();
    public static Path pathBlack = new Path();
    public static Path pathRed = new Path();

    public static int color = 0;

    public RelativeLayout.LayoutParams params;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);


        brushBlue.setAntiAlias(true);
        brushBlue.setColor(Color.BLUE);
        brushBlue.setStyle(Paint.Style.STROKE);
        brushBlue.setStrokeJoin(Paint.Join.ROUND);
        brushBlue.setStrokeWidth(5f);

        brushGreen.setAntiAlias(true);
        brushGreen.setColor(Color.GREEN);
        brushGreen.setStyle(Paint.Style.STROKE);
        brushGreen.setStrokeJoin(Paint.Join.ROUND);
        brushGreen.setStrokeWidth(5f);

        brushBlack.setAntiAlias(true);
        brushBlack.setColor(Color.BLACK);
        brushBlack.setStyle(Paint.Style.STROKE);
        brushBlack.setStrokeJoin(Paint.Join.ROUND);
        brushBlack.setStrokeWidth(5f);

        brushRed.setAntiAlias(true);
        brushRed.setColor(Color.RED);
        brushRed.setStyle(Paint.Style.STROKE);
        brushRed.setStrokeJoin(Paint.Join.ROUND);
        brushRed.setStrokeWidth(5f);

        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (color == 0)
                    pathBlue.moveTo(pointX, pointY);
                else if (color == 1)
                    pathGreen.moveTo(pointX, pointY);
                else if (color == 2)
                    pathBlack.moveTo(pointX, pointY);
                else if (color == 3)
                    pathRed.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                if (color == 0)
                    pathBlue.lineTo(pointX, pointY);
                else if (color == 1)
                    pathGreen.lineTo(pointX, pointY);
                else if (color == 2)
                    pathBlack.lineTo(pointX, pointY);
                else if (color == 3)
                    pathRed.lineTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        // Force a view to draw.
        postInvalidate();
        return false;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(pathBlue, brushBlue);
        canvas.drawPath(pathGreen, brushGreen);
        canvas.drawPath(pathBlack, brushBlack);
        canvas.drawPath(pathRed, brushRed);
        postInvalidate();
    }

    public static void clear() {
        pathBlue.reset();
        pathGreen.reset();
        pathBlack.reset();
        pathRed.reset();
    }

    public static void setBlue() {
        color = 0;
    }

    public static void setGreen() {
        color = 1;
    }

    public static void setBlack() {
        color = 2;
    }

    public static void setRed() {
        color = 3;
    }


}
