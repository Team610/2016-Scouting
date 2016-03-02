package com.team610.scouting.masterapp.team;

import java.util.ArrayList;

/**
 * Created by Tate on 2016-01-30.
 */
public class TeamData {

    public TeamData(String id) {
        this.id = Integer.valueOf(id);
    }

    //Returns a data for a team on id
    public static TeamData getTeamData(int id) {
        //TODO load teamData
        return null;
    }

    public int id;



    public double avgPoints = 0;
    public double avgAutonScore = 0;
    public double avgDefenseScore= 0;
    public double avgHighGoalScore = 0;
    public double highGoalPercentage = 0;
    public double avgLowGoalScore = 0;
    public double lowGoalPercentage = 0;

    public ArrayList<String> matches;

    public ArrayList<String> comments;


}
