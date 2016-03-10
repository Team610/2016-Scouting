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

    public Team[] teams;
    int matchNumber;

    public static MatchData instance; //current match

//    public static MatchData getInstance(int match){
//        if(instance == null){
//            instance = new MatchData(match);
//        }
//        return instance;
//    }


    public MatchData(int match) {
        this.matchNumber = match;
        teams = new Team[6];
        for (int i = 0; i < 6; i++) {
            teams[i] = new Team(i, this);
        }
        instance = this;
        updateDatagen();

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
                try {
                    MainActivity.mFrag.updateViewsFromThe6ix();
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

            @Override
            public void onCancelled(FirebaseError error) {

            }
        });

    }

}

