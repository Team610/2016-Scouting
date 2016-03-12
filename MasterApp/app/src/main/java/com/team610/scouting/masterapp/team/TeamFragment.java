package com.team610.scouting.masterapp.team;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.team610.scouting.masterapp.Defence;
import com.team610.scouting.masterapp.MainActivity;
import com.team610.scouting.masterapp.R;
import com.team610.scouting.masterapp.ScoutingFragment;
import com.team610.scouting.masterapp.SplitScreenFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
//TODO FOR COMMENTS HAVE Alert Dialog THING WITH COMMENT
public class TeamFragment extends ScoutingFragment {

    // TODO: Rename and change types of parameters
    private int id;
    private boolean left;
    private OnFragmentInteractionListener mListener;
    TeamData team;

    public TeamFragment() {
        // Required empty public constructor
    }

    /**
     * @param team ID of the first team.
     * @return A new instance of fragment TeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamFragment newInstance(int team, boolean left) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putInt("TEAM", team);
        args.putBoolean("LEFT", left);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("TEAM");
            left = getArguments().getBoolean("LEFT");
            team = ((MainActivity) getActivity()).getTeam(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        EditText teamNum = (EditText) view.findViewById(R.id.teamNumber);
        teamNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean yes = false;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    yes = true;
                    if (!v.getText().toString().equals("")) {
                        if (left) {
                            ((SplitScreenFragment) MainActivity.mFrag).one.loadTeamData(v.getText().toString());
                        } else {
                            ((SplitScreenFragment) MainActivity.mFrag).two.loadTeamData(v.getText().toString());
                        }

                    }
                }
                return yes;
            }
        });
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void loadTeamData(String teamNum) {
        team = MainActivity.teams.get(teamNum);
        if (team == null) {
            Toast.makeText(getActivity(), "Team Not Found", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            updateViewsFromThe6ix();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void updateViewsFromThe6ix() throws NoSuchFieldException, IllegalAccessException {
        if (team == null) return;
        System.out.println(left);
        ViewGroup splitFrag = (ViewGroup) getActivity().findViewById(left ? R.id.splitleft : R.id.splitright);
        ViewGroup teamFrag = (ViewGroup) splitFrag.getChildAt(0);
        // System.out.println(teamFrag == null);
        TableLayout averages = (TableLayout) teamFrag.getChildAt(1);
        //Averages
        ((TextView) averages.findViewById(R.id.autonScoreBox)).setText(team.avgAutonScore() + "");
        ((TextView) averages.findViewById(R.id.defenseScoreBox)).setText(team.avgDefenceScore() + "");
        ((TextView) averages.findViewById(R.id.highGoalScoreBox)).setText(team.avgHighGoalScore() + "");
        ((TextView) averages.findViewById(R.id.highGoalAccuracyScoreBox)).setText(team.highGoalPercentage() + "");
        ((TextView) averages.findViewById(R.id.lowGoalScoreBox)).setText(team.avgLowGoalScore() + "");
        ((TextView) averages.findViewById(R.id.lowGoalAccuracyScoreBox)).setText(team.lowGoalPercentage() + "");
        ((TextView) averages.findViewById(R.id.courtyardScoreBox)).setText(team.avgCourtyardDrops() + "");
        ((TextView) averages.findViewById(R.id.hangingScoreBox)).setText(team.hangingPercentage() + "");
        ((TextView) averages.findViewById(R.id.challengeScoreBox)).setText(team.challengePercentage() + "");
        ((TextView) averages.findViewById(R.id.hangingScoreBox)).setText(team.hangingPercentage() + "");

        ((TextView) teamFrag.findViewById(R.id.matchText)).setText("Matches: " + team.matches.size());

        TableLayout defences = (TableLayout) teamFrag.getChildAt(4);
        //AvgTime = Crosses TODO fix that
        ((TextView) defences.findViewById(R.id.portcullisAvgTime)).setText(team.getTimesCrossed(Defence.PORTCULLIS) + "");
        ((TextView) defences.findViewById(R.id.chevalDeFriseAvgTime)).setText(team.getTimesCrossed(Defence.CHEVAL_DE_FRISE) + "");
        ((TextView) defences.findViewById(R.id.moatAvgTime)).setText(team.getTimesCrossed(Defence.MOAT) + "");
        ((TextView) defences.findViewById(R.id.rampartsAvgTime)).setText(team.getTimesCrossed(Defence.RAMPARTS) + "");
        ((TextView) defences.findViewById(R.id.drawbridgeAvgTime)).setText(team.getTimesCrossed(Defence.DRAWBRIDGE) + "");
        ((TextView) defences.findViewById(R.id.sallyPortAvgTime)).setText(team.getTimesCrossed(Defence.SALLY_PORT) + "");
        ((TextView) defences.findViewById(R.id.rockWallAvgTime)).setText(team.getTimesCrossed(Defence.ROCK_WALL) + "");
        ((TextView) defences.findViewById(R.id.roughTerrainAvgTime)).setText(team.getTimesCrossed(Defence.ROUGH_TERRAIN) + "");
        ((TextView) defences.findViewById(R.id.lowBarAvgTime)).setText(team.getTimesCrossed(Defence.LOW_BAR) + "");

        //Ratings
        TableRow row = (TableRow) defences.getChildAt(1);
        row.setBackgroundColor(getColor(team.getRating(Defence.PORTCULLIS)));
        row = (TableRow) defences.getChildAt(2);
        row.setBackgroundColor(getColor(team.getRating(Defence.CHEVAL_DE_FRISE)));
        row = (TableRow) defences.getChildAt(3);
        row.setBackgroundColor(getColor(team.getRating(Defence.MOAT)));
        row = (TableRow) defences.getChildAt(4);
        row.setBackgroundColor(getColor(team.getRating(Defence.RAMPARTS)));
        row = (TableRow) defences.getChildAt(5);
        row.setBackgroundColor(getColor(team.getRating(Defence.DRAWBRIDGE)));
        row = (TableRow) defences.getChildAt(6);
        row.setBackgroundColor(getColor(team.getRating(Defence.SALLY_PORT)));
        row = (TableRow) defences.getChildAt(7);
        row.setBackgroundColor(getColor(team.getRating(Defence.ROCK_WALL)));
        row = (TableRow) defences.getChildAt(8);
        row.setBackgroundColor(getColor(team.getRating(Defence.ROUGH_TERRAIN)));
        row = (TableRow) defences.getChildAt(9);
        row.setBackgroundColor(getColor(team.getRating(Defence.LOW_BAR)));

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, team.matches);
        final ListView listView = (ListView) splitFrag.getChildAt(0).findViewById(R.id.matchList);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).matchFrag((String) listView.getItemAtPosition(position));

            }
        });

        //MISC

        TableLayout misc = (TableLayout) ((ViewGroup) teamFrag.getChildAt(6)).getChildAt(0);
        ((TextView) misc.findViewById(R.id.defenceRating)).setText(team.defensiveRating + "");
        ((CheckBox) misc.findViewById(R.id.checkMateCheck)).setChecked(team.shotFromCheckMate);
        ((CheckBox) misc.findViewById(R.id.courtyardShotCheck)).setChecked(team.shotFromCourtyard);
        ((CheckBox) misc.findViewById(R.id.defenceCheck)).setChecked(team.shotFromDefences);
        ((CheckBox) misc.findViewById(R.id.cornerShotCheck)).setChecked(team.shotFromCorner);
//        ((TextView)misc.findViewById(R.id.capturePercentage)).setText(team.captures);
//        ((TextView)misc.findViewById(R.id.breachPercentage)).setText(team.breaches);



    }

    public int getColor(double rating) {
        //Green R:0 G:225 B:0
        //Yellow R:225 G:225 B:0
        //Red R:225 G:0 B:0
        int r, g, b = 0;
        System.out.println(rating);
        if (rating == 0) return Color.GRAY;
        else if (rating <= 2) {
            r = (int) (225 * (rating - 1));
            g = 225;
        } else {
            r = 225;
            g = (int) (225 - 225 * (rating - 2));
        }
        return Color.argb(255, r, g, b);
    }
}
