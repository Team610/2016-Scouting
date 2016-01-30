package com.team610.scouting.scoutingapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeleopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeleopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeleopFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    SeekBar sb1;

    //plus button
    ImageButton plus;
    ImageButton minus;

    TextView highGoalScoresTextView;
    //int highGoalScores = 0;

    TextView lowGoalScoresTextView;
    //int lowGoalScores = 0;

    TextView courtyardScoresTextView;
    //int courtyardScores = 0;

    int state = 1;

    ImageButton plusMisses;
    ImageButton minusMisses;

    TextView highGoalMissesTextView;
    //int highGoalMisses = 0;

    TextView lowGoalMissesTextView;
    //int lowGoalMisses = 0;

    TextView courtyardMissesTextView;
    //int courtyardMisses = 0;


    private static TeleopFragment instance;


    private OnFragmentInteractionListener mListener;

    public TeleopFragment() {
        // Required empty public constructor
    }

    public static TeleopFragment getInstance(){
        if(instance == null){
            instance = new TeleopFragment();
        }
        return instance;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment teleopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeleopFragment newInstance(String param1, String param2) {
        TeleopFragment fragment = new TeleopFragment();
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

    public void onStopTrackingTouch(SeekBar seekBar) {
        int mProgress = seekBar.getProgress();
        if(mProgress >= 0 & mProgress < 26) {
            seekBar.setProgress(10);
            state = 0;
            MainActivity.vib.vibrate(100);
        } else if(mProgress > 25 & mProgress < 76) {
            seekBar.setProgress(50);
            state = 1;
            MainActivity.vib.vibrate(100);
        } else{
            seekBar.setProgress(90);
            state = 2;
            MainActivity.vib.vibrate(100);
        }
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // we don't need it
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        // we don't need it
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_teleop,
                container, false);

        highGoalScoresTextView = (TextView) rootView.findViewById(R.id.highGoal_scores_TextView);
        lowGoalScoresTextView = (TextView) rootView.findViewById(R.id.lowGoal_scores_TextView);
        courtyardScoresTextView = (TextView) rootView.findViewById(R.id.courtyard_scores_TextView);

        highGoalScoresTextView.setText("" + MatchData.highGoalScores);
        lowGoalScoresTextView.setText("" + MatchData.lowGoalScores);
        courtyardScoresTextView.setText("" + MatchData.courtyardScores);


        //plus button
        plus = (ImageButton) rootView.findViewById(R.id.plus_ImageButton);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0) {
                    MatchData.highGoalScores++;
                    highGoalScoresTextView.setText("" + MatchData.highGoalScores);
                } else if (state == 1) {
                    MatchData.lowGoalScores++;
                    lowGoalScoresTextView.setText("" + MatchData.lowGoalScores);
                } else {
                    MatchData.courtyardScores++;
                    courtyardScoresTextView.setText("" + MatchData.courtyardScores);
                }

                MainActivity.vib.vibrate(100);
                //remove
                MatchData.updateTeleop();

            }
        });

        minus = (ImageButton) rootView.findViewById(R.id.minus_ImageButton);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 0) {
                    MatchData.highGoalScores--;
                    if(MatchData.highGoalScores < 0){
                        MatchData.highGoalScores = 0;
                    }
                    highGoalScoresTextView.setText("" + MatchData.highGoalScores);
                }
                else if(state == 1){
                    MatchData.lowGoalScores--;
                    if(MatchData.lowGoalScores < 0){
                        MatchData.lowGoalScores = 0;
                    }
                    lowGoalScoresTextView.setText("" + MatchData.lowGoalScores);
                }
                else{
                    MatchData.courtyardScores--;
                    if(MatchData.courtyardScores < 0){
                        MatchData.courtyardScores = 0;
                    }
                    courtyardScoresTextView.setText("" + MatchData.courtyardScores);
                }

                MainActivity.vib.vibrate(100);
                //remove
                MatchData.updateTeleop();
            }
        });

        highGoalMissesTextView = (TextView) rootView.findViewById(R.id.highgoal_misses_TextView);
        lowGoalMissesTextView = (TextView) rootView.findViewById(R.id.lowgoal_misses_TextView);
        courtyardMissesTextView = (TextView) rootView.findViewById(R.id.courtyard_misses_TextView);

        plusMisses = (ImageButton) rootView.findViewById(R.id.plus_misses_ImageButton);
        minusMisses = (ImageButton) rootView.findViewById(R.id.minus_misses_ImageButton);

        highGoalMissesTextView.setText("" + MatchData.highGoalMisses);
        lowGoalMissesTextView.setText("" + MatchData.lowGoalMisses);
        courtyardMissesTextView.setText("" + MatchData.courtyardMisses);


        plusMisses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 0) {
                    MatchData.highGoalMisses++;
                    highGoalMissesTextView.setText("" + MatchData.highGoalMisses);
                }
                else if(state == 1){
                    MatchData.lowGoalMisses++;
                    lowGoalMissesTextView.setText("" + MatchData.lowGoalMisses);
                }
                else{
                    MatchData.courtyardMisses++;
                    courtyardMissesTextView.setText("" + MatchData.courtyardMisses);
                }

                MainActivity.vib.vibrate(100);

                //remove
                MatchData.updateTeleop();
            }
        });


        minusMisses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 0) {
                    MatchData.highGoalMisses--;
                    if(MatchData.highGoalMisses < 0){
                        MatchData.highGoalMisses = 0;
                    }
                    highGoalMissesTextView.setText("" + MatchData.highGoalMisses);
                }
                else if(state == 1){
                    MatchData.lowGoalMisses--;
                    if(MatchData.lowGoalMisses < 0){
                        MatchData.lowGoalMisses = 0;
                    }
                    lowGoalMissesTextView.setText("" + MatchData.lowGoalMisses);
                }
                else{
                    MatchData.courtyardMisses--;
                    if(MatchData.courtyardMisses < 0){
                        MatchData.courtyardMisses = 0;
                    }
                    courtyardMissesTextView.setText("" + MatchData.courtyardMisses);
                }

                MainActivity.vib.vibrate(100);

                //remove
                MatchData.updateTeleop();
            }
        });


        sb1 = (SeekBar) rootView.findViewById(R.id.setgoal_SeekBar);
        sb1.setOnSeekBarChangeListener(this);

        sb1.setProgress(50);

        return rootView;
        //return inflater.inflate(R.layout.fragment_teleop, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTeleopFragmentInteraction(uri);
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
        void onTeleopFragmentInteraction(Uri uri);
    }
}
