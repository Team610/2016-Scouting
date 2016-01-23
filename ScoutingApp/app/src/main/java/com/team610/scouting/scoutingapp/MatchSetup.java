package com.team610.scouting.scoutingapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchSetup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchSetup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchSetup extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static MatchSetup instance;
    int selectedDefence;

    MatchData thisMatch = MatchData.getInstance();
    String[] defences = new String[4];
    int match;
    int team;


    public MatchSetup() {
        // Required empty public constructor
        openMatchSetup();
    }

    public void openMatchSetup(){
        for(int i = 0; i<4; i++){
            defences[i] = thisMatch.defenceTypes[i];
        }

    }

    public void saveData(){
        for(int i= 0; i < 4; i++){
            thisMatch.defenceTypes[i] = defences[i];
        }
    }


    public static MatchSetup getInstance() {
        if(instance == null){
            instance = new MatchSetup();
        }
        return instance;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchSetup.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchSetup newInstance(String param1, String param2) {
        MatchSetup fragment = new MatchSetup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_setup, container, false);

    }



    //yo

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.defence1_ImageButton:
                selectedDefence = 1;

                break;
            case R.id.defence2_ImageButton:
                selectedDefence = 2;

                break;
            case R.id.defence3_ImageButton:
                selectedDefence = 3;

                break;
            case R.id.defence4_ImageButton:
                selectedDefence = 4;

                break;
        }
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
}
