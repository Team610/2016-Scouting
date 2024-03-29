package com.team610.scouting.scoutingapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectDefences.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectDefences#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectDefences extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageButton chevaldefrise;
    ImageButton sallyport;
    ImageButton roughterrain;
    ImageButton rockwall;
    ImageButton portcullis;
    ImageButton drawbridge;
    ImageButton moat;
    ImageButton ramparts;

    TextView selectedDefenceOutput;

    int numOfDefence;
    String defencePicked;
    static SelectDefences instance;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectDefences() {
        // Required empty public constructor

    }

    public void openSelectDefences(){

        //this.numOfDefence = numOfDefence;
        this.numOfDefence = MatchData.getInstance().selectedDefenceMatchSetup;
        //selectedDefenceOutput.setText("Defence Position #: "+ numOfDefence);
    }

    public static SelectDefences getInstance() {
        if(instance == null){
            instance = new SelectDefences();
        }
        return instance;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectDefences.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectDefences newInstance(String param1, String param2) {
        SelectDefences fragment = new SelectDefences();
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
        View v = inflater.inflate(R.layout.fragment_select_defences, container, false);

        selectedDefenceOutput = (TextView) v.findViewById(R.id.defenceNumber_TextView);
        selectedDefenceOutput.setText("Defence Position #: "+ numOfDefence);




        portcullis = (ImageButton) v.findViewById(R.id.porticullis_ImageButton);
        portcullis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchData.getInstance().defenceTypes[numOfDefence-1] = "porticullis";
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment matchSetupFragment;

                matchSetupFragment = MatchSetup.getInstance();
                //MatchSetup.getInstance().openMatchSetup();
                transaction.replace(R.id.main_container, matchSetupFragment);
                //transaction.addToBackStack(null);


                transaction.commit();
            }
            });

        ramparts = (ImageButton) v.findViewById(R.id.ramparts_ImageButton);
        ramparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchData.getInstance().defenceTypes[numOfDefence-1] = "ramparts";
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment matchSetupFragment;

                matchSetupFragment = MatchSetup.getInstance();
                //MatchSetup.getInstance().openMatchSetup();
                transaction.replace(R.id.main_container, matchSetupFragment);
                //transaction.addToBackStack(null);


                transaction.commit();
            }
        });

        chevaldefrise = (ImageButton) v.findViewById(R.id.chevaldefrise_ImageButton);
        chevaldefrise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchData.getInstance().defenceTypes[numOfDefence-1] = "chevaldefrise";
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment matchSetupFragment;

                matchSetupFragment = MatchSetup.getInstance();
                //MatchSetup.getInstance().openMatchSetup();
                transaction.replace(R.id.main_container, matchSetupFragment);
                //transaction.addToBackStack(null);


                transaction.commit();
            }
        });

        rockwall = (ImageButton) v.findViewById(R.id.rockwall_ImageButton);
        rockwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchData.getInstance().defenceTypes[numOfDefence-1] = "rockwall";
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment matchSetupFragment;

                matchSetupFragment = MatchSetup.getInstance();
                //MatchSetup.getInstance().openMatchSetup();
                transaction.replace(R.id.main_container, matchSetupFragment);
                //transaction.addToBackStack(null);


                transaction.commit();
            }
        });

        roughterrain = (ImageButton) v.findViewById(R.id.roughterrain_ImageButton);
        roughterrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchData.getInstance().defenceTypes[numOfDefence-1] = "roughterrain";
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment matchSetupFragment;

                matchSetupFragment = MatchSetup.getInstance();
                //MatchSetup.getInstance().openMatchSetup();
                transaction.replace(R.id.main_container, matchSetupFragment);
                //transaction.addToBackStack(null);


                transaction.commit();
            }
        });

        moat = (ImageButton) v.findViewById(R.id.moat_ImageButton);
        moat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchData.getInstance().defenceTypes[numOfDefence-1] = "moat";
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment matchSetupFragment;

                matchSetupFragment = MatchSetup.getInstance();
                //MatchSetup.getInstance().openMatchSetup();
                transaction.replace(R.id.main_container, matchSetupFragment);
                //transaction.addToBackStack(null);


                transaction.commit();
            }
        });

        sallyport = (ImageButton) v.findViewById(R.id.sallyport_ImageButton);
        sallyport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchData.getInstance().defenceTypes[numOfDefence-1] = "sallyport";
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment matchSetupFragment;

                matchSetupFragment = MatchSetup.getInstance();
                //MatchSetup.getInstance().openMatchSetup();
                transaction.replace(R.id.main_container, matchSetupFragment);
                //transaction.addToBackStack(null);


                transaction.commit();
            }
        });

        drawbridge = (ImageButton) v.findViewById(R.id.drawbridge_ImageButton);
        drawbridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchData.getInstance().defenceTypes[numOfDefence-1] = "drawbridge";
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment matchSetupFragment;

                matchSetupFragment = MatchSetup.getInstance();
                //MatchSetup.getInstance().openMatchSetup();
                transaction.replace(R.id.main_container, matchSetupFragment);
                //transaction.addToBackStack(null);


                transaction.commit();
            }
        });



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSelectDefencesFragmentInteraction(uri);
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

    public void onClick(View v) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        Fragment matchSetupFragment;
