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


