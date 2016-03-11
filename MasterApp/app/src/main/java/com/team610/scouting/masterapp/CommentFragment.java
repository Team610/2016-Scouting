package com.team610.scouting.masterapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.team610.scouting.masterapp.team.TeamData;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CommentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CommentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentFragment extends ScoutingFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TableLayout t1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CommentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommentFragment newInstance(String param1, String param2) {
        CommentFragment fragment = new CommentFragment();
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

        //start
        View rootView = inflater.inflate(R.layout.fragment_comment,
                container, false);
        String[] teams = MainActivity.teams.keySet().toArray(new String[MainActivity.teams.keySet().size()]);

        ListAdapter theAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, teams);

        ListView theListView = (ListView) rootView.findViewById(R.id.list_view);

        theListView.setAdapter(theAdapter);

        theListView
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int position, long l) {

                        String teamPicked = "Team picked: "
                                + String.valueOf(adapterView
                                .getItemAtPosition(position));
                        String positionchosen = " " + position;
                        Toast.makeText(getActivity(),
                                teamPicked + positionchosen, Toast.LENGTH_SHORT)
                                .show();


//                        teamNumber = (TextView) parentActivity
//                                .findViewById(R.id.team_number_TV);



                        switch (position) {
                            case 0:

//                                Object[][] data = PhpRequest.getTable(610);
//
//
//                                teamNumber.setText("Team Number: " + data[0][0]);
//
//
//                                for (Object[] row : data) {
//
//                                    Log.d("Rows: ", ("" + row.length));
//
//                                    for (Object a : row) {
//
//                                        Log.d("ARRAY", (String) a);
//
//                                    }
//
//                                }

                        }

                    }

                });

       // parentActivity = (MainActivity) getActivity();
        return rootView;
        //end
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCommentFragmentInteraction(uri);
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
        void onCommentFragmentInteraction(Uri uri);
    }

    public void updateViewsFromThe6ix(){

    }
}
