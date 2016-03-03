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
    }

    //Returns a data for a team on id
    public static TeamData getTeamData(int id) {
        //TODO load teamData
        return null;
    }

    public int id;
    //Long array stores rating and times crossed, index 0 for rating, index 1 for crosses
    public HashMap<Defence, Double[]> defences;


    public double avgAutonScore = 0;
    public double avgDefenseScore = 0;

    public double avgHighGoalScore = 0;
    public double highGoalPercentage = 0;
    public double avgLowGoalScore = 0;
    public double lowGoalPercentage = 0;


    public long fouls;

    public long highGoalShots;
    public long highGoalMisses;
    public long lowGoalShots;
    public long lowGoalMisses;


    public ArrayList<String> matches;

    public ArrayList<String> comments;


}
