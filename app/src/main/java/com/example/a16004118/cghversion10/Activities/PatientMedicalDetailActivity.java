package com.example.a16004118.cghversion10.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PatientMedicalDetailActivity extends AppCompatActivity {

    private List<String> listMDHeader;
    private HashMap<String, List<String>> listMDDetail;
    private PatientAndMedicalDetail patientDetail;
    private String patientDetailString;
    private TextView tvWaitingTime, tvLastMeal, tvBedLocation, tvWarningMsg;
    private GridLayout glEmergency;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_detail);

        patientDetail = (PatientAndMedicalDetail) getIntent().getSerializableExtra("patientDetail");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ExpandableListView lvMedicalDetail = findViewById(R.id.lvMedicalDetail);

        tvWaitingTime = findViewById(R.id.tvWaitingTime);
        tvLastMeal = findViewById(R.id.tvLastMeal);
        tvBedLocation = findViewById(R.id.tvBedLocation);
        tvWarningMsg = findViewById(R.id.tvWarningMsg);
        glEmergency = findViewById(R.id.glEmergency);

        prepareMD();

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

    private void prepareMD() {

        listMDHeader = new ArrayList<>();
        listMDDetail = new HashMap<>();

        listMDHeader.add("Medical Details");
        listMDHeader.add("Assign Details");


                if (patientDetail == null) {
                    Toast.makeText(getApplicationContext(), "chit null", Toast.LENGTH_LONG).show();
                } else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Objects.requireNonNull(getSupportActionBar()).setTitle(patientDetail.getName());
                    }

                    if (patientDetail.getLifeThreating()) {
                        tvWarningMsg.setText(patientDetail.getPreOpDiagnosis());
                    } else {
                        glEmergency.setVisibility(GridLayout.GONE);
                    }

                    List<String> medical = new ArrayList<>();
                    medical.add("Last Meal Taken: " + patientDetail.getLastMeal());
                    medical.add("Last Clear Fluid: " + patientDetail.getLastClearFluid());
                    medical.add("Type of Anaesthesia/Sedation: " + patientDetail.getTypeOfAnaesthesiaSedation());
                    medical.add("Pre-Op Diagnosis: " + patientDetail.getPreOpDiagnosis());
                    medical.add("Contact Precautions: " + patientDetail.getContactPrecautionsString());
                    medical.add("Blood borne infections: " + patientDetail.getBloodBorneInfectionString());
                    medical.add("AirBorne precautions: " + patientDetail.getAirbornePrecautionsString());
                    medical.add("Location: " + patientDetail.getLocation());
                    medical.add("OT: " + patientDetail.getOt());

                    List<String> assign = new ArrayList<>();
                    assign.add("Assign Doctor: " + patientDetail.getAssignDoctorId());
                    assign.add("Assign Table: " + patientDetail.getTable());

                    tvWaitingTime.setText(patientDetail.getChitSubmission());
                    tvLastMeal.setText(patientDetail.getLastMeal());
                    tvBedLocation.setText(patientDetail.getBed());

                    listMDDetail.put(listMDHeader.get(0), medical); // Header, Child data
                    listMDDetail.put(listMDHeader.get(1), assign);
                }
            }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        patientDetailString = prefs.getString("patientDetail", null);
        Gson gson = new Gson();
        patientDetail = gson.fromJson(patientDetailString, PatientAndMedicalDetail.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        patientDetailString = gson.toJson(patientDetail);
        editor.putString("patientDetail", patientDetailString);
        editor.apply();
    }

}
