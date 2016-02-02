package com.team610.scouting.scoutingapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchSetup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchSetup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchSetup extends Fragment implements View.OnClickListener {
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
    int match = 0;
    int team = 0;

    ImageButton[] defenceButtons;
    EditText matchNum;
    EditText teamNum;



    public MatchSetup() {
        // Required empty public constructor



       // openMatchSetup();
    }

    public void openMatchSetup(){



        for(int i = 0; i<4; i++){
            defences[i] = thisMatch.defenceTypes[i];
        }
        for(int i = 0; i < 4; i++){
            if(defences[i] != null) {
                if (defences[i].equals("porticullis")) {
                    defenceButtons[i].setBackgroundResource(R.drawable.portcullis);
                } else if (defences[i].equals("moat")) {
                    defenceButtons[i].setBackgroundResource(R.drawable.moat);
                } else if (defences[i].equals("ramparts")) {
                    defenceButtons[i].setBackgroundResource(R.drawable.ramparts);
                } else if (defences[i].equals("rockwall")) {
                    defenceButtons[i].setBackgroundResource(R.drawable.rockwall);
                } else if (defences[i].equals("roughterrain")) {
                    defenceButtons[i].setBackgroundResource(R.drawable.roughterrain);
                } else if (defences[i].equals("sallyport")) {
                    defenceButtons[i].setBackgroundResource(R.drawable.sallyport);
                } else if (defences[i].equals("drawbridge")) {
                    defenceButtons[i].setBackgroundResource(R.drawable.drawbridge);
                } else if (defences[i].equals("chevaldefrise")) {
                    defenceButtons[i].setBackgroundResource(R.drawable.chevaldefrise);
                }
            }
        }

    }

    public void saveData(){
        for(int i= 0; i < 4; i++){
            thisMatch.defenceTypes[i] = defences[i];
        }

        //Integer.parseInt("ps");
        if(matchNum != null && matchNum.getText() != null) {
            if (matchNum.getText().toString().length() > 0) {
                match = Integer.parseInt(matchNum.getText().toString());
            }
        }
        if(teamNum != null && teamNum.getText() != null) {
            if (teamNum.getText().toString().length() > 0) {
                team = Integer.parseInt(teamNum.getText().toString());
            }
        }

        thisMatch.match = match;
        thisMatch.team = team;

//        if(matchNum.getText().toString().length() > 0) {
//            thisMatch.match = Integer.parseInt(matchNum.getText().toString());
//        }
//        if(teamNum.getText().length() >0) {
//            thisMatch.team = Integer.parseInt(teamNum.getText().toString());
//        }

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



        //openMatchSetup();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_match_setup, container, false);

        //thisMatch = MatchData.getInstance();

        defenceButtons = new ImageButton[] {(ImageButton) v.findViewById(R.id.defence1_ImageButton),
                (ImageButton) v.findViewById(R.id.defence2_ImageButton),
                (ImageButton) v.findViewById(R.id.defence3_ImageButton),
                (ImageButton) v.findViewById(R.id.defence4_ImageButton)};
        openMatchSetup();


        //COME BACK TO THIS
        //weird issue of losing the edit text on first try
        //setText fixes this, but causes 0s at beginning, also annoying
        matchNum = (EditText) v.findViewById(R.id.matchNum_EditText);
        //matchNum.setText(""+ thisMatch.match);
        teamNum = (EditText) v.findViewById(R.id.teamNum_EditText);
        //teamNum.setText(""+thisMatch.team);



//        if( matchNum.getText().toString().length() > 0) {
//            match = Integer.parseInt(matchNum.getText().toString());
//        }
//        if(teamNum.getText().toString().length() > 0) {
//            team = Integer.parseInt(teamNum.getText().toString());
//        }

            //selectedDefence = i+1;
            //MatchData.getInstance().selectedDefenceMatchSetup = selectedDefence;
            defenceButtons[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MatchData.getInstance().selectedDefenceMatchSetup = 1;
                    FragmentTransaction transaction = getFragmentManager()
                            .beginTransaction();

                    SelectDefences fragment = SelectDefences.getInstance();
                    fragment.openSelectDefences();

                    transaction.replace(R.id.main_container, fragment);
                    //transaction.addToBackStack(null);


                    transaction.commit();


                }

            });
            defenceButtons[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MatchData.getInstance().selectedDefenceMatchSetup = 2;
                    FragmentTransaction transaction = getFragmentManager()
                            .beginTransaction();

                    SelectDefences fragment = SelectDefences.getInstance();
                    fragment.openSelectDefences();

                    transaction.replace(R.id.main_container, fragment);
                    //transaction.addToBackStack(null);


                    transaction.commit();


                }

            });
            defenceButtons[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MatchData.getInstance().selectedDefenceMatchSetup = 3;
                    FragmentTransaction transaction = getFragmentManager()
                            .beginTransaction();

                    SelectDefences fragment = SelectDefences.getInstance();
                    fragment.openSelectDefences();

                    transaction.replace(R.id.main_container, fragment);
                    //transaction.addToBackStack(null);


                    transaction.commit();


                }

            });
            defenceButtons[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MatchData.getInstance().selectedDefenceMatchSetup = 4;
                    FragmentTransaction transaction = getFragmentManager()
                            .beginTransaction();

                    SelectDefences fragment = SelectDefences.getInstance();
                    fragment.openSelectDefences();

                    transaction.replace(R.id.main_container, fragment);
                    //transaction.addToBackStack(null);


                    transaction.commit();


                }

            });


        //Debug
        //System.out.print("Where is this printing");

        return v;

    }

    @Override
    public void onPause() {
        saveData();
        super.onPause();
    }


    //yo

    public void onClick(View v) {

        //Debug
        //System.out.print("clicked in Match Setup");
//        defenceButtons[0].setVisibility(View.INVISIBLE);
//
//
//        switch (v.getId()) {
//            case R.id.defence1_ImageButton:
//                selectedDefence = 1;
//
//                FragmentTransaction transaction = getFragmentManager()
//                        .beginTransaction();
//
//                SelectDefences fragment = SelectDefences.getInstance();
//                fragment.openSelectDefences(selectedDefence);
//
//                transaction.replace(R.id.main_container, fragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//
//                break;
//            case R.id.defence2_ImageButton:
//                selectedDefence = 2;
//                FragmentTransaction transaction2 = getFragmentManager()
//                        .beginTransaction();
//
//                SelectDefences fragment2 = SelectDefences.getInstance();
//                fragment2.openSelectDefences(selectedDefence);
//
//                transaction2.replace(R.id.main_container, fragment2);
//                //transaction.addToBackStack(null);
//
//
//                transaction2.commit();
//
//                break;
//            case R.id.defence3_ImageButton:
//                selectedDefence = 3;
//                FragmentTransaction transaction3 = getFragmentManager()
//                        .beginTransaction();
//
//                SelectDefences fragment3 = SelectDefences.getInstance();
//                fragment3.openSelectDefences(selectedDefence);
//                transaction3.replace(R.id.main_container, fragment3);
//                //transaction.addToBackStack(null);
//
//
//                transaction3.commit();
//
//                break;
//            case R.id.defence4_ImageButton:
//                selectedDefence = 4;
//                FragmentTransaction transaction4 = getFragmentManager()
//                        .beginTransaction();
//
//                SelectDefences fragment4 = SelectDefences.getInstance();
//
//                fragment4.openSelectDefences(selectedDefence);
//                transaction4.replace(R.id.main_container, fragment4);
//                //transaction.addToBackStack(null);
//
//
//                transaction4.commit();
//
//                break;
//        }
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onMatchSetupFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
        void onMatchSetupFragmentInteraction(Uri uri);
    }


}
