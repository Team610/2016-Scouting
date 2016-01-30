package com.team610.scouting.masterapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.Calendar;


public class TeamDialog extends DialogFragment {
int teamNum;
//    Dialog d;
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        // Get the layout inflater
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//
//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        builder.setView(inflater.inflate(R.layout.team_dialog, null))
//                // Add action buttons
//                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        TeamDialog.this.getDialog().cancel();
//                    }
//                });
//        d = builder.create();
//      //  ((TextView) d.findViewById( R.id.team_dialog_team)) .setText(MatchFragment.teamNum);
//        return d;
//    }

    static TeamDialog newInstance(int num) {
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
