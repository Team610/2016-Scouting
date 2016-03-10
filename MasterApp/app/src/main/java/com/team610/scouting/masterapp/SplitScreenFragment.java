package com.team610.scouting.masterapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team610.scouting.masterapp.team.TeamFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SplitScreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SplitScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplitScreenFragment extends ScoutingFragment {

    // TODO: Rename and change types of parameters
    private int team1;
    private int team2;
    private TeamFragment one,two;
    private OnFragmentInteractionListener mListener;

    public SplitScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SplitScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SplitScreenFragment newInstance(int team1, int team2) {
        SplitScreenFragment fragment = new SplitScreenFragment();
        Bundle args = new Bundle();
        args.putInt("TEAM_1", team1);
        args.putInt("TEAM_2", team2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            team1 = getArguments().getInt("TEAM_1");
            team2 = getArguments().getInt("TEAM_2");
        }
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        one = TeamFragment.newInstance(team1,true);
        two = TeamFragment.newInstance(team2,true);
        trans.add(R.id.splitleft,one);
        trans.add(R.id.splitright,two).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_split_screen, container, false);
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

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void updateViewsFromThe6ix() throws NoSuchFieldException, IllegalAccessException {
        one.updateViewsFromThe6ix();
        two.updateViewsFromThe6ix();
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
}
