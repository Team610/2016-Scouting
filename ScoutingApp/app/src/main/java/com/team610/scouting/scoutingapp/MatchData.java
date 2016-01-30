package com.team610.scouting.scoutingapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackw on 2016-01-18.
 */
public class MatchData {
    public String[] defenceTypes = new String[4];
    static MatchData instance;

    //MatchSetup
    public int selectedDefenceMatchSetup;
    public int match;
    public int team;




    //Auton
    public boolean spybot;
    public boolean scoredHighGoal;
    public boolean scoredLowGoal;
    public boolean placedCourtyard;
    public boolean endedCourtyard;
    public boolean endedNeutralZone;
    public boolean reachDefence;

    //Teleop

    public static int highGoalScores = 0;
    public static int lowGoalScores = 0;
    public static int courtyardScores = 0;
    public static int highGoalMisses = 0;
    public static int lowGoalMisses = 0;
    public static int courtyardMisses = 0;




    private MatchData(){

    }

    public static MatchData getInstance(){
        if(instance == null){
            instance = new MatchData();
        }
        return instance;
    }

    public static void updateTeleop(){
//        MainActivity.rootRef.child("init").setValue(highGoalScores);
//        MainActivity.rootRef.child("init").setValue(lowGoalScores);
//        MainActivity.rootRef.child("init").setValue(courtyardScores);
//        MainActivity.rootRef.child("init").setValue(highGoalMisses);
//        MainActivity.rootRef.child("init").setValue(lowGoalMisses);
//        MainActivity.rootRef.child("init").setValue(courtyardMisses);

        Map<String, Object> score = new HashMap<String, Object>();
        score.put("high goal scores", highGoalScores);
        score.put("low goal scores", lowGoalScores);

        MainActivity.rootRef.updateChildren(score);
    }

    public static void newMatch(){
        instance = new MatchData();
    }
}


