package com.team610.scouting.masterapp;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.team610.scouting.masterapp.team.TeamData;

import java.lang.reflect.Field;
import java.util.Comparator;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamListFragment extends ScoutingFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeamListFragment() {
        // Required empty public constructor
    }

    /*
     * @return A new instance of fragment TeamListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamListFragment newInstance() {
        TeamListFragment fragment = new TeamListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TeamData[] teams;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);
        SortableTableView<TeamData> table = (SortableTableView) view.findViewById(R.id.tableview);
        //TODO load all team data
        teams = new TeamData[MainActivity.teams.size()];
        int i = 0;
        for (TeamData t : MainActivity.teams.values()) {
            teams[i++] = t;
        }
        table.setDataAdapter(new TeamTableDataAdapter(getActivity(), teams));
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getActivity(), "Team", "Auton", "Defence", "High %", "High Shots", "Low %", "Low Shots", "Hang %", "Challenge %", "Courtyard Drops");
        headerAdapter.setTextSize(8);

        table.setHeaderAdapter(headerAdapter);
        table.setColumnComparator(0, new idComparator());
        table.setColumnComparator(1, new avgAutonScoreComparator());
        table.setColumnComparator(2, new avgDefenceScoreComparator());
        table.setColumnComparator(3, new highGoalPercentageComparator());
        table.setColumnComparator(4, new avgHighGoalScoreComparator());
        table.setColumnComparator(5, new lowGoalScorePercentageComparator());
        table.setColumnComparator(6, new avgLowGoalScoreComparator());
        table.setColumnComparator(7, new hangingPercentageComparator());
        table.setColumnComparator(8, new challengePercentageComparator());
        table.setColumnComparator(9, new avgCourtyardDropsComparator());
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTeamListFragmentInteraction(uri);
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

    @Override
    public void updateViewsFromThe6ix() throws NoSuchFieldException, IllegalAccessException {

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
        void onTeamListFragmentInteraction(Uri uri);
    }

//    private static class TeamDataComparator implements Comparator<TeamData> {
//        Method m;
//
//        public TeamDataComparator(String field) {
//            try {
//                m = TeamData.class.getMethod(field);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public int compare(TeamData one, TeamData two) {
//            try {
//                return (int) (m.invoke(one)) - (int) (m.invoke(two));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//            return -1;
//        }
//
//    }

    private static class idComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
                return (one.id) - (two.id);
        }
    }
    private static class avgAutonScoreComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            //return (one.avgAutonScore()) - (two.avgAutonScore());
            return Double.compare(one.avgAutonScore(), two.avgAutonScore());


        }
    }
    private static class avgDefenceScoreComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
           // return (one.avgDefenceScore()) - (two.avgDefenceScore());
           return Double.compare(one.avgDefenceScore(), two.avgDefenceScore());
        }
    }
    private static class avgHighGoalScoreComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            //return (one.avgHighGoalScore()) - (two.avgHighGoalScore());
            return Double.compare(one.avgHighGoalScore(), two.avgHighGoalScore());
        }
    }
    private static class highGoalPercentageComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            return (one.highGoalPercentage()) - (two.highGoalPercentage());
        }
    }
    private static class avgLowGoalScoreComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            //return (one.avgLowGoalScore()) - (two.avgLowGoalScore());
            return Double.compare(one.avgLowGoalScore(), two.avgLowGoalScore());
        }
    }
    private static class lowGoalScorePercentageComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            return (one.lowGoalPercentage()) - (two.lowGoalPercentage());
        }
    }
    private static class hangingPercentageComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            return (one.hangingPercentage()) - (two.hangingPercentage());
        }
    }
    private static class challengePercentageComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            return (one.challengePercentage()) - (two.challengePercentage());
        }
    }
    private static class avgCourtyardDropsComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            //return (one.avgCourtyardDrops()) - (two.avgCourtyardDrops());
          return Double.compare(one.avgCourtyardDrops(), two.avgCourtyardDrops());
        }
    }




}

/*table.setColumnComparator(7, new TeamDataComparator("hangingPercentage"));
        table.setColumnComparator(8, new TeamDataComparator("challengePercentage"));
        table.setColumnComparator(9, new TeamDataComparator("avgCourtyardDrops")); */

