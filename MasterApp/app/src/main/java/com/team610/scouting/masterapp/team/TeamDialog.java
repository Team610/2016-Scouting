package com.team610.scouting.masterapp.team;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.team610.scouting.masterapp.R;
import com.team610.scouting.masterapp.match.MatchData;
import com.team610.scouting.masterapp.match.Team;


public class TeamDialog extends DialogFragment {



    int matchNumber;
    int teamNumber;
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

    Button submitButton;
    Button backPage;

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


        backPage = (Button) v.findViewById(R.id.back_Button);

        submitButton = (Button) v.findViewById(R.id.send_Button);
        matchNum = (TextView) v.findViewById(R.id.matchNum_review_TextView);
        matchNum.setText("Match: "+matchNumber);

        teamNum = (TextView) v.findViewById(R.id.team_num_review_TextView);
        teamNum.setText("Team: " + teamNumber);

        defences[0] = (TextView) v.findViewById(R.id.defence1_selection_TextView);
        defences[0].setText("1. " + team.defence1);

        defences[1] = (TextView) v.findViewById(R.id.defence2_selection_TextView);
        defences[1].setText("2. " + team.defence2);

        defences[2] = (TextView) v.findViewById(R.id.defence3_selection_TextView);
        defences[2].setText("3. " + team.defence3);

        defences[3] = (TextView) v.findViewById(R.id.defence4_selection_TextView);
        defences[3].setText("4. " + team.defence4);

        autonCrossedDefence = (TextView) v.findViewById(R.id.auton_crossed_defence_TextView);
        autonCrossedDefence.setText("Defence Crossed: " + team.crossedDefence);

        reachedDefence = (TextView) v.findViewById(R.id.reached_defence_TextView);
        reachedDefence.setText("Reached Defence: " + team.reachDefence);

        spybot = (TextView) v.findViewById(R.id.spybot_TextView);
        spybot.setText("Spybot: " + team.spybot);

        String ballLocation = "";
        if(match.scoredHighGoal){
            ballLocation = "High Goal";
        }
        if(match.scoredLowGoal){
            if(!ballLocation.equals("")){
                ballLocation = "error";
            }
            else{
                ballLocation = "Low Goal";
            }
        }
        if(match.placedCourtyard){
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
        if(match.endedCourtyard){
            robotLocation = "Courtyard";
        }
        if(match.endedNeutralZone){
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
        teleopDefences[0].setText("1. " + match.defence1Cross + " crosses, " + chooseDefenceRating(match.defence1Rating));

        teleopDefences[1] = (TextView) v.findViewById(R.id.teleop_defence2_TextView);
        teleopDefences[1].setText("2. " + match.defence2Cross+" crosses, " + chooseDefenceRating(match.defence2Rating));

        teleopDefences[2] = (TextView) v.findViewById(R.id.teleop_defence3_TextView);
        teleopDefences[2].setText("3. " + match.defence3Cross+" crosses, " + chooseDefenceRating(match.defence3Rating));

        teleopDefences[3] = (TextView) v.findViewById(R.id.teleop_defence4_TextView);
        teleopDefences[3].setText("4. " + match.defence4Cross+" crosses, " + chooseDefenceRating(match.defence4Rating));

        teleopDefences[4] = (TextView) v.findViewById(R.id.teleop_defence5_TextView);
        teleopDefences[4].setText("5. " + match.defence5Cross+" crosses, " + chooseDefenceRating(match.defence5Rating));

        highGoal = (TextView) v.findViewById(R.id.high_goal_teleop_TextView);
        highGoal.setText("HighGoal - Scores: " + match.highGoalScores+", Misses: " + match.highGoalMisses);

        lowGoal = (TextView) v.findViewById(R.id.low_goal_teleop_TextView);
        lowGoal.setText("LowGoal - Scores: " + match.lowGoalScores+", Misses: " + match.lowGoalMisses);

        courtyard = (TextView) v.findViewById(R.id.courtyard_drop_teleop_TextView);
        courtyard.setText("Courtyard - Scores: " + match.courtyardScores+", Misses: " + match.courtyardMisses);

        fouls = (TextView) v.findViewById(R.id.foul_TextView);
        fouls.setText("Fouls: " +match.fouls);

        hang = (TextView) v.findViewById(R.id.hang_TextView);
        hang.setText("Hang: " + match.hang);

        challenge = (TextView) v.findViewById(R.id.challenge_TextView);
        challenge.setText("Challenge: " + match.challenge);

//        breach = (TextView) v.findViewById(R.id.breach_TextView);
//        breach.setText("Breach: " + match.breach);
//
//        capture = (TextView) v.findViewById(R.id.capture_TextView);
//        capture.setText("Capture: " + match.capture);

        checkmate = (TextView) v.findViewById(R.id.checkmate_TextView);
        checkmate.setText("Checkmate Shots: " + match.shotFromCheckMate);

        defenceShots = (TextView) v.findViewById(R.id.defence_shots_TextView);
        defenceShots.setText("Defence Shots: " +  match.shotFromDefences);

        popShots = (TextView) v.findViewById(R.id.pop_shots_TextView);
        popShots.setText("Courtyard Shots: " + match.shotFromPopShot);

        cornerShots = (TextView) v.findViewById(R.id.corner_shot_TextView);
        cornerShots.setText("Corner Shots: " + match.shotFromCorner);

        defensiveRating = (TextView) v.findViewById(R.id.defence_rating_TextView);
        defensiveRating.setText("Defensive Rating: " + match.defensiveRating);

        scoutName = (TextView) v.findViewById(R.id.scout_name_TextView);
        scoutName.setText("Scout: " + match.scoutName);

        comment = (TextView) v.findViewById(R.id.comment_TextView);
        comment.setText(match.comment);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                MatchData.newMatch();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                MatchSetup mFrag = MatchSetup.getInstance();
                transaction.replace(R.id.main_container, mFrag).commit();

            }


        });
        backPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ExtraData mFrag = ExtraData.getInstance();
                transaction.replace(R.id.main_container,mFrag).commit();

            }

        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onReviewFragmentInteraction(uri);
        }
    }

    public static String chooseDefenceRating(int rate){
        switch (rate){
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

    public void sendData(){

        MatchData.newMatch();

    }

    public static void clearFragment(){
        instance = new ReviewFragment();
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
