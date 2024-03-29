package com.team610.scouting.masterapp.match;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.team610.scouting.masterapp.R;
import com.team610.scouting.masterapp.ScoutingFragment;
import com.team610.scouting.masterapp.team.TeamDialog;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends ScoutingFragment {
    // TODO: Rename parameter arguments, choose names that match

    private MatchData data;



    private int matchNum;
    // public static String teamNum;

    public MatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(String match) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        args.putString("MATCH", match);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().getString("MATCH") != null) {
                matchNum = Integer.valueOf(getArguments().getString("MATCH"));
                loadMatchData();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_match, container, false);
        View auton = root.findViewById(R.id.match_auton_layout),
                teleop = root.findViewById(R.id.match_teleop_layout),
                defences = root.findViewById(R.id.match_defence_layout);

        auton.setVisibility(View.GONE);
        defences.setVisibility(View.GONE);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
      //  mListener = null;
    }

    public void onMenuTap(int id) {
        if (id == R.id.action_matchNumber) {
            createMatchDialog();

            //TODO LOAD DATA FROM MATCH
        } else if (id == R.id.action_team) {
            createTeamChoiceDialog();

        } else {
            View auton = getActivity().findViewById(R.id.match_auton_layout),
                    teleop = getActivity().findViewById(R.id.match_teleop_layout),
               defences = getActivity().findViewById(R.id.match_defence_layout);

            defences.setVisibility(View.GONE);
            teleop.setVisibility(View.GONE);
            auton.setVisibility(View.GONE);
            if (id == R.id.action_auton) {
                auton.setVisibility(View.VISIBLE);
            } else if (id == R.id.action_teleop) {
                teleop.setVisibility(View.VISIBLE);
            } else if (id == R.id.action_defences) {
                defences.setVisibility(View.VISIBLE);
            }
        }
    }

    private void createTeamDialog(int i) {
        TeamDialog dialog = TeamDialog.newInstance(i);
        dialog.show(getFragmentManager(), "Team Data");
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

    public void createMatchDialog() {
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

    public void createTeamChoiceDialog() {
        //  ListAdapter adapter =  new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,teams);
        if (data == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Team")
                .setItems(getStringArray(data.teams), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  teamNum = teams[which];
                        createTeamDialog(which);
                    }
                });
        //Set up negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private String[] getStringArray(Team[] teams) {
        String[] toReturn = new String[6];
        for (int i = 0; i < 6; i++) {
            toReturn[i] = teams[i].id + "";
        }
        return toReturn;
    }

    public void loadMatchData() {
        ActionMenuItemView item = (ActionMenuItemView) getActivity().findViewById(R.id.action_matchNumber);
        item.setTitle("Match # " + matchNum);
        Toast.makeText(getActivity(), "Loading Match Data", Toast.LENGTH_LONG).show();
        data = new MatchData(matchNum);

    }

    public void updateViewsFromThe6ix() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("UPDATE");

        for (int i = 0; i < 6; i++) {
            System.out.println(i);
            Team t = data.teams[i];
            //Auton
            ViewGroup autonView = (ViewGroup) getView().findViewById(R.id.class.getField("match_auton_team" + (i + 1)).getInt(R.id.class));
            TableLayout autonTable = (TableLayout) ((ViewGroup) autonView.getChildAt(0)).getChildAt(0);
            ((TextView) autonTable.findViewById(R.id.match_auton_team_num)).setText(t.id + "");
            ((TextView) autonTable.findViewById(R.id.reaches_defense)).setText(t.reachDefence + "");
            ((TextView) autonTable.findViewById(R.id.spybot)).setText(t.spybot + "");
            ((TextView) autonTable.findViewById(R.id.robotLocation)).setText(t.endedCourtyard + "");
            String s = t.scoredHighGoal ? "High Goal" : t.scoredLowGoal ? "Low Goal" : t.placedCourtyard ? "Courtyard" : "Neutral Zone";
            ((TextView) autonTable.findViewById(R.id.ballLocation)).setText(s);
            ((TextView) autonTable.findViewById(R.id.defenseCrossedText)).setText(t.defenseCrossed);

            //Tele-OP
            ViewGroup teleView = (ViewGroup) getView().findViewById(R.id.class.getField("match_teleop_team" + (i + 1)).getInt(R.id.class));
            TableLayout teleTable = (TableLayout) ((ViewGroup) teleView.getChildAt(0)).getChildAt(0);
            ((TextView) teleTable.findViewById(R.id.match_teleop_team_num)).setText(t.id + "");
            ((TextView) teleTable.findViewById(R.id.match_teleop_court_goals)).setText(t.courtyardScores + "");
            ((TextView) teleTable.findViewById(R.id.match_teleop_court_attempts)).setText(t.courtyardScores + t.courtyardMisses + "");
            ((TextView) teleTable.findViewById(R.id.match_teleop_court_percent)).setText(t.courtyardPercent() + "%");
            ((TextView) teleTable.findViewById(R.id.match_teleop_high_goals)).setText(t.highGoalScores + "");
            ((TextView) teleTable.findViewById(R.id.match_teleop_high_attempts)).setText(t.highGoalScores + t.highGoalMisses + "");
            ((TextView) teleTable.findViewById(R.id.match_teleop_high_percent)).setText(t.highGoalPercent() + "%");
            ((TextView) teleTable.findViewById(R.id.match_teleop_low_goals)).setText(t.lowGoalScores + "");
            ((TextView) teleTable.findViewById(R.id.match_teleop_low_attempts)).setText(t.lowGoalScores + t.lowGoalMisses + "");
            ((TextView) teleTable.findViewById(R.id.match_teleop_low_percent)).setText(t.lowGoalPercent() + "%");
            ((TextView) teleTable.findViewById(R.id.teleop_alliance)).setText(t.alliance);


            //Defences
            ViewGroup defenceView = (ViewGroup) getView().findViewById(R.id.class.getField("match_defence_team" + (i + 1)).getInt(R.id.class));
            ((TextView) defenceView.findViewById(R.id.defence_team_num)).setText(t.id + "");
            TableLayout defenceTable = (TableLayout) defenceView.findViewById(R.id.defence_table);
            for(int j = 1; j <= 4; j++){
                TableRow row = (TableRow) defenceTable.getChildAt(j);
                String defence = (String) Team.class.getField("defence"+j).get(t);
                long rating = Team.class.getField("defence"+j+"rating").getLong(t);
                long crosses = Team.class.getField("defence"+j+"crosses").getLong(t);
                row.setBackgroundColor(rating == 1 ? Color.GREEN : rating == 2 ? Color.YELLOW : rating == 3 ? Color.RED : Color.GRAY);
                ((TextView) row.getChildAt(0)).setText(defence);
                ((TextView) row.getChildAt(1)).setText(crosses + "");
            }
            TableRow row = (TableRow) defenceTable.getChildAt(5);
            long rating = Team.class.getField("defence5rating").getLong(t);
            long crosses = Team.class.getField("defence5crosses").getLong(t);
            row.setBackgroundColor(rating == 1 ? Color.GREEN : rating == 2 ? Color.YELLOW : rating == 3 ? Color.RED : Color.GRAY);
            ((TextView) row.getChildAt(1)).setText(crosses + "");
        }
        Toast.makeText(getActivity(), "Match Data Loaded", Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
