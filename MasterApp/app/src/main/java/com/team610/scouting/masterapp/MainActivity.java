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
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;
import com.team610.scouting.masterapp.field.FieldFragment;
import com.team610.scouting.masterapp.match.MatchFragment;
import com.team610.scouting.masterapp.team.TeamFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MatchFragment.OnFragmentInteractionListener, SplitScreenFragment.OnFragmentInteractionListener, TeamFragment.OnFragmentInteractionListener, FieldFragment.OnFragmentInteractionListener {


    public static Firebase rootRef;

    Menu actionbar;

    public static Fragment mFrag;
    static final String[] tournaments = {"GTC", "GTE", "WATERLOO", "WORLDS"};
    public static String currentTournament = "GTC";//TODO default when on that date

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
        }else if(mFrag instanceof FieldFragment){
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
            mFrag = new TeamListFragment();
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

    public void createTournamentDialog(MenuItem ignore) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Tournament")
                .setItems(tournaments, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentTournament = tournaments[which];
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
}

