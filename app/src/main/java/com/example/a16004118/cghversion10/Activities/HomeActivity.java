package com.example.a16004118.cghversion10.Activities;

import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.a16004118.cghversion10.Fragment.Activity_notification_list;
import com.example.a16004118.cghversion10.Fragment.PatientListFragment;
import com.example.a16004118.cghversion10.ObjectPackage.Doctor;
import com.example.a16004118.cghversion10.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.FirebaseFunctionsException;
import com.google.firebase.functions.HttpsCallableResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";

    private FirebaseFunctions mFunctions;

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mFunctions = FirebaseFunctions.getInstance();

        //initiate navigation drawer header
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView tvDoctorName = headerView.findViewById(R.id.tvDoctorName);
        TextView tvDoctorId = headerView.findViewById(R.id.tvDoctorEmail);

        Fragment fragment = new PatientListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.drawer_fragment_container, fragment);
        ft.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);

        Gson gson = new GsonBuilder().setLenient().create();
        String doctorString = prefs.getString("doctorString", null);
        Doctor currentDoctor = gson.fromJson(doctorString, Doctor.class);

        tvDoctorName.setText(currentDoctor.getName());
        tvDoctorId.setText(currentDoctor.getSurgeronId());

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
                Intent i = new Intent(HomeActivity.this, AddPatient.class);
                startActivityForResult(i,123);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_patient) {
            fragment = new PatientListFragment();
            Log.i("HomeActivity", "select Patient List button");
        } else if (id == R.id.nav_notification) {
            fragment = new Activity_notification_list();
            Log.i("HomeActivity", "select notification button");
        } else if (id == R.id.nav_profile) {
            Log.i("HomeActivity", "select nav_profile button");
        } else if (id == R.id.nav_about_us) {
            Log.i("HomeActivity", "select nav_aboutUs button");

        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.drawer_fragment_container, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Task<String> getDoctorInfo(String doctor) {
        // Create the arguments to the callable function, which is just one string

        return mFunctions
                .getHttpsCallable("getDoctorInfo")
                .call(doctor)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();
                        Log.w(TAG, "getDoctorInfo:result" + result);
                        return result;
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("Home Activity","Add patient successfully");

    }

}
