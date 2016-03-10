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
    static String competition = "GTE";



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


    //Extra Data
    public boolean capture = false;
    public boolean breach = false;
    public boolean challenge = false;
    public boolean hang = false;
    public String scoutName = "";
    public String comment = "";
    public int defensiveRating = 0; //0 means no defence
    public boolean shotFromDefences = false;
    public boolean shotFromCheckMate = false;
    public boolean shotFromPopShot = false;
    public boolean shotFromCorner = false;


    //FireBase Refs
    static Firebase competitionRef;

    static Firebase matchRef;
    static Firebase teamRef;

    static Firebase matchSetupRef;
    static Firebase autoRef;
    static Firebase teleRef;
    static Firebase miscRef;






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
        matchRef = competitionRef.child("match" + instance.match);

        teamRef = matchRef.child("" + instance.team);

        matchSetupRef = teamRef.child("matchSetup");
        autoRef = teamRef.child("auto");
        teleRef = teamRef.child("teleop");
        miscRef = teamRef.child("misc");



    }
    public static void updateMatchSetup(){

        createBranches();
        Map<String, Object> defences = new HashMap<String, Object>();
        defences.put("defence1", instance.defenceTypes[0]);
        defences.put("defence2", instance.defenceTypes[1]);
        defences.put("defence3", instance.defenceTypes[2]);
        defences.put("defence4", instance.defenceTypes[3]);

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
        score.put("highGoalScores", instance.highGoalScores);
        score.put("lowGoalScores", instance.lowGoalScores);
        score.put("courtyardScores", instance.courtyardScores);
        score.put("highGoalMisses", instance.highGoalMisses);
        score.put("lowGoalMisses", instance.lowGoalMisses);
        score.put("courtyardMisses", instance.courtyardMisses);

        /*public static int porticullisCross = 0;
        public static int moatCross = 0;
        public static int rampartsCross = 0;
        public static int rockwallCross = 0;
        public static int roughterrainCross = 0;
        public static int sallyportCross = 0;
        public static int drawbridgeCross = 0;
        public static int chevaldefriseCross  = 0; */

        score.put("defence1crosses", instance.defence1Cross);
        score.put("defence2crosses", instance.defence2Cross);
        score.put("defence3crosses", instance.defence3Cross);
        score.put("defence4crosses", instance.defence4Cross);
        score.put("defence5crosses", instance.defence5Cross);

        score.put("defence1rating", instance.defence1Rating);
        score.put("defence2rating", instance.defence2Rating);
        score.put("defence3rating", instance.defence3Rating);
        score.put("defence4rating", instance.defence4Rating);
        score.put("defence5rating", instance.defence5Rating);

        score.put("fouls", instance.fouls);


        teleRef.updateChildren(score);
    }

    public static void updateAuto(){

        Map<String, Object> auto = new HashMap<String, Object>();

        auto.put("spybot", instance.spybot);
        auto.put("scoredHighGoal",instance.scoredHighGoal);
        auto.put("scoredLowGoal", instance.scoredLowGoal);
        auto.put("placedCourtyard", instance.placedCourtyard);
        auto.put("endedCourtyard", instance.endedCourtyard);
        auto.put("endedNeutralZone", instance.endedNeutralZone);
        auto.put("reachDefence", instance.reachDefence);
        auto.put("defenseCrossed", instance.crossedDefence);
        autoRef.updateChildren(auto);
    }

    public static void updateMisc(){

        Map<String, Object> misc = new HashMap<String, Object>();

//        public boolean capture = false;
//        public boolean breach = false;
//        public boolean challenge = false;
//        public boolean hang = false;
//        public String scoutName = "";
//        public String comment = "";
//        public int defensiveRating = 0; //0 means no defence
//        public boolean shotFromDefences = false;
//        public boolean shotFromCheckMate = false;
//        public boolean shotFromPopShot = false;

        misc.put("capture", instance.capture);
        misc.put("breach", instance.breach);
        misc.put("challenge", instance.challenge);
        misc.put("hang", instance.hang);
        misc.put("scoutName", instance.scoutName);
        misc.put("comment", instance.comment);
        misc.put("defensiveRating", instance.defensiveRating);
        misc.put("shotFromDefences", instance.shotFromDefences);
        misc.put("shotFromCheckMate", instance.shotFromCheckMate);
        misc.put("shotFromPopShot",instance.shotFromPopShot);
        misc.put("shotFromCorner",instance.shotFromCorner);
        miscRef.updateChildren(misc);
    }







    public static void newMatch(){
        updateMatchSetup();
        updateTeleop();
        updateAuto();
        updateMisc();



        instance = new MatchData();

        AutoFragment.clearFragment();
        ExtraData.clearFragment();
        //InitialFragment;
        MatchSetup.clearFragment();
        ReviewFragment.clearFragment();
        TeleopFragment.clearFragment();


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


