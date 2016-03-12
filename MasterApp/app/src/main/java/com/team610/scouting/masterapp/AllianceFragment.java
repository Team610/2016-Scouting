package com.team610.scouting.masterapp;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.team610.scouting.masterapp.team.TeamData;

public class AllianceFragment extends ScoutingFragment {


    public AllianceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alliance, container, false);
        EditText teamNum1 = (EditText) view.findViewById(R.id.alliance_team1);

        teamNum1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!v.getText().toString().equals("")) {
                        updateRow(1, v.getText().toString());
                    }
                }
                return false;
            }
        });

        EditText teamNum2 = (EditText) view.findViewById(R.id.alliance_team2);
        teamNum2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!v.getText().toString().equals("")) {
                        updateRow(2, v.getText().toString());
                    }
                }
                return false;
            }
        });

        EditText teamNum3 = (EditText) view.findViewById(R.id.alliance_team3);

        teamNum3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!v.getText().toString().equals("")) {
                        updateRow(3, v.getText().toString());
                    }
                }
                return false;
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void updateRow(int row, String team) {
        TableLayout table = (TableLayout) getActivity().findViewById(R.id.alliance_table);
        TeamData teamData = MainActivity.teams.get(team);
        TableRow r = (TableRow) table.getChildAt(row);
        ((TextView) r.getChildAt(1)).setText(teamData.getRating(Defence.PORTCULLIS) + "");
        ((TextView) r.getChildAt(2)).setText(teamData.getRating(Defence.CHEVAL_DE_FRISE) + "");
        ((TextView) r.getChildAt(3)).setText(teamData.getRating(Defence.MOAT) + "");
        ((TextView) r.getChildAt(4)).setText(teamData.getRating(Defence.RAMPARTS) + "");
        ((TextView) r.getChildAt(5)).setText(teamData.getRating(Defence.DRAWBRIDGE) + "");
        ((TextView) r.getChildAt(6)).setText(teamData.getRating(Defence.SALLY_PORT) + "");
        ((TextView) r.getChildAt(7)).setText(teamData.getRating(Defence.ROCK_WALL) + "");
        ((TextView) r.getChildAt(8)).setText(teamData.getRating(Defence.ROUGH_TERRAIN) + "");
        ((TextView) r.getChildAt(9)).setText(teamData.getRating(Defence.LOW_BAR) + "");
        try {
            updateViewsFromThe6ix();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateViewsFromThe6ix() throws NoSuchFieldException, IllegalAccessException {
        TableLayout table = (TableLayout) getActivity().findViewById(R.id.alliance_table);
        TableRow r1 = (TableRow) table.getChildAt(1);
        TableRow r2 = (TableRow) table.getChildAt(2);
        TableRow r3 = (TableRow) table.getChildAt(3);
        TableRow r4 = (TableRow) table.getChildAt(4);
        for (int i = 1; i <= 9; i++) {
            double avg = 0;
            int count = 0;
            String s = ((TextView) r1.getChildAt(i)).getText().toString();
            if (!s.equals("")) {
                avg += Double.valueOf(s);
                count++;
            }
            s = ((TextView) r2.getChildAt(i)).getText().toString();
            if (!s.equals("")) {
                avg += Double.valueOf(s);
                count++;
            }
            s = ((TextView) r3.getChildAt(i)).getText().toString();
            if (!s.equals("")) {
                avg += Double.valueOf(s);
                count++;
            }
            ((TextView) r1.getChildAt(i)).setText(avg / count + "");
        }

    }
}
