package com.team610.scouting.masterapp.match;

import android.widget.Toast;

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
            teams[i] = new Team(i, match);
        }
    }

}

class Team {
    public int match;
    Random rand = new Random();
    public int id = rand.nextInt(1000);


    //Auton
    public boolean spybot = rand.nextBoolean();
    public boolean scoredHighGoal = rand.nextBoolean();
    public boolean scoredLowGoal = rand.nextBoolean();
    public boolean placedCourtyard = rand.nextBoolean();
    public boolean endedCourtyard = rand.nextBoolean();
    public boolean endedNeutralZone = rand.nextBoolean();
    public boolean reachDefence = rand.nextBoolean();
    public String defenseCrossed = "Lowbar";
    //Teleop
    public int highGoalScores = 3;
    public int lowGoalScores = rand.nextInt(100);
    public int courtyardScores = rand.nextInt(100);
    public int highGoalMisses = rand.nextInt(100);
    public int lowGoalMisses = rand.nextInt(100);
    public int courtyardMisses = rand.nextInt(100);
    public double courtyardPercent = ((10000*courtyardScores)) / (courtyardMisses + courtyardScores) / 100D;
    public double highGoalPercent =((10000*highGoalScores)) / (highGoalMisses + highGoalScores) / 100D;
    public double lowGoalPercent = ((10000*lowGoalScores)) / (lowGoalMisses + lowGoalScores) / 100D;
    public int points = 20;
    public int totalCrosses = 120;
    public String capture = "Scale";
    public Team(int i, int match) {
        //TODO load team from firebase
        loadData();
        this.match = match;
        calcTotalPoints();
    }

    private void loadData() {
        //updateData();

    }

    private void calcTotalPoints() {
        //Auton
        //TODO add crossin defense
        //points +=

        //Teleop
        points += highGoalScores * 5 + lowGoalScores * 2;//TODO ADD DEFENSES

        //Post Match
      //  points +=
    }


   //start
   public void updateData() {
       MainActivity.rootRef.child("GTC").child("Match 16").child("Teleop").child("high goal scores").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot snapshot) {

               if (snapshot.getValue() == null) {


               } else {
                   highGoalScores = (Integer) snapshot.getValue();
               }
           }

           @Override
           public void onCancelled(FirebaseError error) {

           }
       });
   }

    //end


}
