package com.team610.scouting.scoutingapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import android.widget.TextView;

import com.firebase.client.Firebase;

import android.widget.ImageButton;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AutoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AutoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String[] defences = new String[4];

    private OnFragmentInteractionListener mListener;

    private static AutoFragment instance;

    CheckBox reachedDefenceCheckBox;
    CheckBox spybotCheckBox;
    CheckBox scoreHighGoalCheckBox;
    CheckBox scoreLowGoalCheckBox;

    //CheckBox s

    ImageButton defence1;
    ImageButton defence2;
    ImageButton defence3;
    ImageButton defence4;
    ImageButton lowBarDefence5;



    public AutoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AutoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AutoFragment newInstance(String param1, String param2) {
        AutoFragment fragment = new AutoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static AutoFragment getInstance(){
        if(instance == null){
            instance = new AutoFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void openAutonFragment(){

    }

    public void saveData(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_auto, container, false);

        defence1 = (ImageButton) v.findViewById(R.id.defence1_ImageButton);
        defence2 = (ImageButton) v.findViewById(R.id.defence2_ImageButton);
        defence3 = (ImageButton) v.findViewById(R.id.defence3_ImageButton);
        defence4 = (ImageButton) v.findViewById(R.id.defence4_ImageButton);
        lowBarDefence5 = (ImageButton) v.findViewById(R.id.lowBar_defence_ImageButton);

        defence1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }

        });



        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_auto,
                container, false);

        reachedDefenceCheckBox = (CheckBox) rootView.findViewById(R.id.reachTheDefence_checkBox);
        spybotCheckBox = (CheckBox) rootView.findViewById(R.id.spybot_checkBox);
        scoreHighGoalCheckBox= (CheckBox) rootView.findViewById(R.id.highGoal_checkBox);
        scoreLowGoalCheckBox = (CheckBox) rootView.findViewById(R.id.lowGoal_checkBox);

        //delete this
        MatchData.updateAuto();




        return rootView;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onAutonFragmentInteraction(uri);
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
        void onAutonFragmentInteraction(Uri uri);
    }
}
