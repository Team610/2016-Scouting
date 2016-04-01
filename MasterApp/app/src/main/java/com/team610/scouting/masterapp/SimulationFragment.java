package com.team610.scouting.masterapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.team610.scouting.masterapp.team.TeamData;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimulationFragment extends ScoutingFragment {


    public SimulationFragment() {
        // Required empty public constructor
    }

    TeamData[] red = new TeamData[3], blue = new TeamData[3];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_simulation, container, false);
//        EditText teamNum = (EditText) root.findViewById(R.id.sim_team1);
//
//        teamNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
//                    if (!v.getText().toString().equals("")) {
//                        System.out.println(Integer.valueOf(v.getText().toString()));
//                        blue[0] = MainActivity.getTeam(Integer.valueOf(v.getText().toString()));
//                    }
//                }
//                return false;
//            }
//        });
//        teamNum = (EditText) root.findViewById(R.id.sim_team2);
//
//        teamNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
//                    if (!v.getText().toString().equals("")) {
//                        blue[1] = MainActivity.getTeam(Integer.valueOf(v.getText().toString()));
//                    }
//                }
//                return false;
//            }
//        });
//        teamNum = (EditText) root.findViewById(R.id.sim_team3);
//
//        teamNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
//                    if (!v.getText().toString().equals("")) {
//                        blue[2] = MainActivity.getTeam(Integer.valueOf(v.getText().toString()));
//                    }
//                }
//                return false;
//            }
//        });
//        teamNum = (EditText) root.findViewById(R.id.sim_team4);
//
//        teamNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                System.out.println(EditorInfo.IME_ACTION_UNSPECIFIED);
//                System.out.println(EditorInfo.IME_ACTION_NONE);
//                System.out.println(actionId);
//                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
//                    if (!v.getText().toString().equals("")) {
//                        red[0] = MainActivity.getTeam(Integer.valueOf(v.getText().toString()));
//                    }
//                }
//                return false;
//            }
//        });
//        teamNum = (EditText) root.findViewById(R.id.sim_team5);
//
//        teamNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
//                    if (!v.getText().toString().equals("")) {
//                        red[1] = MainActivity.getTeam(Integer.valueOf(v.getText().toString()));
//                    }
//                }
//                return false;
//            }
//        });
//        teamNum = (EditText) root.findViewById(R.id.sim_team6);
//
//        teamNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
//                    if (!v.getText().toString().equals("")) {
//                        red[2] = MainActivity.getTeam(Integer.valueOf(v.getText().toString()));
//
//                    }
//                }
//                return false;
//            }
//        });

        return root;
    }

    @Override
    public void updateViewsFromThe6ix() throws NoSuchFieldException, IllegalAccessException {
        EditText teamNum = (EditText) getActivity().findViewById(R.id.sim_team1);
        blue[0] = MainActivity.getTeam(Integer.valueOf(teamNum.getText().toString()));
        teamNum = (EditText) getActivity().findViewById(R.id.sim_team2);
        blue[1] = MainActivity.getTeam(Integer.valueOf(teamNum.getText().toString()));
        teamNum = (EditText) getActivity().findViewById(R.id.sim_team3);
        blue[2] = MainActivity.getTeam(Integer.valueOf(teamNum.getText().toString()));
        teamNum = (EditText) getActivity().findViewById(R.id.sim_team4);
        red[0] = MainActivity.getTeam(Integer.valueOf(teamNum.getText().toString()));
        teamNum = (EditText) getActivity().findViewById(R.id.sim_team5);
        red[1] = MainActivity.getTeam(Integer.valueOf(teamNum.getText().toString()));
        teamNum = (EditText) getActivity().findViewById(R.id.sim_team6);
        red[2] = MainActivity.getTeam(Integer.valueOf(teamNum.getText().toString()));

        for (int i = 0; i < 3; i++) {
            if (blue[i] == null || red[i] == null) {
                System.out.println(i);
                Toast.makeText(getActivity(), "Could not find all 6 teams", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        TextView text = (TextView) getActivity().findViewById(R.id.sim_total_team1);
        text.setText(blue[0].avgTotalPoints() + "");
        text = (TextView) getActivity().findViewById(R.id.sim_total_team2);
        text.setText(blue[1].avgTotalPoints() + "");
        text = (TextView) getActivity().findViewById(R.id.sim_total_team3);
        text.setText(blue[2].avgTotalPoints() + "");
        text = (TextView) getActivity().findViewById(R.id.sim_total_team4);
        text.setText(red[0].avgTotalPoints() + "");
        text = (TextView) getActivity().findViewById(R.id.sim_total_team5);
        text.setText(red[1].avgTotalPoints() + "");
        text = (TextView) getActivity().findViewById(R.id.sim_total_team6);
        text.setText(red[2].avgTotalPoints() + "");

        text = (TextView) getActivity().findViewById(R.id.sim_total_blue);
        text.setText(Math.round(blue[0].avgTotalPoints() + blue[1].avgTotalPoints() + blue[2].avgTotalPoints()) + "");

        text = (TextView) getActivity().findViewById(R.id.sim_total_red);
        text.setText(Math.round(red[0].avgTotalPoints() + red[1].avgTotalPoints() + red[2].avgTotalPoints()) + "");

        text = (TextView) getActivity().findViewById(R.id.sim_red_capture);
        double capturePercent = getCapPercent(red);
        text.setText(capturePercent == -1 ? "Weaken Unlikely" : capturePercent + "");


        text = (TextView) getActivity().findViewById(R.id.sim_blue_capture);
        capturePercent = getCapPercent(blue);
        text.setText(capturePercent == -1 ? "Weaken Unlikely" : capturePercent + "");


        text = (TextView) getActivity().findViewById(R.id.sim_red_defence);
        text.setText(Math.round(red[0].avgDefenceScore() + red[1].avgDefenceScore() + red[2].avgDefenceScore()) + "");

        text = (TextView) getActivity().findViewById(R.id.sim_blue_defence);
        text.setText(Math.round(blue[0].avgDefenceScore() + blue[1].avgDefenceScore() + blue[2].avgDefenceScore()) + "");

    }

    private double getCapPercent(TeamData[] red) {
        double count = 0;
        for (TeamData t : red) {
            count += t.avgHighGoalScore() + t.avgLowGoalScore();
        }
        if (count < 8) {
            return -1;
        } else {
            double percent = 100;//1
            for (TeamData t : red) {
               // percent *= (t.challengePercentage() / 100D + t.hangingPercentage() / 100D);
                percent = Math.min(percent, t.challengePercentage() + t.hangingPercentage());
            }
            return ((int) (percent * 100)) / 100D;
        }

    }

    public void saveMatch() {
        if(blue[0] == null) return;
        boolean redWin = (blue[0].avgTotalPoints() + blue[1].avgTotalPoints() + blue[2].avgTotalPoints()) > (red[0].avgTotalPoints() + red[1].avgTotalPoints() + red[2].avgTotalPoints());
        boolean redBreach = red[0].avgDefenceScore() + red[1].avgDefenceScore() + red[2].avgDefenceScore() >= 40;
        boolean blueBreach = blue[0].avgDefenceScore() + blue[1].avgDefenceScore() + blue[2].avgDefenceScore() >= 40;


        SimulatedMatch match = new SimulatedMatch(blue,red,redWin,redBreach,blueBreach,getCapPercent(red), getCapPercent(blue));
        MainActivity.simMatches.add(match);
    }
}
