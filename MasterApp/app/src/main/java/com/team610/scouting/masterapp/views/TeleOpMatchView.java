package com.team610.scouting.masterapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.team610.scouting.masterapp.R;

/**
 * Created by Tate on 2016-01-27.
 */
public class TeleOpMatchView extends LinearLayout {

    public TeleOpMatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.match_teleop_view, this, true);
    }
}
