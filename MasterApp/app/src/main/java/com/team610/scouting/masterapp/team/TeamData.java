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
        comments = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public int id;
    //Long array stores rating and times crossed, index 0 for rating, index 1 for crosses
    public HashMap<Defence, Double[]> defences;


    public double avgAutonScore = 0;
    public double avgDefenseScore = 0;
//TODO
    public double avgHighGoalScore = 0;
    public double highGoalPercentage = 0;
    public double avgLowGoalScore = 0;
    public double lowGoalPercentage = 0;
//TODO
    public double hangingPercentage;
    public double challengePercentage;


    public long fouls;

    public long highGoalShots;
    public long highGoalMisses;
    public long lowGoalShots;
    public long lowGoalMisses;
    //TODO
    public double avgCourtyardDrops;
    //TODO
    public ArrayList<String> matches;
    //TODO
    public ArrayList<String> comments;


}
