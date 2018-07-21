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

        listMDHeader.add("Issues");
        listMDHeader.add("Admission Details");
        listMDHeader.add("Investigations");
        listMDHeader.add("Consent");
        listMDHeader.add("Surgery Details");

        DatabaseReference databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion01").child("chit/" + idFB);

        databaseReferenceChit.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one

                Chit currentChit = dataSnapshot.getValue(Chit.class);

                if (currentChit == null) {
                    Toast.makeText(getApplicationContext(), "chit null", Toast.LENGTH_LONG).show();
                } else {

                    Objects.requireNonNull(getSupportActionBar()).setTitle(currentChit.getPatient().getName());

                    if (currentChit.getLifeThreatening()) {
                        tvWarningMsg.setText(currentChit.getIssues().getPreOpDiagnosis());
                    } else {
                        glEmergency.setVisibility(GridLayout.GONE);
                    }

                    List<String> issues = new ArrayList<>();
                    Issues currentIssues = currentChit.getIssues();
                    issues.add("Pre-op Diagnosis: " + currentIssues.getPreOpDiagnosis());
                    issues.add("Remarks: " + currentIssues.getRemarks());
                    issues.add("Infection Control Concerns (Yes/No): " + currentIssues.getInfectionControlConcerns());

                    List<String> admissionDetails = new ArrayList<>();
                    AdmissionDetail currentAdmissionDetail = currentChit.getAdmissionDetail();
                    admissionDetails.add("Date of admission: " + currentAdmissionDetail.getDateOfAdmission());
                    admissionDetails.add("Time of admission: " + currentAdmissionDetail.getTimeOfAdmission());
                    admissionDetails.add("Time of admission: " + currentAdmissionDetail.getTimeOfAdmission());
                    admissionDetails.add("Time of last meal: " + currentAdmissionDetail.getLastMeal());
                    admissionDetails.add("Time of last clear fluid: " + currentAdmissionDetail.getLastFluid());

                    tvWaitingTime.setText(currentAdmissionDetail.getTimeOfAdmission() + "Mins");
                    tvLastMeal.setText(currentAdmissionDetail.getLastMeal());
                    tvBedLocation.setText("R1-B2");

                    List<String> investigations = new ArrayList<>();
                    Investigations currentInvestigations = currentChit.getInvestigations();
                    investigations.add("Full blood count: " + currentInvestigations.getFullBloodCount());
                    investigations.add("Renal panel: " + currentInvestigations.getPenalPanel());
                    investigations.add("GXM: " + currentInvestigations.getGxm());
                    investigations.add("PT/PTT: " + currentInvestigations.getPtOrPtt());
                    investigations.add("CXR: " + currentInvestigations.getCxr());
                    investigations.add("ECG: " + currentInvestigations.getEcg());

                    List<String> consent = new ArrayList<>();
                    Consent currentConsent = currentChit.getConsent();
                    consent.add("Surgical consent: " + currentConsent.getSurgicalConsent());
                    consent.add("Anaesthesia consent: " + currentConsent.getAnaesthesiaConsent());
                    consent.add("Others (if applicable): " + currentConsent.getOthers());

                    List<String> surgeryDetails = new ArrayList<>();
                    SurgeryDetails currentSurgeryDetails = currentChit.getSurgeryDetails();
//                    surgeryDetails.add("surgeryDetails);
                    surgeryDetails.add("Orthopaedic consultant-in-charge: " + currentSurgeryDetails.getOrthopaedicConsultantInCharge());
                    surgeryDetails.add("Time windows during which consultant-in-charge is ready (if applicable): " + currentSurgeryDetails.getTimeWindow());
                    surgeryDetails.add("Registrar-in-charge (usually registrar-on-call): " + currentSurgeryDetails.getRegistrarInCharge());
                    surgeryDetails.add("Time window during which registrar-in-charge is ready: " + currentSurgeryDetails.getTimeWindow());
                    surgeryDetails.add("Other registrars available (to be linked with department roster): " + currentSurgeryDetails.getOtherRegistrars());
                    surgeryDetails.add("Other team doctors: " + currentSurgeryDetails.getOtherTeamDoctors());
                    surgeryDetails.add("Lowest level of surgeon allowed to do case (consultant/registar/MO): " + currentSurgeryDetails.getLowestLevelOfSurgronAllowedToDoCase());
                    surgeryDetails.add("Nature of surgery: " + currentSurgeryDetails.getNatureOfSurgery());
                    surgeryDetails.add("Estimated duration: " + currentSurgeryDetails.getEstimatedDuration());
                    surgeryDetails.add("Positioning: " + currentSurgeryDetails.getPositioning());
                    surgeryDetails.add("Implants: " + currentSurgeryDetails.getImplantsm());
                    surgeryDetails.add("Imaging required?" + currentSurgeryDetails.getImagingRequired());
                    surgeryDetails.add("Repeat surgery?: " + currentSurgeryDetails.getRepeatSurgery());
                    surgeryDetails.add("Operating theatre (which OT patient is to be listed in – drop-down menu): " + currentSurgeryDetails.getOperatingTheatre());

                    listMDDetail.put(listMDHeader.get(0), issues); // Header, Child data
                    listMDDetail.put(listMDHeader.get(1), admissionDetails);
                    listMDDetail.put(listMDHeader.get(2), investigations);
                    listMDDetail.put(listMDHeader.get(3), consent);
                    listMDDetail.put(listMDHeader.get(4), surgeryDetails);
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
