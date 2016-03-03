package com.team610.scouting.masterapp.match;

import android.app.AlertDialog;
import android.os.Handler;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.team610.scouting.masterapp.MainActivity;

import java.util.Random;

/**
 * Created by Tate on 2016-01-30.
 */
public class MatchData {

    Team[] teams;
    int matchNumber;



    public MatchData(int match) {
        this.matchNumber = match;
        teams = new Team[6];
        for (int i = 0; i < 6; i++) {
            teams[i] = new Team(i, this);
        }

        updateDatagen();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MainActivity.mFrag instanceof MatchFragment)
                    try {
                        ((MatchFragment)MainActivity.mFrag).updateViewsFromThe6ix();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }catch (NullPointerException e){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.mFrag.getActivity());
                        dialog.setTitle("Error");
                        dialog.setMessage("Data took too long to get");
                    }
            }

}, 2000);//7.5 second timeout


        }
    public void updateDatagen(){
        MainActivity.rootRef.child("GTC").child("match" + matchNumber).addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                int i = -1;
                for (DataSnapshot team : snapshot.getChildren()) {
                    i++;
                    teams[i].id = Integer.valueOf(team.getKey());
                    for (DataSnapshot mode : team.getChildren()) {
                        for(DataSnapshot data : mode.getChildren()){
                            try {
                                Log.d(data.getKey(), "" + data.getValue().getClass());

                                Team.class.getField(data.getKey()).set(teams[i],data.getValue());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {

            }
        });

    }

}

class Team {
    Random rand = new Random();
    public int id;
    MatchData match;
    public String defences[] = new String[4];

    //Auton
    public boolean spybot = rand.nextBoolean();
    public boolean scoredHighGoal = rand.nextBoolean();
    public boolean scoredLowGoal = rand.nextBoolean();
    public boolean placedCourtyard = rand.nextBoolean();
    public boolean endedCourtyard = rand.nextBoolean();
    public boolean endedNeutralZone = rand.nextBoolean();
    public boolean reachDefence = rand.nextBoolean();
    public String defenceCrossed;
    //public String defenseCrossed = "Lowbar";
    //Teleop
    public long highGoalScores = 3;
    public long lowGoalScores = rand.nextInt(100);
    public long courtyardScores = rand.nextInt(100);
    public long highGoalMisses = rand.nextInt(100);
    public long lowGoalMisses = rand.nextInt(100);
    public long courtyardMisses = rand.nextInt(100);
    public double courtyardPercent = ((10000 * courtyardScores)) / (courtyardMisses + courtyardScores) / 100D;
    public double highGoalPercent = ((10000 * highGoalScores)) / (highGoalMisses + highGoalScores) / 100D;
    public double lowGoalPercent = ((10000 * lowGoalScores)) / (lowGoalMisses + lowGoalScores) / 100D;
    public int points = 0;
    public int totalCrosses = 120;
    public String capture = "Scale";

    public boolean challenge = false;
    public boolean hang = false;

    //unimplemented
    public long defencecrosses[] = new long[5];


    public long defense1rating;
    public long defense2rating;
    public long defense3rating;
    public long defense4rating;
    public long defense5rating;

    public String defence1;
    public String defence2;
    public String defence3;
    public String defence4;

    boolean[] defenceCrosses;


    public long fouls;



    public Team(int i, MatchData data) {
        //TODO load team from firebase
        this.match = data;

        calcTotalPoints();
    }


    private void calcTotalPoints() {
        //Auton
        //TODO add crossin defense
        //points +=

        points = 0;

        //Auton
        points += autoScore();

        //Teleop
        points += getDefenceCrossScore(1);
        points += getDefenceCrossScore(2);
        points += getDefenceCrossScore(3);
        points += getDefenceCrossScore(4);
        points += getDefenceCrossScore(5);

        points += highGoalScores * 5 + lowGoalScores * 2;//TODO ADD DEFENSES

        if(challenge){
            points += 5;
        }
        else if (hang) {
            points += 15;
        }




        //Post Match
        //  points +=
    }


    private int getDefenceCrossScore(int num){
        long a = getDefenceCrossScore(num-1);
        int score = 10;
        if(defences[num-1].equals(defenceCrossed)){
            if (a == 0) {
                score = 0;
            } else if (a == 1) {
                score = 5;
            }
        }
        else {
            if (a == 0) {
                score = 0;
            } else if (a == 1) {
                score = 5;
            } else {
                score = 10;
            }
        }


        return score;

    }
    //TODO
    private int teleOp(){
        int teleOpScore = 0;
        //if()
        return 0;
    }

    private int autoScore(){
        int autoPoints = 0;
        if(defenceCrossed == null || defenceCrossed.equals("")){
            autoPoints+=10;
        }
        if(scoredHighGoal){
            autoPoints += 10;
        }
        if(scoredLowGoal){
            autoPoints += 5;
        }
        if(reachDefence){
            autoPoints += 2;
        }
        return autoPoints;
    }



}
