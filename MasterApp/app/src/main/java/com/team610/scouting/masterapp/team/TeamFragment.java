package com.team610.scouting.masterapp.team;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.team610.scouting.masterapp.Defence;
import com.team610.scouting.masterapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
//TODO FOR COMMENTS HAVE Alert Dialog THING WITH COMMENT
public class TeamFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private int team;
    private boolean left;
    private OnFragmentInteractionListener mListener;

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
            team = getArguments().getInt("TEAM");
            left = getArguments().getBoolean("LEFT");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public void loadTeamData(int teamNum) {
        //TODO
    }

    public void updateViewsFromThe6ix(TeamData team) throws NoSuchFieldException, IllegalAccessException {

        ViewGroup splitFrag = (ViewGroup) getView().findViewById(R.id.class.getField("split" + (left ? "left" : "right")).getInt(R.id.class));
        TableLayout averages = (TableLayout) ((ViewGroup) splitFrag.getChildAt(0)).getChildAt(1);
        //Averages
        ((TextView) averages.findViewById(R.id.autonScoreBox)).setText(team.avgAutonScore + "");
        ((TextView) averages.findViewById(R.id.defenseScoreBox)).setText(team.avgDefenseScore + "");
        ((TextView) averages.findViewById(R.id.highGoalScoreBox)).setText(team.avgHighGoalScore + "");
        ((TextView) averages.findViewById(R.id.highGoalAccuracyScoreBox)).setText(team.highGoalPercentage + "");
        ((TextView) averages.findViewById(R.id.lowGoalScoreBox)).setText(team.avgLowGoalScore + "");
        ((TextView) averages.findViewById(R.id.lowGoalAccuracyScoreBox)).setText(team.lowGoalPercentage + "");
        ((TextView) averages.findViewById(R.id.courtyardScoreBox)).setText(team.avgCourtyardDrops + "");
        ((TextView) averages.findViewById(R.id.hangingScoreBox)).setText(team.hangingPercentage + "");
        ((TextView) averages.findViewById(R.id.challengeScoreBox)).setText(team.challengePercentage + "");
        ((TextView) averages.findViewById(R.id.hangingScoreBox)).setText(team.hangingPercentage + "");

        TableLayout defences = (TableLayout) ((ViewGroup) splitFrag.getChildAt(0)).getChildAt(4);
        //AvgTime = Crosses TODO fix that
        ((TextView) defences.findViewById(R.id.portcullisAvgTime)).setText(team.defences.get(Defence.PORTCULLIS)[1] + "");

    }
}
