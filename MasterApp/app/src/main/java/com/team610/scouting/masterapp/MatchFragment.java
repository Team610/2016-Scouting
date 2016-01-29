package com.team610.scouting.masterapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public MatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(String param1, String param2) {
        MatchFragment fragment = new MatchFragment();
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
        View root = inflater.inflate(R.layout.fragment_match, container, false);
        View auton = root.findViewById(R.id.match_auton_layout),
                teleop = root.findViewById(R.id.match_teleop_layout);
        auton.setVisibility(View.VISIBLE);
        teleop.setVisibility(View.GONE);
        return root;
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

    public void onMenuTap(int id) {
        if (id == R.id.action_matchNumber) {
            createDialog();

            //TODO LOAD DATA FROM MATCH
        } else {
            View auton = getActivity().findViewById(R.id.match_auton_layout),
                    teleop = getActivity().findViewById(R.id.match_teleop_layout),
                    post = getActivity().findViewById(R.id.match_post_layout);
            post.setVisibility(View.GONE);
            teleop.setVisibility(View.GONE);
            auton.setVisibility(View.GONE);
            if (id == R.id.action_auton) {

                auton.setVisibility(View.VISIBLE);
            } else if (id == R.id.action_teleop) {
                teleop.setVisibility(View.VISIBLE);

            } else if (id == R.id.action_postMatch) {

                post.setVisibility(View.VISIBLE);
            }
            //possible WIP fix to the hack invisble views
//            LayoutInflater inflater = LayoutInflater.from(getActivity());
//            View inflatedLayout;
//            if (id == R.id.action_auton) {
//                inflatedLayout= inflater.inflate(R.layout.match_auton, null, false);
//            } else if (id == R.id.action_teleop) {
//                inflatedLayout= inflater.inflate(R.layout.match_teleop, null, false);
//            } else if (id == R.id.action_postMatch) {
//                inflatedLayout= inflater.inflate(R.layout.match_post, null, false);
//            }
        }
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
    private int matchNum;
    public void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Match");

        // Set up the input
        final EditText input = new EditText(getActivity());
        // Specify the type to number
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                matchNum = Integer.valueOf(input.getText().toString());
                loadMatchData();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void loadMatchData(){
        ActionMenuItemView item = (ActionMenuItemView) getActivity().findViewById(R.id.action_matchNumber);
        item.setTitle("Match # " + matchNum);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
