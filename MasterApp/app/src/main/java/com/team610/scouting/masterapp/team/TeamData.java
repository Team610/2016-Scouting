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


    public double autonScore = 0;
    public double defenseScore = 0;
    //TODO
    public int hangs;
    public int challenges;


    public int breaches;
    public int captures;

    public boolean shotFromCourtyard,
            shotFromCheckMate,
            shotFromDefences;
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


    public int hangingPercentage() {
        return (int) (100 * (((double) hangs) / matches.size()));
    }
    public int challengePercentage() {
        return (int) (100 * (((double) challenges) / matches.size()));
    }
    public int avgAutonScore() {
        return (int) (autonScore / matches.size());
    }
    public int avgDefenceScore() {
        return (int) (defenseScore / matches.size());
    }
    public int avgHighGoalScore() {
        return (int) highGoalShots / matches.size();
    }
    public int highGoalPercentage() {
        return (int) (100 * (((double) highGoalShots) / (highGoalShots + highGoalMisses)));
    }
    public int avgLowGoalScore() {
        return (int) lowGoalShots / matches.size();
    }
    public int lowGoalPercentage() {
        return (int) (100 * (((double) lowGoalShots) / (lowGoalShots + lowGoalMisses)));
    }
    public int avgCourtyardDrops() {
        return (int) ( courtyardDrops) / matches.size();
    }
    public int id(){
        return id;
    }







}
