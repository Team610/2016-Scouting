package com.team610.scouting.scoutingapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    MatchData thisMatch = MatchData.getInstance();

    String drivenToDefence;
    int defenceNumDrivenTo;

    CheckBox reachedDefenceCheckBox;
    CheckBox spybotCheckBox;
    CheckBox scoreHighGoalCheckBox;
    CheckBox scoreLowGoalCheckBox;
    CheckBox droppedInCourtyardCheckBox;
    CheckBox endedInCourtyardCheckBox;
    CheckBox endedInNeutralCheckBox;

    //CheckBox s

    ImageButton defence1;
    ImageButton defence2;
    ImageButton defence3;
    ImageButton defence4;
    ImageButton lowBarDefence5;

    Button nextPage;
    Button backPage;



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

    public void saveData(){
        thisMatch.spybot = spybotCheckBox.isChecked();
        thisMatch.scoredHighGoal = scoreHighGoalCheckBox.isChecked();
        thisMatch.scoredLowGoal = scoreLowGoalCheckBox.isChecked();
        thisMatch.placedCourtyard = droppedInCourtyardCheckBox.isChecked();
        thisMatch.reachDefence = reachedDefenceCheckBox.isChecked();
        thisMatch.endedNeutralZone = endedInNeutralCheckBox.isChecked();
        thisMatch.endedCourtyard = endedInCourtyardCheckBox.isChecked();
        thisMatch.crossedDefence = drivenToDefence;
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

        for(int i = 0; i<4; i++){
            defences[i] = thisMatch.defenceTypes[i];
        }
        if(thisMatch.crossedDefence != null) {
            drivenToDefence = thisMatch.crossedDefence;
        }

        setDefenceButtonPics(defence1, 0, false);
        setDefenceButtonPics(defence2,1, false);
        setDefenceButtonPics(defence3,2, false);
        setDefenceButtonPics(defence4, 3, false);

        if(defenceNumDrivenTo == 5){
            lowBarDefence5.setImageResource(R.drawable.lowbarselected);
        }
        else{
            lowBarDefence5.setImageResource(R.drawable.lowbar);
        }
        if(defenceNumDrivenTo == 4){
            setDefenceButtonPics(defence1, 0, false);
            setDefenceButtonPics(defence2,1, false);
            setDefenceButtonPics(defence3,2, false);
            setDefenceButtonPics(defence4, 3, true);
        }
        else if(defenceNumDrivenTo == 3){
            setDefenceButtonPics(defence1, 0, false);
            setDefenceButtonPics(defence2,1, false);
            setDefenceButtonPics(defence3,2, true);
            setDefenceButtonPics(defence4, 3, false);
        }
        else if(defenceNumDrivenTo == 2){
            setDefenceButtonPics(defence1, 0, false);
            setDefenceButtonPics(defence2,1, true);
            setDefenceButtonPics(defence3,2, false);
            setDefenceButtonPics(defence4, 3, false);
        }
        else if(defenceNumDrivenTo == 1){
            setDefenceButtonPics(defence1, 0, true);
            setDefenceButtonPics(defence2,1, false);
            setDefenceButtonPics(defence3,2, false);
            setDefenceButtonPics(defence4, 3, false);
        }

    }

    public void setDefenceButtonPics(ImageButton defence , int i, boolean selected){
        if(selected){
            if (defences[i] != null) {
                if (defences[i].equals("porticullis")) {
                    defence.setImageResource(R.drawable.portcullisgreen);
                } else if (defences[i].equals("moat")) {
                    defence.setImageResource(R.drawable.moatgreen);
                } else if (defences[i].equals("ramparts")) {
                    defence.setImageResource(R.drawable.rampartsgreen);
                } else if (defences[i].equals("rockwall")) {
                    defence.setImageResource(R.drawable.rockwallgreen);
                } else if (defences[i].equals("roughterrain")) {
                    defence.setImageResource(R.drawable.roughterraingreen); //two of roughterrainselected for some reason, check this out
                } else if (defences[i].equals("sallyport")) {
                    defence.setImageResource(R.drawable.sallyportgreen);
                } else if (defences[i].equals("drawbridge")) {
                    defence.setImageResource(R.drawable.drawbridgegreen);
                } else if (defences[i].equals("chevaldefrise")) {
                    defence.setImageResource(R.drawable.chevaldefrisegreen);
                }
            }
        }else {
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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);

        defence1 = (ImageButton) rootView.findViewById(R.id.defence1_ImageButton);
        defence2 = (ImageButton) rootView.findViewById(R.id.defence2_ImageButton);
        defence3 = (ImageButton) rootView.findViewById(R.id.defence3_ImageButton);
        defence4 = (ImageButton) rootView.findViewById(R.id.defence4_ImageButton);
        lowBarDefence5 = (ImageButton) rootView.findViewById(R.id.lowBar_defence_ImageButton);


        reachedDefenceCheckBox = (CheckBox) rootView.findViewById(R.id.reachTheDefence_checkBox);
        spybotCheckBox = (CheckBox) rootView.findViewById(R.id.spybot_checkBox);
        scoreHighGoalCheckBox= (CheckBox) rootView.findViewById(R.id.highGoal_checkBox);
        scoreLowGoalCheckBox = (CheckBox) rootView.findViewById(R.id.lowGoal_checkBox);
        droppedInCourtyardCheckBox = (CheckBox) rootView.findViewById(R.id.courtyard_checkBox);
        endedInCourtyardCheckBox = (CheckBox) rootView.findViewById(R.id.ended_courtyard_checkBox);
        endedInNeutralCheckBox = (CheckBox) rootView.findViewById(R.id.neutralZone_CheckBox);

        nextPage = (Button) rootView.findViewById(R.id.next_Button);
        backPage = (Button) rootView.findViewById(R.id.back_Button);

        defence1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(drivenToDefence !=null && !drivenToDefence.equals(defences[0])  || drivenToDefence == null) {
                    drivenToDefence = defences[0];
                    colourPickedDefence(1);
                    defenceNumDrivenTo = 1;
                    reachedDefenceCheckBox.setChecked(false);
                }else{
                    drivenToDefence = null;
                    colourPickedDefence(0);
                    defenceNumDrivenTo = 0;
                }
            }

        });
        defence2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drivenToDefence !=null && !drivenToDefence.equals(defences[1])  || drivenToDefence == null) {
                    drivenToDefence = defences[1];
                    colourPickedDefence(2);
                    defenceNumDrivenTo = 2;
                    reachedDefenceCheckBox.setChecked(false);
                }else{
                    drivenToDefence = null;
                    colourPickedDefence(0);
                    defenceNumDrivenTo = 0;
                }
            }

        });

        defence3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drivenToDefence !=null && !drivenToDefence.equals(defences[2])  || drivenToDefence == null) {
                    drivenToDefence = defences[2];
                    colourPickedDefence(3);
                    defenceNumDrivenTo = 3;
                    reachedDefenceCheckBox.setChecked(false);
                }else{
                    drivenToDefence = null;
                    colourPickedDefence(0);
                    defenceNumDrivenTo = 0;
                }


            }

        });

        defence4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (drivenToDefence != null && !drivenToDefence.equals(defences[3]) || drivenToDefence == null) {
                    drivenToDefence = defences[3];
                    colourPickedDefence(4);
                    defenceNumDrivenTo = 4;
                    reachedDefenceCheckBox.setChecked(false);
                } else {
                    drivenToDefence = null;
                    colourPickedDefence(0);
                    defenceNumDrivenTo = 0;
                }

            }

        });
        lowBarDefence5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drivenToDefence != null && !drivenToDefence.equals("lowbar") || drivenToDefence == null) {
                    drivenToDefence = "lowbar";
                    colourPickedDefence(5);
                    defenceNumDrivenTo = 5;
                    reachedDefenceCheckBox.setChecked(false);
                } else {
                    drivenToDefence = null;
                    colourPickedDefence(0);
                    defenceNumDrivenTo = 0;
                }
            }

        });

        nextPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                TeleopFragment mFrag = TeleopFragment.getInstance();
                transaction.replace(R.id.main_container,mFrag).commit();

            }

        });
        backPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                MatchSetup mFrag = MatchSetup.getInstance();
                transaction.replace(R.id.main_container,mFrag).commit();

            }

        });








        openAutonFragment();



        // Inflate the layout for this fragment





        reachedDefenceCheckBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (reachedDefenceCheckBox.isChecked()){
                    drivenToDefence = null;
                    defenceNumDrivenTo = 0;
                    colourPickedDefence(0);
                }

            }

        });
        //delete this
        //MatchData.updateAuto();




        return rootView;
    }


    //need to fix
    public void colourPickedDefence(int i){

        if(i == 1){
            //defence1.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
            //defence1.setColorFilter(Color.CYAN, PorterDuff.Mode.DST_OVER);
            //defence1.setBackgroundColor(Color.CYAN);
            setDefenceButtonPics(defence1, 0, true);
        }else {
            //defence1.clearColorFilter();
            setDefenceButtonPics(defence1, 0, false);
        }
        if(i == 2){
            //defence2.setColorFilter(Color.CYAN, PorterDuff.Mode.DST_OVER);
            setDefenceButtonPics(defence2,1,true);
        }else {
            //defence2.clearColorFilter();
            setDefenceButtonPics(defence2, 1, false);
        }
        if(i == 3){
            setDefenceButtonPics(defence3, 2, true);
            //defence3.setColorFilter(Color.CYAN, PorterDuff.Mode.DST_OVER);
        }else {
            setDefenceButtonPics(defence3, 2, false);
            //defence3.clearColorFilter();
        }
        if(i == 4){
            setDefenceButtonPics(defence4, 3, true);
            //defence4.setColorFilter(Color.CYAN, PorterDuff.Mode.DST_OVER);
        }else {
            setDefenceButtonPics(defence4, 3, false);
            //defence4.clearColorFilter();
        }
        if(i == 5){
            //lowBarDefence5.setColorFilter(Color.CYAN, PorterDuff.Mode.DST_OVER);
            lowBarDefence5.setImageResource(R.drawable.lowbarselected);
        }else {
            lowBarDefence5.setImageResource(R.drawable.lowbar);
            //lowBarDefence5.clearColorFilter();
        }



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

    @Override
    public void onPause() {
        saveData();
        super.onPause();
    }

    public static void clearFragment(){
        instance = new AutoFragment();
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
