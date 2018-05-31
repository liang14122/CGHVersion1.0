package com.example.a16004118.cghversion10.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.a16004118.cghversion10.Fragment.PatientListFragment;
import com.example.a16004118.cghversion10.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onStart() {
        super.onStart();


        //Better check user login status
        //may not be using intent
        Intent i = getIntent();
        boolean data = i.getBooleanExtra("userLogin", false);
        if (data) {
            Bundle bundle = new Bundle();
            double distance = i.getDoubleExtra("distance", 0.0);
            String duration = i.getStringExtra("duration");
            bundle.putDouble("distance", distance);
            bundle.putString("duration", duration);

            //Set initial fragment
//            Fragment frag = new HistoryFragment();
//            frag.setArguments(bundle);

            //open initiated fragment at begin
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.addToBackStack(null);
//            ft.replace(R.id.drawer_fragment_container, frag);
//            ft.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //initiate navigation drawer header
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        //Initiate UI element for navigation drawer header here
//        tvUserName = headerView.findViewById(R.id.textViewUsername);
//        ivProfile = headerView.findViewById(R.id.imageViewProfilePic);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_action_bar_button, menu);
        return true;
    }

@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_patient:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_patient) {
            fragment = new PatientListFragment();
            Log.i("HomeActivity","select Patient List button");
        } else if (id == R.id.nav_roster) {
            Log.i("HomeActivity","select nav_roster button");

        } else if (id == R.id.nav_notification) {

            fragment = new Activity_notification_list();
            Log.i("HomeActivity","select notification button");
        } else if (id == R.id.nav_profile) {
            Log.i("HomeActivity","select nav_profile button");

        } else if (id == R.id.nav_about_us) {
            Log.i("HomeActivity","select nav_aboutUs button");

        }
        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.drawer_layout, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
