package com.team610.scouting.masterapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MatchFragment.OnFragmentInteractionListener, SplitScreenFragment.OnFragmentInteractionListener, TeamFragment.OnFragmentInteractionListener, FieldFragment.OnFragmentInteractionListener {


    Menu actionbar;
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
        getFragmentManager().beginTransaction().add(R.id.main_container,new SplitScreenFragment()).commit();


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
        menu.setGroupVisible(R.id.matchMenuItems,false);
        actionbar = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //TODO
        if (id == R.id.action_auton) {

        }else if(id == R.id.action_teleop){

        }else if(id == R.id.action_postMatch){

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
        //Switch fragments based on id
        if (id == R.id.nav_matches) {
            MatchFragment mFrag = new MatchFragment();
            transaction.replace(R.id.main_container,mFrag).commit();

        } else if (id == R.id.nav_teams) {
            SplitScreenFragment mFrag = new SplitScreenFragment();
            transaction.replace(R.id.main_container,mFrag).commit();
        } else if (id == R.id.nav_field) {
            FieldFragment mFrag = new FieldFragment();
            transaction.replace(R.id.main_container,mFrag).commit();
        }

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
}

