package com.example.a16004118.cghversion10.Activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class PatientPersonalDetailsActivity extends AppCompatActivity {

    TextView tvName, tvId, tvAge, tvDoctor, tvTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_personal_details);

        tvName = findViewById(R.id.tvName);
        tvId = findViewById(R.id.tvId);
        tvAge = findViewById(R.id.tvAge);
        tvDoctor = findViewById(R.id.tvDoctor);
        tvTable = findViewById(R.id.tvTable);
        PatientAndMedicalDetail patientDetail = (PatientAndMedicalDetail) getIntent().getSerializableExtra("patientDetail");
//        Toast.makeText(getApplicationContext(), patientDetail+"", Toast.LENGTH_LONG).show();

        getSupportActionBar().setTitle(patientDetail.getName());
        tvName.setText("Name: " + patientDetail.getName());
        tvId.setText("Account No.: " + patientDetail.getAccountNo());
        tvAge.setText("Age: " + patientDetail.getAge());
        tvDoctor.setText("Doctor: " + patientDetail.getAssignDoctorId());
        tvTable.setText("Table: " + patientDetail.getTable());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
