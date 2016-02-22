package com.team610.scouting.masterapp.team;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team610.scouting.masterapp.R;


public class TeamDialog extends DialogFragment {
int teamNum;

    public static TeamDialog newInstance(int num) {
        TeamDialog f = new TeamDialog();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamNum = getArguments().getInt("num");
        setStyle(STYLE_NO_TITLE,0);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.team_dialog, null);
        View tv = v.findViewById(R.id.team_dialog_team);
        ((TextView)tv).setText(""+teamNum);
        return v;
    }

}
