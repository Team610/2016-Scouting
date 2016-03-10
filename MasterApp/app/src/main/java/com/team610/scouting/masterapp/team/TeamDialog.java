package com.team610.scouting.masterapp.team;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.team610.scouting.masterapp.R;
import com.team610.scouting.masterapp.match.MatchData;
import com.team610.scouting.masterapp.match.Team;


public class TeamDialog extends DialogFragment {


    int index;

    TextView matchNum;
    TextView teamNum;
    TextView[] defences = new TextView[4];
    TextView autonCrossedDefence;
    TextView reachedDefence;
    TextView spybot;
    TextView autonBall;
    TextView autonRobotEnd;
    TextView[] teleopDefences = new TextView[5];
    TextView highGoal;
    TextView lowGoal;
    TextView courtyard;
    TextView fouls;
    TextView hang;
    TextView challenge;
    TextView breach;
    TextView capture;
    TextView checkmate;
    TextView defenceShots;
    TextView popShots;
    TextView cornerShots;
    TextView defensiveRating;
    TextView scoutName;
    TextView comment;


    MatchData match;


    // TODO: Rename and change types of parameters

    public static TeamDialog newInstance(int num) {
        TeamDialog f = new TeamDialog();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("num");
        setStyle(STYLE_NO_TITLE,0);
    }





    //static ReviewFragment instance;

    private OnFragmentInteractionListener mListener;


//    public static ReviewFragment getInstance(){
//
//        if(instance == null){
//            instance = new ReviewFragment();
//        }
//
//        return instance;
//    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_review, container, false);

        match = MatchData.instance;
        Team team = match.teams[index];


//        backPage = (Button) v.findViewById(R.id.back_Button);
//
//        submitButton = (Button) v.findViewById(R.id.send_Button);

        matchNum = (TextView) v.findViewById(R.id.matchNum_review_TextView);
        matchNum.setText("Match: "+ match.matchNumber);

        teamNum = (TextView) v.findViewById(R.id.team_num_review_TextView);
        teamNum.setText("Team: " + team.id);

        defences[0] = (TextView) v.findViewById(R.id.defence1_selection_TextView);
        defences[0].setText("1. " + team.defence1);

        defences[1] = (TextView) v.findViewById(R.id.defence2_selection_TextView);
        defences[1].setText("2. " + team.defence2);

        defences[2] = (TextView) v.findViewById(R.id.defence3_selection_TextView);
        defences[2].setText("3. " + team.defence3);

        defences[3] = (TextView) v.findViewById(R.id.defence4_selection_TextView);
        defences[3].setText("4. " + team.defence4);

        autonCrossedDefence = (TextView) v.findViewById(R.id.auton_crossed_defence_TextView);
        autonCrossedDefence.setText("Defence Crossed: " + team.defenseCrossed);

        reachedDefence = (TextView) v.findViewById(R.id.reached_defence_TextView);
        reachedDefence.setText("Reached Defence: " + team.reachDefence);

        spybot = (TextView) v.findViewById(R.id.spybot_TextView);
        spybot.setText("Spybot: " + team.spybot);

        String ballLocation = "";
        if(team.scoredHighGoal){
            ballLocation = "High Goal";
        }
        if(team.scoredLowGoal){
            if(!ballLocation.equals("")){
                ballLocation = "error";
            }
            else{
                ballLocation = "Low Goal";
            }
        }
        if(team.placedCourtyard){
            if(!ballLocation.equals("")){
                ballLocation = "error";
            }
            else{
                ballLocation = "Courtyard Drop";
            }
        }

        autonBall = (TextView) v.findViewById(R.id.auton_ball_TextView);
        autonBall.setText("Ball: " + ballLocation);


        String robotLocation = "";
        if(team.endedCourtyard){
            robotLocation = "Courtyard";
        }
        if(team.endedNeutralZone){
            if(!robotLocation.equals("")){
                robotLocation = "error";
            }
            else{
                robotLocation = "Neutral Zone";
            }
        }
        autonRobotEnd = (TextView) v.findViewById(R.id.auton_robot_location_TextView);
        autonRobotEnd.setText("Robot Ended: " + robotLocation);


