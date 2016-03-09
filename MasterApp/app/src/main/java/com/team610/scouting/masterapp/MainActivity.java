package com.team610.scouting.masterapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.team610.scouting.masterapp.field.FieldFragment;
import com.team610.scouting.masterapp.match.MatchFragment;
import com.team610.scouting.masterapp.team.TeamData;
import com.team610.scouting.masterapp.team.TeamFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MatchFragment.OnFragmentInteractionListener, SplitScreenFragment.OnFragmentInteractionListener, TeamFragment.OnFragmentInteractionListener, FieldFragment.OnFragmentInteractionListener {


    public static Firebase rootRef;

    Menu actionbar;

    public static ScoutingFragment mFrag;
    static final String[] tournaments = {"GTC", "GTE", "WATERLOO", "WORLDS"};
    public static String currentTournament = "GTC";//TODO default when on that date


    HashMap<String, TeamData> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Create Fragment to place in main activity
        getFragmentManager().beginTransaction().add(R.id.main_container, new SplitScreenFragment()).commit();


        Firebase.setAndroidContext(this);
        rootRef = new Firebase("https://scouting-app.firebaseio.com/");
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.setGroupVisible(R.id.matchMenuItems, false);
        menu.setGroupVisible(R.id.fieldMenuItems, false);
        actionbar = menu;
        actionbar.findItem(R.id.action_tournament).setTitle(currentTournament);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        SpannableString string = new SpannableString(item.getTitle().toString());
        string.setSpan(new ForegroundColorSpan(Color.BLACK), 0, string.length(), 0);
        item.setTitle(string);
        if (mFrag instanceof MatchFragment) {
            ((MatchFragment) mFrag).onMenuTap(id);
        } else if (mFrag instanceof FieldFragment) {
            ((FieldFragment) mFrag).onMenuTap(id);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Get id of menu item
        int id = item.getItemId();
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        //Set Match menu items visible based on fragment choice
        actionbar.setGroupVisible(R.id.matchMenuItems, id == R.id.nav_matches);
        actionbar.setGroupVisible(R.id.fieldMenuItems, id == R.id.nav_field);
        //Switch fragments based on id
        //TODO should probably use a switch statment
        if (id == R.id.nav_matches) {
            mFrag = new MatchFragment();
        } else if (id == R.id.nav_teams) {
            mFrag = new SplitScreenFragment();
        } else if (id == R.id.nav_field) {
            mFrag = new FieldFragment();
        } else if (id == R.id.nav_list) {
            //TODO FIX IT  mFrag = new TeamListFragment();
        } else if (id == R.id.nav_alliance) {
            mFrag = new AllianceFragment();
        }
        transaction.replace(R.id.main_container, mFrag).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //TODO idk wtf to do
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void refresh(MenuItem ignore) {
        //TODO update all text Views of current fragment
        loadAllTeamData();
    }

    public void loadAllTeamData() {
        rootRef.child(currentTournament).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot match : dataSnapshot.getChildren()) {
                    for (DataSnapshot teamData : match.getChildren()) {
                        TeamData team;
                        if (!teams.containsKey(teamData.getKey())) {
                            team = new TeamData(teamData.getKey());
                        } else {
                            team = teams.get(teamData.getKey());
                        }
                        team.matches.add(match.getKey().substring(5));
                        int numMatches = team.matches.size();
                        //AUTON
                        DataSnapshot data = teamData.child("auto");

                        //TODO this probably wont work
                        team.autonScore += data.child("defenseCrossed").getValue() == null ? 10 : ((boolean) data.child("reachDefence").getValue()) ? 2 : 0;
                        team.autonScore += ((boolean) data.child("scoredHighGoal").getValue()) ? 10 : ((boolean) data.child("scoredLowGoal").getValue()) ? 5 : 0;

                        //Defence Scores
                        data = teamData.child("teleop");
                        team.defenseScore += Math.min(((long) data.child("defence1crosses").getValue()) * 5, 10);
                        team.defenseScore += Math.min(((long) data.child("defence2crosses").getValue()) * 5, 10);
                        team.defenseScore += Math.min(((long) data.child("defence3crosses").getValue()) * 5, 10);
                        team.defenseScore += Math.min(((long) data.child("defence4crosses").getValue()) * 5, 10);
                        team.defenseScore += Math.min(((long) data.child("defence5crosses").getValue()) * 5, 10);

                        //Shooting
                        team.highGoalMisses += ((long) data.child("highGoalMisses").getValue());
                        team.highGoalShots += ((long) data.child("highGoalShots").getValue());
                        team.lowGoalMisses += ((long) data.child("lowGoalMisses").getValue());
                        team.lowGoalShots += ((long) data.child("lowGoalShots").getValue());
                        team.courtyardDrops += ((long) data.child("courtyardScores").getValue());

                        //Fouls
                        team.fouls += ((long) data.child("fouls").getValue());

                        //Defence rating
                        for (int i = 0; i < 5; i++) {
                            data = teamData.child("matchSetup");
                            Defence d = Defence.getDefence((String) data.child("defence" + i).getValue());
                            if (!team.defences.containsKey(d)) {
                                team.defences.put(d, new Double[]{0D, 0D});
                            }
                            data = teamData.child("teleop");
                            long val = (long) data.child("defence" + i + "rating").getValue();
                            if (val != 0) {
                                team.defences.get(d)[0] *= numMatches - 1;
                                team.defences.get(d)[0] += val;
                                team.defences.get(d)[0] /= numMatches;
                            }
                            team.defences.get(d)[1] += (long) data.child("defence" + i + "crosses").getValue();
                        }

                        //MISC
                        data = teamData.child("misc");
                        String challenge = "challenge";

                        if (currentTournament.equals("GTC")) challenge = "challange";
                        team.comments.put(match.getKey(), (String) data.child("comment").getValue());
                        team.breaches += (boolean) data.child("breach").getValue() ? 1 : 0;
                        team.captures += (boolean) data.child("capture").getValue() ? 1 : 0;
                        team.challenges += (boolean) data.child(challenge).getValue() ? 1 : 0;
                        team.hangs += (boolean) data.child("hang").getValue() ? 1 : 0;
                        int rating = (int) data.child("defenseRating").getValue();
                        if (rating != 0) {
                            team.defensiveRating *= team.defensePlayed;
                            team.defensePlayed++;
                            team.defensiveRating += rating;
                            team.defensiveRating /= team.defensePlayed;
                        }
                        team.shotFromCheckMate = team.shotFromCheckMate || (boolean) data.child("shotFromCheckMate").getValue();
                        team.shotFromDefences = team.shotFromDefences || (boolean) data.child("shotFromDefences").getValue();
                        team.shotFromCourtyard = team.shotFromCourtyard || (boolean) data.child("shotFromPopShot").getValue();

                    }


                }

                try {
                    mFrag.updateViewsFromThe6ix();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void createTournamentDialog(final MenuItem ignore) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Tournament")
                .setItems(tournaments, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentTournament = tournaments[which];
                        ignore.setTitle(currentTournament);
                    }
                });
        //Set up negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public TeamData getTeam(int id) {
        if (teams == null || teams.isEmpty()) {
            return null;
        } else {
            return teams.get(id + "");
        }
    }
}

