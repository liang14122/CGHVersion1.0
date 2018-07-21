package com.example.a16004118.cghversion10.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a16004118.cghversion10.Adapter.MedicalDetailExpandableListAdapter;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PatientMedicalDetailActivity extends AppCompatActivity {

    private List<String> listMDHeader;
    private HashMap<String, List<String>> listMDDetail;
    private String idFB;
    private TextView tvWaitingTime, tvLastMeal, tvBedLocation, tvWarningMsg;
    private GridLayout glEmergency;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_detail);

        idFB = getIntent().getStringExtra("idFB");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ExpandableListView lvMedicalDetail = findViewById(R.id.lvMedicalDetail);

        tvWaitingTime = findViewById(R.id.tvWaitingTime);
        tvLastMeal = findViewById(R.id.tvLastMeal);
        tvBedLocation = findViewById(R.id.tvBedLocation);
        tvWarningMsg = findViewById(R.id.tvWarningMsg);
        glEmergency = findViewById(R.id.glEmergency);

        prepareMD(idFB);

        MedicalDetailExpandableListAdapter listAdapter = new MedicalDetailExpandableListAdapter(this, listMDHeader, listMDDetail);

        lvMedicalDetail.setAdapter(listAdapter);

        lvMedicalDetail.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listMDHeader.get(groupPosition)
                                + " : "
                                + listMDDetail.get(
                                listMDHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        lvMedicalDetail.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listMDHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        lvMedicalDetail.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listMDHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_personal_detail:
                // User chose the "Settings" item, show the app settings UI...
                Intent i = new Intent(PatientMedicalDetailActivity.this, PatientPersonalDetailsActivity.class);
                i.putExtra("idFB",  getIntent().getStringExtra("idFB"));
                startActivity(i);
                return true;
            case R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.medical_detail_action_bar_button, menu);
        return true;
    }

    private void prepareMD(String idFB) {

        listMDHeader = new ArrayList<>();
        listMDDetail = new HashMap<>();

        listMDHeader.add("Medical Details");
        listMDHeader.add("Assign Details");

        DatabaseReference databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion01").child("chit/" + idFB);

        databaseReferenceChit.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one

                PatientAndMedicalDetail currentChit = dataSnapshot.getValue(PatientAndMedicalDetail.class);

                if (currentChit == null) {
                    Toast.makeText(getApplicationContext(), "chit null", Toast.LENGTH_LONG).show();
                } else {

                    Objects.requireNonNull(getSupportActionBar()).setTitle(currentChit.getName());

                    if (currentChit.getLifeThreating()) {
                        tvWarningMsg.setText(currentChit.getPreOpDiagnosis());
                    } else {
                        glEmergency.setVisibility(GridLayout.GONE);
                    }

                    List<String> medical = new ArrayList<>();
                    medical.add("Last Meal Taken: " + currentChit.getLastMeal());
                    medical.add("Last Clear Fluid: " + currentChit.getLastClearFluid());
                    medical.add("Type of Anaesthesia/Sedation: " + currentChit.getTypeOfAnaesthesiaSedation());
                    medical.add("Pre-Op Diagnosis: " + currentChit.getPreOpDiagnosis());
                    medical.add("Contact Precautions: " + currentChit.getContactPrecautionsString());
                    medical.add("Blood borne infections: " + currentChit.getBloodBorneInfectionString());
                    medical.add("AirBorne precautions: " + currentChit.getAirbornePrecautionsString());
                    medical.add("Location: " + currentChit.getLocation());
                    medical.add("OT: " + currentChit.getOt());

                    List<String> assign = new ArrayList<>();
                    assign.add("Assign Doctor: " + currentChit.getAssignDoctorId());
                    assign.add("Assign Table: " + currentChit.getTable());

                    tvWaitingTime.setText(currentChit.getChitSubmission());
                    tvLastMeal.setText(currentChit.getLastMeal());
                    tvBedLocation.setText(currentChit.getBed());

                    listMDDetail.put(listMDHeader.get(0), medical); // Header, Child data
                    listMDDetail.put(listMDHeader.get(1), assign);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        idFB = prefs.getString("idFB", null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("idFB", idFB);
        editor.apply();
    }


}
