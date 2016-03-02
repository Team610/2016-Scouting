package com.team610.scouting.scoutingapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExtraData.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExtraData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExtraData extends Fragment implements SeekBar.OnSeekBarChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static ExtraData instance;

    String comment;
    String scoutName;



    SeekBar sb1;


    //0 is no defence
    int defenceRating;


    CheckBox captureCheck;
    CheckBox breachCheck;
    CheckBox challengeCheck;
    CheckBox hangCheck;
    EditText commentText;
    EditText scoutNameText;
    CheckBox shotDefencesCheck;
    CheckBox shotCheckMateCheck;
    CheckBox shotPopShotCheck;

    MatchData thisMatch = MatchData.getInstance();

    Button nextPage;

    public static ExtraData getInstance(){
        if(instance == null){
            instance = new ExtraData();
        }
        return instance;
    }


    private OnFragmentInteractionListener mListener;

    public ExtraData() {
        // Required empty public constructor
    }

    public void saveData(){

        thisMatch.breach = breachCheck.isChecked();
        thisMatch.capture = captureCheck.isChecked();
        thisMatch.challenge = challengeCheck.isChecked();
        thisMatch.hang = hangCheck.isChecked();
        thisMatch.comment = commentText.getText().toString();
        thisMatch.scoutName = scoutNameText.getText().toString();
        thisMatch.defensiveRating = defenceRating;
        thisMatch.shotFromCheckMate = shotCheckMateCheck.isChecked();
        thisMatch.shotFromDefences = shotDefencesCheck.isChecked();
        thisMatch.shotFromPopShot = shotPopShotCheck.isChecked();
    }


    public void onStopTrackingTouch(SeekBar seekBar) {
        int mProgress = seekBar.getProgress();

        //at 0
        if(mProgress >= 0 & mProgress < 10) {
            seekBar.setProgress(0);
            defenceRating = 0;
            MainActivity.vib.vibrate(100);
        }
        //at 20
        else if(mProgress >= 10 & mProgress < 30) {
            seekBar.setProgress(20);
            defenceRating = 1;
            MainActivity.vib.vibrate(100);
        }
        //at 40
        else if(mProgress >= 30 & mProgress < 50) {
            seekBar.setProgress(40);
            defenceRating = 2;
            MainActivity.vib.vibrate(100);
        }
        //at 60
        else if(mProgress >= 50 & mProgress < 70) {
            seekBar.setProgress(60);
            defenceRating = 3;
            MainActivity.vib.vibrate(100);
        }
        //at 80
        else if(mProgress >= 70 & mProgress < 90) {
            seekBar.setProgress(80);
            defenceRating = 4;
            MainActivity.vib.vibrate(100);
        }
        //at 100
        else{
            seekBar.setProgress(100);
            defenceRating = 5;
            MainActivity.vib.vibrate(100);
        }

        //CONTINUE
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // we don't need it
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        // we don't need it
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExtraData.
     */
    // TODO: Rename and change types and number of parameters
    public static ExtraData newInstance(String param1, String param2) {
        ExtraData fragment = new ExtraData();
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

        View v = inflater.inflate(R.layout.fragment_extra_data, container, false);



        captureCheck = (CheckBox) v.findViewById(R.id.capture_CheckBox);
        challengeCheck = (CheckBox) v.findViewById(R.id.challenge_CheckBox);
        hangCheck = (CheckBox) v.findViewById(R.id.hang_CheckBox);
        breachCheck = (CheckBox) v.findViewById(R.id.breach_CheckBox);
        nextPage = (Button) v.findViewById(R.id.next_Button);
        commentText = (EditText) v.findViewById(R.id.comment_EditText);
        scoutNameText = (EditText) v.findViewById(R.id.scout_name_EditText);

        shotDefencesCheck = (CheckBox) v.findViewById(R.id.defences_CheckBox);
        shotCheckMateCheck = (CheckBox) v.findViewById(R.id.checkmate_CheckBox);
        shotPopShotCheck = (CheckBox) v.findViewById(R.id.pop_shot_CheckBox);

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ReviewFragment mFrag = ReviewFragment.getInstance();
                transaction.replace(R.id.main_container, mFrag).commit();

            }
        });

        sb1 = (SeekBar) v.findViewById(R.id.set_defence_SeekBar);
        sb1.setOnSeekBarChangeListener(this);

        //change this
        sb1.setProgress(defenceRating*20);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onExtraDataFragmentInteraction(uri);
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
    public void onPause() {
        saveData();
        super.onPause();
    }

    public static void clearFragment(){
        instance = new ExtraData();
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
        void onExtraDataFragmentInteraction(Uri uri);
    }
}
