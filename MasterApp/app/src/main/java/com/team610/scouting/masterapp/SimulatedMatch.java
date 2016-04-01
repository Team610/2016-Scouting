package com.team610.scouting.masterapp;

import com.team610.scouting.masterapp.team.TeamData;

/**
 * Created by Tate on 2016-04-01.
 */
public class SimulatedMatch {

    TeamData[] blue;
    TeamData[] red;

    boolean redWin, redBreach, blueBreach;

    double blueCapture, redCapture;

    public SimulatedMatch(TeamData[] blue, TeamData[] red, boolean redWin, boolean redBreach, boolean blueBreach, double blueCapture, double redCapture) {
        this.blue = blue;
        this.red = red;
        this.redWin = redWin;
        this.redBreach = redBreach;
        this.blueBreach = blueBreach;
        this.blueCapture = blueCapture;
        this.redCapture = redCapture;
    }
}