        teleopDefences[0] = (TextView) v.findViewById(R.id.teleop_defence1_TextView);
        teleopDefences[0].setText("1. " + team.defence1crosses + " crosses, " + chooseDefenceRating(team.defence1rating));

        teleopDefences[1] = (TextView) v.findViewById(R.id.teleop_defence2_TextView);
        teleopDefences[1].setText("2. " + team.defence2crosses+" crosses, " + chooseDefenceRating(team.defence2rating));

        teleopDefences[2] = (TextView) v.findViewById(R.id.teleop_defence3_TextView);
        teleopDefences[2].setText("3. " + team.defence3crosses+" crosses, " + chooseDefenceRating(team.defence3rating));

        teleopDefences[3] = (TextView) v.findViewById(R.id.teleop_defence4_TextView);
        teleopDefences[3].setText("4. " + team.defence4crosses+" crosses, " + chooseDefenceRating(team.defence4rating));

        teleopDefences[4] = (TextView) v.findViewById(R.id.teleop_defence5_TextView);
        teleopDefences[4].setText("5. " + team.defence5crosses+" crosses, " + chooseDefenceRating(team.defence5rating));

        highGoal = (TextView) v.findViewById(R.id.high_goal_teleop_TextView);
        highGoal.setText("HighGoal - Scores: " + team.highGoalScores+", Misses: " + team.highGoalMisses);

        lowGoal = (TextView) v.findViewById(R.id.low_goal_teleop_TextView);
        lowGoal.setText("LowGoal - Scores: " + team.lowGoalScores+", Misses: " + team.lowGoalMisses);

        courtyard = (TextView) v.findViewById(R.id.courtyard_drop_teleop_TextView);
        courtyard.setText("Courtyard - Scores: " + team.courtyardScores+", Misses: " + team.courtyardMisses);

        fouls = (TextView) v.findViewById(R.id.foul_TextView);
        fouls.setText("Fouls: " +team.fouls);

        hang = (TextView) v.findViewById(R.id.hang_TextView);
        hang.setText("Hang: " + team.hang);

        challenge = (TextView) v.findViewById(R.id.challenge_TextView);
        challenge.setText("Challenge: " + team.challenge);

//        breach = (TextView) v.findViewById(R.id.breach_TextView);
//        breach.setText("Breach: " + match.breach);
//
//        capture = (TextView) v.findViewById(R.id.capture_TextView);
//        capture.setText("Capture: " + match.capture);

        checkmate = (TextView) v.findViewById(R.id.checkmate_TextView);
        checkmate.setText("Checkmate Shots: " + team.shotFromCheckMate);

        defenceShots = (TextView) v.findViewById(R.id.defence_shots_TextView);
        defenceShots.setText("Defence Shots: " +  team.shotFromDefences);

        popShots = (TextView) v.findViewById(R.id.pop_shots_TextView);
        popShots.setText("Courtyard Shots: " + team.shotFromPopShot);

        cornerShots = (TextView) v.findViewById(R.id.corner_shot_TextView);
        cornerShots.setText("Corner Shots: " + team.shotFromCorner);

        defensiveRating = (TextView) v.findViewById(R.id.defence_rating_TextView);
        defensiveRating.setText("Defensive Rating: " + team.defensiveRating);

        scoutName = (TextView) v.findViewById(R.id.scout_name_TextView);
        scoutName.setText("Scout: " + team.scoutName);

        comment = (TextView) v.findViewById(R.id.comment_TextView);
        comment.setText(team.comment);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event

    public static String chooseDefenceRating(long rate){
        int r = (int) rate;
        switch (r){
            case 0:
                return "white";
            //break;
            case 1:
                return "green";
            //break;
            case 2:
                return "yellow";
            //break;
            case 3:
                return "red";
            //break;
            default:
                return "error";
            //break;
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
        void onReviewFragmentInteraction(Uri uri);
    }

}
