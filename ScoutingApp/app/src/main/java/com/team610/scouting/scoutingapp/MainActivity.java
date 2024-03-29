package com.team610.scouting.scoutingapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MatchSetup.OnFragmentInteractionListener,
            TeleopFragment.OnFragmentInteractionListener,
            InitialFragment.OnFragmentInteractionListener,
            AutoFragment.OnFragmentInteractionListener,
            SelectDefences.OnFragmentInteractionListener,
            ReviewFragment.OnFragmentInteractionListener,
            ExtraData.OnFragmentInteractionListener{

    public static Vibrator vib;
    public static Firebase rootRef;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);




        Firebase.setAndroidContext(this);

        //Init the firebase
         rootRef = new Firebase("https://scouting-app.firebaseio.com/");



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Transaction to Main Screen
        initialTransaction();

//        transitionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });

    }

    public void updateDataTest(){

        rootRef.setValue(5);

    }

    /**
     * Initial fragment transaction
     */
    public void initialTransaction(){
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        MatchSetup initialFragment = MatchSetup.getInstance();

        transaction.replace(R.id.main_container, initialFragment);

        transaction.commit();
    }


    //Remove this or not
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        saveAllFragmentData();


        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        if (id == R.id.fragment_initial) {

            //saveAllFragmentData();

            InitialFragment mFrag = new InitialFragment();
            transaction.replace(R.id.main_container,mFrag).commit();
        } else if (id == R.id.fragment_match_setup) {

            //saveAllFragmentData();

            MatchSetup mFrag = MatchSetup.getInstance();
            mFrag.openMatchSetup();
            transaction.replace(R.id.main_container,mFrag).commit();
        } else if (id == R.id.fragment_auto) {

            //saveAllFragmentData();

            AutoFragment mFrag = AutoFragment.getInstance();
            transaction.replace(R.id.main_container,mFrag).commit();
        } else if(id == R.id.fragment_teleop){

            //saveAllFragmentData();

            TeleopFragment mFrag = TeleopFragment.getInstance();
            transaction.replace(R.id.main_container,mFrag).commit();
        }
        else if(id == R.id.fragment_review){

            //saveAllFragmentData();

            ReviewFragment mFrag = ReviewFragment.getInstance();
            transaction.replace(R.id.main_container,mFrag).commit();
        }
        else if(id == R.id.fragment_extra_data){

            ExtraData mFrag = ExtraData.getInstance();
            transaction.replace(R.id.main_container, mFrag).commit();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void saveAllFragmentData(){
        MatchSetup.getInstance().saveData();
        //
    }




    @Override
    public void onMatchSetupFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTeleopFragmentInteraction(Uri uri) {

    }

    @Override
    public void onInitialFragmentInteraction(Uri uri) {

    }
    @Override
    public void onAutonFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSelectDefencesFragmentInteraction(Uri uri) {

    }

    @Override
    public void onReviewFragmentInteraction(Uri uri) {

    }



    @Override
    public void onExtraDataFragmentInteraction(Uri uri) {

    }
}
