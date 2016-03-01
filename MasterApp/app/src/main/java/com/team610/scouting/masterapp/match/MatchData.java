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
    public long highGoalScores = 3;
    public long lowGoalScores = rand.nextInt(100);
    public long courtyardScores = rand.nextInt(100);
    public long highGoalMisses = rand.nextInt(100);
    public long lowGoalMisses = rand.nextInt(100);
    public long courtyardMisses = rand.nextInt(100);
    public double courtyardPercent = ((10000 * courtyardScores)) / (courtyardMisses + courtyardScores) / 100D;
    public double highGoalPercent = ((10000 * highGoalScores)) / (highGoalMisses + highGoalScores) / 100D;
    public double lowGoalPercent = ((10000 * lowGoalScores)) / (lowGoalMisses + lowGoalScores) / 100D;
    public int points = 20;
    public int totalCrosses = 120;
    public String capture = "Scale";

    //unimplemented
    public long defence1crosses;
    public long defence2crosses;
    public long defence3crosses;
    public long defence4crosses;
    public long defense5crosses;

    public long defense1rating;
    public long defense2rating;
    public long defense3rating;
    public long defense4rating;
    public long defense5rating;

    public String defence1;
    public String defence2;
    public String defence3;
    public String defence4;


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

        //Teleop
        points += highGoalScores * 5 + lowGoalScores * 2;//TODO ADD DEFENSES

        //Post Match
        //  points +=
    }


}
