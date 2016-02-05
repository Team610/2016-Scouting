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
    public static String[] defenceTypes = new String[4];
    public static int match;
    public static int team = 0;

    //Change
    static String competition = "GTC";



    //Auton
    public static boolean spybot;
    public static boolean scoredHighGoal;
    public static boolean scoredLowGoal;
    public static boolean placedCourtyard;
    public static boolean endedCourtyard;
    public static boolean endedNeutralZone;
    public static boolean reachDefence;
    public static String crossedDefence;

    //Teleop

    //scores in teleop
    public static int highGoalScores = 0;
    public static int lowGoalScores = 0;
    public static int courtyardScores = 0;
    public static int highGoalMisses = 0;
    public static int lowGoalMisses = 0;
    public static int courtyardMisses = 0;

    //defences crossed in teleop
    public static int porticullisCross = 0;
    public static int moatCross = 0;
    public static int rampartsCross = 0;
    public static int rockwallCross = 0;
    public static int roughterrainCross = 0;
    public static int sallyportCross = 0;
    public static int drawbridgeCross = 0;
    public static int chevaldefriseCross  = 0;



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
        matchRef = competitionRef.child("Match " + match);

        teamRef = matchRef.child(" " + team);

        matchSetupRef = teamRef.child("Match Setup");
        autoRef = teamRef.child("Auto");
        teleRef = teamRef.child("Teleop");


    }
    public static void updateMatchSetup(){

        createBranches();
        Map<String, Object> defences = new HashMap<String, Object>();
        defences.put("Defence 1", defenceTypes[0]);
        defences.put("Defence 2", defenceTypes[1]);
        defences.put("Defence 3", defenceTypes[2]);
        defences.put("Defence 4", defenceTypes[3]);

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
        score.put("high goal scores", highGoalScores);
        score.put("low goal scores", lowGoalScores);
        score.put("courtyard scores", courtyardScores);
        score.put("high goal misses", highGoalMisses);
        score.put("low goal misses", lowGoalMisses);
        score.put("courtyard misses", courtyardMisses);

        /*public static int porticullisCross = 0;
        public static int moatCross = 0;
        public static int rampartsCross = 0;
        public static int rockwallCross = 0;
        public static int roughterrainCross = 0;
        public static int sallyportCross = 0;
        public static int drawbridgeCross = 0;
        public static int chevaldefriseCross  = 0; */

        score.put("moat crosses", moatCross);
        score.put("porticullis crosses", porticullisCross);
        score.put("ramparts crosses", rampartsCross);
        score.put("rockwall crosses", rockwallCross);
        score.put("roughterrain crosses", roughterrainCross);
        score.put("sallyport crosses", sallyportCross);
        score.put("drawbridge crosses", drawbridgeCross);
        score.put("chevaldefrise", chevaldefriseCross);


        teleRef.updateChildren(score);
    }

    public static void updateAuto(){

        Map<String, Object> auto = new HashMap<String, Object>();

        auto.put("spybot", spybot);
        auto.put("Scored High Goal",scoredHighGoal);
        auto.put("Scored Low Goal", scoredLowGoal);
        auto.put("Placed Courtyard", placedCourtyard);
        auto.put("Ended Courtyard", endedCourtyard);
        auto.put("Ended Neutral Zone", endedNeutralZone);
        auto.put("Reach Defence", reachDefence);
        autoRef.updateChildren(auto);


    }





    public static void newMatch(){

        instance = new MatchData();

        //reset values
        spybot = false;
        scoredHighGoal = false;
        scoredLowGoal = false;
        placedCourtyard = false;
        endedCourtyard = false;
        endedNeutralZone = false;
        reachDefence = false;

        highGoalScores = 0;
        lowGoalScores = 0;
        courtyardScores = 0;
        highGoalMisses = 0;
        lowGoalMisses = 0;
        courtyardMisses = 0;

        match = 0;
        team = 0;




    }
}


