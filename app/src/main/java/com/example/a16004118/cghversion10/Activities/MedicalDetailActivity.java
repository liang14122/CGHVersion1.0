package com.example.a16004118.cghversion10.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.a16004118.cghversion10.Adapter.MedicalDetailExpandableListAdapter;
import com.example.a16004118.cghversion10.ObjectPackage.Chit;
import com.example.a16004118.cghversion10.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicalDetailActivity extends AppCompatActivity {

    private ExpandableListView lvMedicalDetail;
    private MedicalDetailExpandableListAdapter listAdapter;
    private List<String> listMDHeader;
    private HashMap<String, List<String>> listMDDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_detail);

        getSupportActionBar().setTitle("Patient Name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvMedicalDetail = findViewById(R.id.lvMedicalDetail);

        Chit currentChit = (Chit) getIntent().getSerializableExtra("currentChit");
        prepareMD(currentChit);

        listAdapter = new MedicalDetailExpandableListAdapter(this, listMDHeader, listMDDetail);

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
            case android.R.id.home:
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

    private void prepareMD(Chit currentChit){

        listMDHeader = new ArrayList<>();
        listMDDetail = new HashMap<String, List<String>>();

        listMDHeader.add("Issues");
        listMDHeader.add("Admission Details");
        listMDHeader.add("Investigations");
        listMDHeader.add("Consent");
        listMDHeader.add("Surgery Details");

        List<String> issues = new ArrayList<String>();
        issues.add("Pre-op Diagnosis");
        issues.add("Remarks");
        issues.add("Infection Control Concerns (Yes/No)");

        List<String> admissionDetails = new ArrayList<String>();
        admissionDetails.add("Date of admission");
        admissionDetails.add("Time of admission");
        admissionDetails.add("Time of last meal");
        admissionDetails.add("Time of last clear fluid");

        List<String> investigations = new ArrayList<String>();
        investigations.add("Full blood count");
        investigations.add("Renal panel");
        investigations.add("GXM");
        investigations.add("PT/PTT");
        investigations.add("CXR");
        investigations.add("ECG");

        List<String> consent = new ArrayList<String>();
        consent.add("Surgical consent");
        consent.add("Anaesthesia consent");
        consent.add("Others (if applicable)");

        List<String> surgeryDetails = new ArrayList<String>();
        surgeryDetails.add("surgeryDetails");
        surgeryDetails.add("Orthopaedic consultant-in-charge");
        surgeryDetails.add("Time windows during which consultant-in-charge is ready (if applicable)");
        surgeryDetails.add("Registrar-in-charge (usually registrar-on-call)");
        surgeryDetails.add("Time window during which registrar-in-charge is ready");
        surgeryDetails.add("Other registrars available (to be linked with department roster)");
        surgeryDetails.add("Other team doctors");
        surgeryDetails.add("Lowest level of surgeon allowed to do case (consultant/registar/MO)");
        surgeryDetails.add("Nature of surgery");
        surgeryDetails.add("Estimated duration");
        surgeryDetails.add("Positioning");
        surgeryDetails.add("Implants");
        surgeryDetails.add("Imaging required?");
        surgeryDetails.add("Repeat surgery?");
        surgeryDetails.add("Operating theatre (which OT patient is to be listed in – drop-down menu)");

        listMDDetail.put(listMDHeader.get(0), issues); // Header, Child data
        listMDDetail.put(listMDHeader.get(1), admissionDetails);
        listMDDetail.put(listMDHeader.get(2), investigations);
        listMDDetail.put(listMDHeader.get(3), consent);
        listMDDetail.put(listMDHeader.get(4), surgeryDetails);
    }
}
