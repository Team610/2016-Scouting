package com.team610.scouting.masterapp.team;

import com.team610.scouting.masterapp.Defence;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tate on 2016-01-30.
 */
public class TeamData {


    public TeamData(String id) {
        this.id = Integer.valueOf(id);
        defences = new HashMap<>();
        comments = new HashMap<>();
        matches = new ArrayList<>();
    }

    public int id;
    //Long array stores rating and times crossed, index 0 for rating, index 1 for crosses
    public HashMap<Defence, Double[]> defences;


    public long autonScore = 0;
    public long defenseScore = 0;
    //TODO
    public int hangs;
    public int challenges;


    public int breaches;
    public int captures;

    public boolean shotFromCourtyard,
            shotFromCheckMate,
            shotFromDefences,
            shotFromCorner;
    public long fouls;

    public double defensiveRating;
    public int defensePlayed;

    public long highGoalShots;
    public long highGoalMisses;
    public long lowGoalShots;
    public long lowGoalMisses;
    public long courtyardDrops;
    //TODO courtyard drops


    public ArrayList<String> matches;
    //TODO
    public HashMap<String, String> comments; //Key is match number, object is comment

    /*
    public double courtyardPercent(){
        if(courtyardMisses + courtyardScores == 0) return -1;
        return ((10000 * courtyardScores)) / (courtyardMisses + courtyardScores) / 100D;
    }
 */

    public int hangingPercentage() {
        return (int) (100 * (((double) hangs) / matches.size()));
    }

    public int challengePercentage() {
        return (int) (100 * (((double) challenges) / matches.size()));
    }


    public double avgAutonScore() {
        return (1000 * autonScore) / matches.size() / 1000D;
    }

    public double avgDefenceScore() {
        return (1000 * defenseScore) / matches.size() / 1000D;
    }

    public double avgHighGoalScore() {
        return (1000 * highGoalShots) / matches.size() / 1000D;
    }

    public int highGoalPercentage() {
        return (int) (100 * (((double) highGoalShots) / (highGoalShots + highGoalMisses)));
    }


    public double avgLowGoalScore() {
        return (1000 * lowGoalShots) / matches.size() / 1000D;
    }

    public int lowGoalPercentage() {
        return (int) (100 * (((double) lowGoalShots) / (lowGoalShots + lowGoalMisses)));
    }


    public double avgCourtyardDrops() {
        return (1000 * courtyardDrops) / matches.size() / 1000D;
    }

    public int id() {
        return id;
    }

}

