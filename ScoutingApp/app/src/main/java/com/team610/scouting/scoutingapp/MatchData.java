package com.team610.scouting.scoutingapp;

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

    public static void newMatch(){
        instance = new MatchData();
    }
}


