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


    MatchData thisMatch = MatchData.getInstance();

    ImageButton defence1;
    ImageButton defence1inc;
    ImageButton defence1dec;

    ImageButton defence2;
    ImageButton defence2inc;
    ImageButton defence2dec;

    ImageButton defence3;
    ImageButton defence3inc;
    ImageButton defence3dec;

    ImageButton defence4;
    ImageButton defence4inc;
    ImageButton defence4dec;

    ImageButton lowBarDefence5;
    ImageButton defence5inc;
    ImageButton defence5dec;


    int defence1counter = 0;
    int defence2counter = 0;
    int defence3counter = 0;
    int defence4counter = 0;
    int defence5counter = 0;

    int defence1rating = 0;
    int defence2rating = 0;
    int defence3rating = 0;
    int defence4rating = 0;
    int defence5rating = 0;

    TextView defence1DisplayCount;
    TextView defence2DisplayCount;
    TextView defence3DisplayCount;
    TextView defence4DisplayCount;
    TextView defence5DisplayCount;

    String[] defences = new String[4];

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


    public void setDefenceButtonPics(ImageButton defence , int i){

            if (defences[i] != null) {
                if (defences[i].equals("porticullis")) {
                    defence.setImageResource(R.drawable.portcullis);
                } else if (defences[i].equals("moat")) {
                    defence.setImageResource(R.drawable.moat);
                } else if (defences[i].equals("ramparts")) {
                    defence.setImageResource(R.drawable.ramparts);
                } else if (defences[i].equals("rockwall")) {
                    defence.setImageResource(R.drawable.rockwall);
                } else if (defences[i].equals("roughterrain")) {
                    defence.setImageResource(R.drawable.roughterrain);
                } else if (defences[i].equals("sallyport")) {
                    defence.setImageResource(R.drawable.sallyport);
                } else if (defences[i].equals("drawbridge")) {
                    defence.setImageResource(R.drawable.drawbridge);
                } else if (defences[i].equals("chevaldefrise")) {
                    defence.setImageResource(R.drawable.chevaldefrise);
                }
            }

    }


    public void openTeleopFragment(){

        for(int i = 0; i<4; i++){
            defences[i] = thisMatch.defenceTypes[i];
        }


        setDefenceButtonPics(defence1, 0);
        setDefenceButtonPics(defence2,1);
        setDefenceButtonPics(defence3,2);
        setDefenceButtonPics(defence4, 3);


        lowBarDefence5.setImageResource(R.drawable.lowbar);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // need this line to link xml!!!!
        View rootView = inflater.inflate(R.layout.fragment_teleop,
                container, false);

        defence1 = (ImageButton) rootView.findViewById(R.id.defence1_ImageButton);
        defence2 = (ImageButton) rootView.findViewById(R.id.defence2_ImageButton);
        defence3 = (ImageButton) rootView.findViewById(R.id.defence3_ImageButton);
        defence4 = (ImageButton) rootView.findViewById(R.id.defence4_ImageButton);
        lowBarDefence5 = (ImageButton) rootView.findViewById(R.id.defence5_lowbar_ImageButton);

        defence1inc = (ImageButton) rootView.findViewById(R.id.plus_defence1_ImageButton);
        defence2inc = (ImageButton) rootView.findViewById(R.id.plus_defence2_ImageButton);
        defence3inc = (ImageButton) rootView.findViewById(R.id.plus_defence3_ImageButton);
        defence4inc = (ImageButton) rootView.findViewById(R.id.plus_defence4_ImageButton);
        defence5inc = (ImageButton) rootView.findViewById(R.id.plus_defence5_ImageButton);

        defence1dec = (ImageButton) rootView.findViewById(R.id.minus_defence1_ImageButton);
        defence2dec = (ImageButton) rootView.findViewById(R.id.minus_defence2_ImageButton);
        defence3dec = (ImageButton) rootView.findViewById(R.id.minus_defence3_ImageButton);
        defence4dec = (ImageButton) rootView.findViewById(R.id.minus_defence4_ImageButton);
        defence5dec = (ImageButton) rootView.findViewById(R.id.minus_defence5_ImageButton);


        defence1DisplayCount = (TextView) rootView.findViewById(R.id.defence1_count_TextView);
        defence2DisplayCount = (TextView) rootView.findViewById(R.id.defence2_count_TextView);
        defence3DisplayCount = (TextView) rootView.findViewById(R.id.defence3_count_TextView);
        defence4DisplayCount = (TextView) rootView.findViewById(R.id.defence4_count_TextView);
        defence5DisplayCount = (TextView) rootView.findViewById(R.id.defence5_count_TextView);



        defence1inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                defence1counter++;

                MainActivity.vib.vibrate(100);
                //remove


                defence1DisplayCount.setText("" + defence1counter);

            }
        });
        defence1dec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(defence1counter > 0){
                    defence1counter--;
                }

                MainActivity.vib.vibrate(100);

                defence1DisplayCount.setText("" + defence1counter);
            }
        });

        defence2inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                defence2counter++;

                MainActivity.vib.vibrate(100);
                //remove


                defence2DisplayCount.setText("" + defence2counter);

            }
        });
        defence2dec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(defence2counter > 0){
                    defence2counter--;
                }

                MainActivity.vib.vibrate(100);

                defence2DisplayCount.setText("" + defence2counter);
            }
        });
        defence3inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                defence3counter++;

                MainActivity.vib.vibrate(100);
                //remove


                defence3DisplayCount.setText("" + defence3counter);

            }
        });
        defence3dec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(defence3counter > 0){
                    defence3counter--;
                }

                MainActivity.vib.vibrate(100);

                defence3DisplayCount.setText("" + defence3counter);
            }
        });
        defence4inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                defence4counter++;

                MainActivity.vib.vibrate(100);
                //remove


                defence4DisplayCount.setText("" + defence4counter);

            }
        });
        defence4dec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(defence4counter > 0){
                    defence4counter--;
                }

                MainActivity.vib.vibrate(100);

                defence4DisplayCount.setText("" + defence4counter);
            }
        });
        defence5inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                defence5counter++;

                MainActivity.vib.vibrate(100);
                //remove


                defence5DisplayCount.setText("" + defence5counter);

            }
        });
        defence5dec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(defence5counter > 0){
                    defence5counter--;
                }

                MainActivity.vib.vibrate(100);

                defence5DisplayCount.setText("" + defence5counter);
            }
        });


        highGoalScoresTextView = (TextView) rootView.findViewById(R.id.highGoal_scores_TextView);
        lowGoalScoresTextView = (TextView) rootView.findViewById(R.id.lowGoal_scores_TextView);
        courtyardScoresTextView = (TextView) rootView.findViewById(R.id.courtyard_scores_TextView);

        highGoalScoresTextView.setText("" + thisMatch.highGoalScores);
        lowGoalScoresTextView.setText("" + thisMatch.lowGoalScores);
        courtyardScoresTextView.setText("" + thisMatch.courtyardScores);


        //plus button
        plus = (ImageButton) rootView.findViewById(R.id.plus_ImageButton);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0) {
                    thisMatch.highGoalScores++;
                    highGoalScoresTextView.setText("" + thisMatch.highGoalScores);
                } else if (state == 1) {
                    thisMatch.lowGoalScores++;
                    lowGoalScoresTextView.setText("" + thisMatch.lowGoalScores);
                } else {
                    thisMatch.courtyardScores++;
                    courtyardScoresTextView.setText("" + thisMatch.courtyardScores);
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
                    thisMatch.highGoalScores--;
                    if(thisMatch.highGoalScores < 0){
                        thisMatch.highGoalScores = 0;
                    }
                    highGoalScoresTextView.setText("" + thisMatch.highGoalScores);
                }
                else if(state == 1){
                    thisMatch.lowGoalScores--;
                    if(thisMatch.lowGoalScores < 0){
                        thisMatch.lowGoalScores = 0;
                    }
                    lowGoalScoresTextView.setText("" + thisMatch.lowGoalScores);
                }
                else{
                    thisMatch.courtyardScores--;
                    if(thisMatch.courtyardScores < 0){
                        thisMatch.courtyardScores = 0;
                    }
                    courtyardScoresTextView.setText("" + thisMatch.courtyardScores);
                }

                MainActivity.vib.vibrate(100);
                //remove
                MatchData.updateTeleop();
            }
        });

        openTeleopFragment();

        highGoalMissesTextView = (TextView) rootView.findViewById(R.id.highgoal_misses_TextView);
        lowGoalMissesTextView = (TextView) rootView.findViewById(R.id.lowgoal_misses_TextView);
        courtyardMissesTextView = (TextView) rootView.findViewById(R.id.courtyard_misses_TextView);

        plusMisses = (ImageButton) rootView.findViewById(R.id.plus_misses_ImageButton);
        minusMisses = (ImageButton) rootView.findViewById(R.id.minus_misses_ImageButton);

        highGoalMissesTextView.setText("" + thisMatch.highGoalMisses);
        lowGoalMissesTextView.setText("" + thisMatch.lowGoalMisses);
        courtyardMissesTextView.setText("" + thisMatch.courtyardMisses);


        plusMisses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 0) {
                    thisMatch.highGoalMisses++;
                    highGoalMissesTextView.setText("" + thisMatch.highGoalMisses);
                }
                else if(state == 1){
                    thisMatch.lowGoalMisses++;
                    lowGoalMissesTextView.setText("" + thisMatch.lowGoalMisses);
                }
                else{
                    thisMatch.courtyardMisses++;
                    courtyardMissesTextView.setText("" + thisMatch.courtyardMisses);
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
                    thisMatch.highGoalMisses--;
                    if(thisMatch.highGoalMisses < 0){
                        thisMatch.highGoalMisses = 0;
                    }
                    highGoalMissesTextView.setText("" + thisMatch.highGoalMisses);
                }
                else if(state == 1){
                    thisMatch.lowGoalMisses--;
                    if(thisMatch.lowGoalMisses < 0){
                        thisMatch.lowGoalMisses = 0;
                    }
                    lowGoalMissesTextView.setText("" + thisMatch.lowGoalMisses);
                }
                else{
                    thisMatch.courtyardMisses--;
                    if(thisMatch.courtyardMisses < 0){
                        thisMatch.courtyardMisses = 0;
                    }
                    courtyardMissesTextView.setText("" + thisMatch.courtyardMisses);
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
    public void onPause() {
        saveData();
        super.onPause();
    }

    public void saveData(){

        thisMatch.defence1Cross = defence1counter;
        thisMatch.defence2Cross = defence2counter;
        thisMatch.defence3Cross = defence3counter;
        thisMatch.defence4Cross = defence4counter;
        thisMatch.defence5Cross = defence5counter;

        thisMatch.defence1Rating = defence1rating;
        thisMatch.defence2Rating = defence2rating;
        thisMatch.defence3Rating = defence3rating;
        thisMatch.defence4Rating = defence4rating;
        thisMatch.defence5Rating = defence5rating;



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
