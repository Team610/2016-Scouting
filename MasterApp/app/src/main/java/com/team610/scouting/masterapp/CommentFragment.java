package com.team610.scouting.masterapp;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.team610.scouting.masterapp.team.TeamData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


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

    public TableLayout tl;

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
        tl = (TableLayout) rootView.findViewById(R.id.main_table);


        //Table

        //


        String[] teams = MainActivity.teams.keySet().toArray(new String[MainActivity.teams.keySet().size()]);
        Arrays.sort(teams);
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

                        String teamNumPicked = String.valueOf(adapterView
                                .getItemAtPosition(position));

                        tl.removeAllViews();

                        TeamData a = MainActivity.teams.get(teamNumPicked);
                        ArrayList<String> matchNums = new ArrayList<String>(Arrays.asList(a.comments.keySet().toArray(new String[a.comments.keySet().size()])));
                        Collections.sort(matchNums);
                        HashMap comments = a.comments;

                        TextView[] textArray = new TextView[matchNums.size()];
                        TableRow[] tr_headA = new TableRow[matchNums.size()];


                        Context c = getActivity().getApplicationContext();

                        TableRow tr_head = new TableRow(c);
                        tr_head.setId(generateViewId());
                        tr_head.setBackgroundColor(Color.GRAY);        // part1
                        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                                TableLayout.LayoutParams.WRAP_CONTENT,
                                TableLayout.LayoutParams.WRAP_CONTENT));

                        TextView label_hello = new TextView(c);
                        label_hello.setId(generateViewId());
                        label_hello.setText("Match Number");
                        label_hello.setTextColor(Color.WHITE);          // part2
                        label_hello.setPadding(5, 5, 5, 5);


                        tr_head.addView(label_hello);// add the column to the table row here

                        TextView label_android = new TextView(c);    // part3
                        label_android.setId(generateViewId());// define id that must be unique
                        label_android.setText("Comment"); // set the text for the header
                        label_android.setTextColor(Color.WHITE); // set the color
                        label_android.setPadding(5, 5, 5, 5); // set the padding (if required)
                        tr_head.addView(label_android); // add the column to the table row here

                        tl.addView(tr_head, new TableLayout.LayoutParams(
                                TableLayout.LayoutParams.WRAP_CONTENT,                    //part4
                                TableLayout.LayoutParams.WRAP_CONTENT));

                        //


                        for (int i = 0; i < matchNums.size(); i++) {
//Create the tablerows
                            // Context c = getActivity().getApplicationContext();
                            tr_headA[i] = new TableRow(c);
                            tr_headA[i].setId(i + 1);
                            tr_headA[i].setBackgroundColor(Color.GRAY);
                            tr_headA[i].setLayoutParams(new TableLayout.LayoutParams(
                                    TableLayout.LayoutParams.WRAP_CONTENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));

                            // Here create the TextView dynamically

                            textArray[i] = new TextView(c);
                            textArray[i].setId(i + 111);
                            textArray[i].setText(matchNums.get(i));
                            textArray[i].setTextColor(Color.WHITE);
                            textArray[i].setPadding(5, 5, 5, 5);
                            tr_headA[i].addView(textArray[i]);


                            textArray[i] = new TextView(c);
                            textArray[i].setId(i + 99);
                            String comment = a.comments.get("" + matchNums.get(i));
                            final int CHAR_LIMIT = 50;
                            for (int k = 0; comment.length() - k * CHAR_LIMIT > CHAR_LIMIT; k++) {
                                int lastIndex = comment.substring(0, 40 * (k + 1)).lastIndexOf(' ');
                                comment = comment.substring(0, lastIndex) + "\n" + comment.substring(lastIndex + 1);
                            }
                            textArray[i].setText(comment);
                            Log.d("comment", a.comments.get("" + matchNums.get(i)));
                            textArray[i].setTextColor(Color.WHITE);
                            textArray[i].setPadding(5, 5, 5, 5);
                            tr_headA[i].addView(textArray[i]); // add the column to the table row here

                            tl.addView(tr_headA[i], new TableLayout.LayoutParams(
                                    TableLayout.LayoutParams.WRAP_CONTENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));

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

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public void updateViewsFromThe6ix() {

    }
}