//
//        //System.out.print("debug");
//
//        switch (v.getId()) {
//            case R.id.chevaldefrise_ImageButton:
//
//                MatchData.getInstance().defenceTypes[numOfDefence] = "chevaldefrise";
//
//
//                matchSetupFragment = MatchSetup.getInstance();
//                MatchSetup.getInstance().openMatchSetup();
//                transaction.replace(R.id.main_container, matchSetupFragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//
//                break;
//            case R.id.moat_ImageButton:
//                MatchData.getInstance().defenceTypes[numOfDefence] = "moat";
//
//
//                matchSetupFragment = MatchSetup.getInstance();
//                MatchSetup.getInstance().openMatchSetup();
//                transaction.replace(R.id.main_container, matchSetupFragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//                break;
//            case R.id.ramparts_ImageButton:
//                MatchData.getInstance().defenceTypes[numOfDefence] = "ramparts";
//
//
//                matchSetupFragment = MatchSetup.getInstance();
//                MatchSetup.getInstance().openMatchSetup();
//                transaction.replace(R.id.main_container, matchSetupFragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//                break;
//            case R.id.drawbridge_ImageButton:
//                MatchData.getInstance().defenceTypes[numOfDefence] = "drawbridge";
//
//
//                matchSetupFragment = MatchSetup.getInstance();
//                MatchSetup.getInstance().openMatchSetup();
//                transaction.replace(R.id.main_container, matchSetupFragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//                break;
//            case R.id.porticullis_ImageButton:
//                MatchData.getInstance().defenceTypes[numOfDefence] = "porticullis";
//
//
//                matchSetupFragment = MatchSetup.getInstance();
//                MatchSetup.getInstance().openMatchSetup();
//                transaction.replace(R.id.main_container, matchSetupFragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//                break;
//            case R.id.sallyport_ImageButton:
//                MatchData.getInstance().defenceTypes[numOfDefence] = "sallyport";
//
//
//                matchSetupFragment = MatchSetup.getInstance();
//                MatchSetup.getInstance().openMatchSetup();
//                transaction.replace(R.id.main_container, matchSetupFragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//                break;
//            case R.id.rockwall_ImageButton:
//                MatchData.getInstance().defenceTypes[numOfDefence] = "rockwall";
//
//
//                matchSetupFragment = MatchSetup.getInstance();
//                MatchSetup.getInstance().openMatchSetup();
//                transaction.replace(R.id.main_container, matchSetupFragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//                break;
//            case R.id.roughterrain_ImageButton:
//                MatchData.getInstance().defenceTypes[numOfDefence] = "roughterrain";
//
//
//                matchSetupFragment = MatchSetup.getInstance();
//                MatchSetup.getInstance().openMatchSetup();
//                transaction.replace(R.id.main_container, matchSetupFragment);
//                //transaction.addToBackStack(null);
//
//
//                transaction.commit();
//                break;
//
//
//        }
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
        void onSelectDefencesFragmentInteraction(Uri uri);
    }
}
