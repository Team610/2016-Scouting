package com.team610.scouting.masterapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.team610.scouting.masterapp.field.FieldFragment;
import com.team610.scouting.masterapp.match.MatchFragment;
import com.team610.scouting.masterapp.team.TeamData;
import com.team610.scouting.masterapp.team.TeamDialog;
import com.team610.scouting.masterapp.team.TeamFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MatchFragment.OnFragmentInteractionListener,
        SplitScreenFragment.OnFragmentInteractionListener, TeamFragment.OnFragmentInteractionListener,
        FieldFragment.OnFragmentInteractionListener, TeamListFragment.OnFragmentInteractionListener,
        CommentFragment.OnFragmentInteractionListener, TeamDialog.OnFragmentInteractionListener,
        AllianceFragment.OnFragmentInteractionListener {


    public static Firebase rootRef;
    public static HashMap<Integer, Double> simulatedRP; //Key is id, double is predicited RP
    public static ArrayList<SimulatedMatch> simMatches; //List of saved matches
    Menu actionbar;

    public static ScoutingFragment mFrag;
    static final String[] tournaments = {"GTC", "GTE", "WATERLOO", "WORLDS"};
    public static String currentTournament = "WATERLOO";//TODO default when on that date


    public static HashMap<String, TeamData> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        simulatedRP = new HashMap<>();
        simMatches = new ArrayList<>();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Create Fragment to place in main activity
        mFrag = new SplitScreenFragment();
        getFragmentManager().beginTransaction().add(R.id.main_container, mFrag).commit();


        Firebase.setAndroidContext(this);
        rootRef = new Firebase("https://scouting-app.firebaseio.com/");
        teams = new HashMap<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.setGroupVisible(R.id.matchMenuItems, false);
        menu.setGroupVisible(R.id.fieldMenuItems, false);
        menu.setGroupVisible(R.id.metricMenuItems, false);
        menu.setGroupVisible(R.id.simulationMenuItems, false);
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
        } else if (id == R.id.display && (mFrag instanceof MetricsFragment || mFrag instanceof SimulationFragment || mFrag instanceof StandingsFragment)) {
            try {
                mFrag.updateViewsFromThe6ix();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.save_match && mFrag instanceof SimulationFragment) {
            ((SimulationFragment) mFrag).saveMatch();
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
        actionbar.setGroupVisible(R.id.metricMenuItems, id == R.id.nav_metrics || id == R.id.nav_sim || id == R.id.nav_standings);
        actionbar.setGroupVisible(R.id.simulationMenuItems, id == R.id.nav_sim);
        //Switch fragments based on id
        //TODO should probably use a switch statment
        if (id == R.id.nav_matches) {
            mFrag = new MatchFragment();
        } else if (id == R.id.nav_teams) {
            mFrag = new SplitScreenFragment();
        } else if (id == R.id.nav_field) {
            mFrag = new FieldFragment();
        } else if (id == R.id.nav_list) {
            mFrag = new TeamListFragment();
        } else if (id == R.id.nav_alliance) {
            mFrag = new AllianceFragment();
        } else if (id == R.id.nav_comments) {
            mFrag = new CommentFragment();
        } else if (id == R.id.nav_metrics) {
            mFrag = new MetricsFragment();
        } else if (id == R.id.nav_sim) {
            mFrag = new SimulationFragment();
        } else if (id == R.id.nav_standings) {
            mFrag = new StandingsFragment();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    //TODO idk wtf to do
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void refresh(MenuItem ignore) {
        //TODO update all text Views of current fragment
        loadAllTeamData();
        Toast.makeText(MainActivity.mFrag.getActivity(), "Refreshing Team Data", Toast.LENGTH_LONG).show();
    }

    public void loadAllTeamData() {

        rootRef.child(currentTournament).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("YEA");
                int redRating = 0;
                MainActivity.teams.clear();
                for (DataSnapshot match : dataSnapshot.getChildren()) {
                    for (DataSnapshot teamData : match.getChildren()) {
                        TeamData team;
                        if (!teams.containsKey(teamData.getKey())) {
                            team = new TeamData(teamData.getKey());
                            team.putDefence(Defence.PORTCULLIS, new Double[]{0D, 0D, 0D, 0D});
                            team.putDefence(Defence.LOW_BAR, new Double[]{0D, 0D, 0D, 0D});
                            team.putDefence(Defence.ROUGH_TERRAIN, new Double[]{0D, 0D, 0D, 0D});
                            team.putDefence(Defence.ROCK_WALL, new Double[]{0D, 0D, 0D, 0D});
                            team.putDefence(Defence.CHEVAL_DE_FRISE, new Double[]{0D, 0D, 0D, 0D});
                            team.putDefence(Defence.DRAWBRIDGE, new Double[]{0D, 0D, 0D, 0D});
                            team.putDefence(Defence.MOAT, new Double[]{0D, 0D, 0D, 0D});
                            team.putDefence(Defence.RAMPARTS, new Double[]{0D, 0D, 0D, 0D});
                            team.putDefence(Defence.SALLY_PORT, new Double[]{0D, 0D, 0D, 0D});
                            teams.put(team.id + "", team);
                        } else {
                            team = teams.get(teamData.getKey());
                        }
                        team.matches.add(match.getKey().substring(5));
                        int numMatches = team.matches.size();
                        //AUTON
                        DataSnapshot data = teamData.child("auto");

                        //TODO this probably wont work
//                        if (data.child("defenseCrossed") != null && data.child("defenseCrossed").getValue() != null)
//                            team.autonScore += !data.child("defenseCrossed").getValue().equals("") ? 10 : ((boolean) data.child("reachDefence").getValue()) ? 2 : 0;
//                        team.autonScore += ((boolean) data.child("scoredHighGoal").getValue()) ? 10 : ((boolean) data.child("scoredLowGoal").getValue()) ? 5 : 0;

                        if (data.child("defenseCrossed").getValue() != null) {
                            team.autonScore += 10;
                        } else if ((boolean) data.child("reachDefence").getValue()) {
                            team.autonScore += 2;
                        }

                        if ((boolean) data.child("scoredHighGoal").getValue()) {
                            team.autonScore += 10;
                        } else if ((boolean) data.child("scoredLowGoal").getValue()) {
                            team.autonScore += 5;
                        }

                        //Defence Scores
                        data = teamData.child("teleop");
                        team.defenseScore += Math.min(((long) data.child("defence1crosses").getValue()) * 5, 10);
                        team.defenseScore += Math.min(((long) data.child("defence2crosses").getValue()) * 5, 10);
                        team.defenseScore += Math.min(((long) data.child("defence3crosses").getValue()) * 5, 10);
                        team.defenseScore += Math.min(((long) data.child("defence4crosses").getValue()) * 5, 10);
                        team.defenseScore += Math.min(((long) data.child("defence5crosses").getValue()) * 5, 10);

                        //Shooting
                        team.highGoalMisses += ((long) data.child("highGoalMisses").getValue());
                        team.highGoalShots += ((long) data.child("highGoalScores").getValue());
                        team.lowGoalMisses += ((long) data.child("lowGoalMisses").getValue());
                        team.lowGoalShots += ((long) data.child("lowGoalScores").getValue());
                        team.courtyardDrops += ((long) data.child("courtyardScores").getValue());

                        //Fouls
                        team.fouls += ((long) data.child("fouls").getValue());

                        //Defence rating
                        for (int i = 1; i <= 5; i++) {
                            data = teamData.child("matchSetup");
                            Defence d;
                            if (i == 5) {
                                d = Defence.LOW_BAR;
                            } else {
                                d = Defence.getDefence((String) data.child("defence" + i).getValue());
                            }
                            if (d == null) continue;
                            if (!team.defences.containsKey(d)) {
                                team.defences.put(d, new Double[]{0D, 0D, 0D, 0D});
                            }
                            team.defences.get(d)[2]++;//Increase number of matches this defence occured
                            data = teamData.child("teleop");
                            long val = (long) data.child("defence" + i + "rating").getValue();
                            if (val != 0) {
                                if (val == 3) redRating++;
                                team.defences.get(d)[0] += val;
                                team.defences.get(d)[3]++;
                            }

                            System.out.println(match.getKey() + " " + team.id + " " + i);
                            System.out.println(team.id + " " + d.toString() + " " + data.child("defence" + i + "crosses").getValue());
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
                        long rating = (long) (data.child("defensiveRating").getValue());
                        if (rating != 0) {
                            team.defensiveRating *= team.defensePlayed;
                            team.defensePlayed++;
                            team.defensiveRating += rating;
                            team.defensiveRating /= team.defensePlayed;
                        }
                        team.shotFromCheckMate = team.shotFromCheckMate || (boolean) data.child("shotFromCheckMate").getValue();
                        team.shotFromDefences = team.shotFromDefences || (boolean) data.child("shotFromDefences").getValue();
                        team.shotFromCourtyard = team.shotFromCourtyard || (boolean) data.child("shotFromPopShot").getValue();
                        team.shotFromCorner = team.shotFromCorner || (boolean) data.child("shotFromCorner").getValue();

                        team.scouts.put(match.getKey(), (String) data.child("scoutName").getValue());
                        if (!currentTournament.equals("GTE")) {
                            if (data.child("RP") != null) {
                                team.RP += (Long) data.child("RP").getValue();
                            }
                            team.captures += (boolean) data.child("capture").getValue() ? 1 : 0;
                            team.breaches += (boolean) data.child("breach").getValue() ? 1 : 0;
                        }
                    }


                }
                System.out.println(redRating);
                try {
                    mFrag.updateViewsFromThe6ix();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("NAH");
                for (TeamData t : teams.values()) {
                    simulatedRP.put(t.id, Double.valueOf(t.RP));
                }
               // printErrors();
                Toast.makeText(MainActivity.mFrag.getActivity(), "Team Data Loaded", Toast.LENGTH_SHORT).show();
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

    public static TeamData getTeam(int id) {
        if (teams == null || teams.isEmpty()) {
            return null;
        } else {
            return teams.get(id + "");
        }
    }

    public void matchFrag(String match) {
        mFrag = MatchFragment.newInstance(match);
        getFragmentManager().beginTransaction().replace(R.id.main_container, mFrag).commit();
        actionbar.setGroupVisible(R.id.matchMenuItems, true);
    }

    @Override
    public void onTeamListFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCommentFragmentInteraction(Uri uri) {

    }

    @Override
    public void onReviewFragmentInteraction(Uri uri) {

    }

    public void printErrors() {
        rootRef.child(currentTournament).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot tournament) {
                int teamNumErrors = 0;
                int defenceErrors = 0;
                int teamIDErrors = 0;
                int noScout = 0;
                int rpErrors = 0;
                HashMap<String, Integer> scouts = new HashMap<>();
                for (DataSnapshot match : tournament.getChildren()) {
                    for (DataSnapshot team : match.getChildren()) {
                        if (team.child("misc").child("scoutName").getValue().equals("")) {
                            noScout++;
                        } else {
                            String s = (String) team.child("misc").child("scoutName").getValue();
                            if (!scouts.containsKey(s)) {
                                scouts.put(s, 1);
                            } else {
                                scouts.put(s, scouts.get(s) + 1);
                            }
                        }

                        if (team.child("misc").child("RP") == null) {
                            rpErrors++;
                            System.out.println("RP NULL " + match.getKey() + "  " + team.getKey());
                        }

                    }
                    if (match.getChildrenCount() != 6L) {
                        System.out.println(match.getKey() + " has " + match.getChildrenCount() + " teams");
                        teamNumErrors++;
                        continue;
                    }
                    HashMap<Integer, Integer> check = new HashMap<>();
                    for (DataSnapshot team : match.getChildren()) {
                        String s = "";
                        for (DataSnapshot defence : team.child("matchSetup").getChildren()) {
                            s += defence.getValue();
                        }
                        if (!check.containsKey(s.hashCode())) check.put(s.hashCode(), 1);
                        else check.put(s.hashCode(), check.get(s.hashCode()) + 1);
                    }
                    if (check.size() != 2 && match.getChildrenCount() != 7) {
                        System.out.println(match.getKey() + " has " + check.size() + " defence configs");
                        defenceErrors++;
                    } else {
                        for (int i : check.values())
                            if (i != 3) {
                                System.out.println(match.getKey() + " has a defence config " + i + " times");
                                defenceErrors++;
                                break;
                            }
                    }


                }
                for (TeamData teamData : teams.values()) {
                    if (teamData.matches.size() != 10) {
                        if (teamData.matches.size() < 3) {
                            System.out.println(teamData.id + " isnt real " + teamData.matches.get(0));
                            //teamIDErrors++;
                        } else if (teamData.matches.size() > 6) {
                            System.out.println(teamData.id + " is missing " + (10 - teamData.matches.size()) + " matches");
                            teamIDErrors++;
                        }
                    }
                }
                for (String s : scouts.keySet()) {
                    System.out.println(s + ":  " + scouts.get(s) + " matches");
                }


                System.out.println("Defence Errors: " + defenceErrors);
                System.out.println("Team Num Errors: " + teamNumErrors);
                System.out.println("Team ID Errors: " + teamIDErrors);
                System.out.println("No Scout Names : " + noScout);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onAllianceFragmentInteraction(Uri uri) {

    }
}

