package com.team610.scouting.masterapp.match;

/**
 * Created by Tate on 2016-01-30.
 */
public class MatchData {

    Team[] teams;
    int matchNumber;

    public MatchData(int match){
        this.matchNumber = match;
    }

}

class Team {
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
}
