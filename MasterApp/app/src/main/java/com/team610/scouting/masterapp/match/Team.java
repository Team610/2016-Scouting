package com.team610.scouting.masterapp.match;

import java.util.Random;

public class Team {
    public int id;
    MatchData match;
    public String defences[] = new String[4];

    //Auton
    public boolean spybot;
    public boolean scoredHighGoal;
    public boolean scoredLowGoal;
    public boolean placedCourtyard ;
    public boolean endedCourtyard ;
    public boolean endedNeutralZone;
    public boolean reachDefence ;
    public String defenseCrossed;
    public boolean breach;
    public boolean capture;
    //public String defenseCrossed = "Lowbar";
    //Teleop
    public long highGoalScores;
    public long lowGoalScores;
    public long courtyardScores;
    public long highGoalMisses;
    public long lowGoalMisses;
    public long courtyardMisses;


    public boolean challenge = false;
    public boolean hang = false;
    public boolean shotFromCheckMate = false;
    public boolean shotFromDefences = false;
    public boolean shotFromPopShot = false;
    public boolean shotFromCorner = false;

    public long defensiveRating = 0;

    public String scoutName = "";
    public String comment = "";

    //unimplemented
    //public long defencecrosses[] = new long[5];


    public long defence1rating;
    public long defence2rating;
    public long defence3rating;
    public long defence4rating;
    public long defence5rating;

    public long defence1crosses;
    public long defence2crosses;
    public long defence3crosses;
    public long defence4crosses;
    public long defence5crosses;

    public String defence1;
    public String defence2;
    public String defence3;
    public String defence4;

    boolean[] defenceCrosses;


    public long fouls;



    public Team(int i, MatchData data) {
        //TODO load team from firebase
        this.match = data;
    }

    public double courtyardPercent(){
        if(courtyardMisses + courtyardScores == 0) return MatchFragment.numberOfWillsFriends;
        return ((10000 * courtyardScores)) / (courtyardMisses + courtyardScores) / 100D;
    }
    public double highGoalPercent(){
        if(highGoalScores + highGoalMisses == 0) return MatchFragment.numberOfWillsFriends;
        return ((10000 * highGoalScores)) / (highGoalMisses + highGoalScores) / 100D;
    }
    public double lowGoalPercent(){
        if(lowGoalMisses + lowGoalScores == 0) return MatchFragment.numberOfWillsFriends;
        return ((10000 * lowGoalScores)) / (lowGoalMisses + lowGoalScores) / 100D;
    }


}
