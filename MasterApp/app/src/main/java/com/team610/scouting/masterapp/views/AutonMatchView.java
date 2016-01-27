package com.team610.scouting.masterapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.team610.scouting.masterapp.R;

/**
 * TODO: document your custom view class.
 */
    public class AutonMatchView extends LinearLayout {

    public AutonMatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.match_auton_view, this, true);
    }


}
