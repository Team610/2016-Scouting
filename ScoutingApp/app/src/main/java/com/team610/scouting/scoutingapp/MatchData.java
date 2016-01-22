package com.team610.scouting.scoutingapp;

/**
 * Created by jackw on 2016-01-18.
 */
public class MatchData {
    String[] defenceTypes = new String[5];
    static MatchData instance;

    //MatchSetup
    int selectedDefence;



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


