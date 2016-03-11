package com.team610.scouting.masterapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.team610.scouting.masterapp.R;

/**
 * Created by jackw on 2016-03-11.
 */
public class CommentView extends LinearLayout {

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_comment_view, this, true);
    }

}
