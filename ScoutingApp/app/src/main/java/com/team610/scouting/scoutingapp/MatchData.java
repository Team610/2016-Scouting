package com.team610.scouting.scoutingapp;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackw on 2016-01-18.
 */
public class MatchData {

    public int selectedDefenceMatchSetup;
    static MatchData instance;

    //MatchSetup
    public String[] defenceTypes = new String[4];
    public int match;
    public int team = 0;

    //Change
    static String competition = "GTC";



    //Auton
    public boolean spybot;
    public boolean scoredHighGoal;
    public boolean scoredLowGoal;
    public boolean placedCourtyard;
    public boolean endedCourtyard;
    public boolean endedNeutralZone;
    public boolean reachDefence;
    public String crossedDefence;

    //Teleop

    //scores in teleop
    public int highGoalScores = 0;
    public int lowGoalScores = 0;
    public int courtyardScores = 0;
    public int highGoalMisses = 0;
    public int lowGoalMisses = 0;
    public int courtyardMisses = 0;

    //defences crossed in teleop
    public int defence1Cross = 0;
    public int defence2Cross = 0;
    public int defence3Cross = 0;
    public int defence4Cross = 0;
    public int defence5Cross = 0;

    public int fouls = 0;


    //Ratings of defenses
    //0 didn't cross
    //1 green - really good
    //2 yellow - ok
    //3 red - really bad

    public int defence1Rating = 0;
    public int defence2Rating = 0;
    public int defence3Rating = 0;
    public int defence4Rating = 0;
    public int defence5Rating = 0;



    //FireBase Refs
    static Firebase competitionRef;

    static Firebase matchRef;
    static Firebase teamRef;

    static Firebase matchSetupRef;
    static Firebase autoRef;
    static Firebase teleRef;






    private MatchData(){




    }

    public static MatchData getInstance(){
        if(instance == null){
            instance = new MatchData();
        }
        return instance;
    }

    public static void createBranches(){
        competitionRef = MainActivity.rootRef.child(competition);
        matchRef = competitionRef.child("Match " + instance.match);

        teamRef = matchRef.child(" " + instance.team);

        matchSetupRef = teamRef.child("Match Setup");
        autoRef = teamRef.child("Auto");
        teleRef = teamRef.child("Teleop");


    }
    public static void updateMatchSetup(){

        createBranches();
        Map<String, Object> defences = new HashMap<String, Object>();
        defences.put("Defence 1", instance.defenceTypes[0]);
        defences.put("Defence 2", instance.defenceTypes[1]);
        defences.put("Defence 3", instance.defenceTypes[2]);
        defences.put("Defence 4", instance.defenceTypes[3]);

        matchSetupRef.updateChildren(defences);


    }





    public static void updateTeleop(){
//        MainActivity.rootRef.child("init").setValue(highGoalScores);
//        MainActivity.rootRef.child("init").setValue(lowGoalScores);
//        MainActivity.rootRef.child("init").setValue(courtyardScores);
//        MainActivity.rootRef.child("init").setValue(highGoalMisses);
//        MainActivity.rootRef.child("init").setValue(lowGoalMisses);
//        MainActivity.rootRef.child("init").setValue(courtyardMisses);

        Map<String, Object> score = new HashMap<String, Object>();
        score.put("high goal scores", instance.highGoalScores);
        score.put("low goal scores", instance.lowGoalScores);
        score.put("courtyard scores", instance.courtyardScores);
        score.put("high goal misses", instance.highGoalMisses);
        score.put("low goal misses", instance.lowGoalMisses);
        score.put("courtyard misses", instance.courtyardMisses);

        /*public static int porticullisCross = 0;
        public static int moatCross = 0;
        public static int rampartsCross = 0;
        public static int rockwallCross = 0;
        public static int roughterrainCross = 0;
        public static int sallyportCross = 0;
        public static int drawbridgeCross = 0;
        public static int chevaldefriseCross  = 0; */

        score.put("Defence 1 crosses", instance.defence1Cross);
        score.put("Defence 2 crosses", instance.defence2Cross);
        score.put("Defence 3 crosses", instance.defence3Cross);
        score.put("Defence 4 crosses", instance.defence4Cross);
        score.put("Defence 5 crosses", instance.defence5Cross);

        score.put("Defence 1 rating", instance.defence1Rating);
        score.put("Defence 2 rating", instance.defence2Rating);
        score.put("Defence 3 rating", instance.defence3Rating);
        score.put("Defence 4 rating", instance.defence4Rating);
        score.put("Defence 5 rating", instance.defence5Rating);



        teleRef.updateChildren(score);
    }

    public static void updateAuto(){

        Map<String, Object> auto = new HashMap<String, Object>();

        auto.put("spybot", instance.spybot);
        auto.put("Scored High Goal",instance.scoredHighGoal);
        auto.put("Scored Low Goal", instance.scoredLowGoal);
        auto.put("Placed Courtyard", instance.placedCourtyard);
        auto.put("Ended Courtyard", instance.endedCourtyard);
        auto.put("Ended Neutral Zone", instance.endedNeutralZone);
        auto.put("Reach Defence", instance.reachDefence);
        autoRef.updateChildren(auto);


    }





    public static void newMatch(){

        instance = new MatchData();

        //reset values
//        instance.spybot = false;
//        instance.scoredHighGoal = false;
//        instance.scoredLowGoal = false;
//        instance.placedCourtyard = false;
//        instance.endedCourtyard = false;
//        endedNeutralZone = false;
//        reachDefence = false;
//
//        highGoalScores = 0;
//        lowGoalScores = 0;
//        courtyardScores = 0;
//        highGoalMisses = 0;
//        lowGoalMisses = 0;
//        courtyardMisses = 0;
//
//        match = 0;
//        team = 0;




    }
}


