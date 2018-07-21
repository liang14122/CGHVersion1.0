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

import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class PatientPersonalDetailsActivity extends AppCompatActivity {

    TextView tvName, tvId, tvAge, tvJob, tvOccupation, tvLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_personal_details);

        tvName = findViewById(R.id.tvName);
        tvId = findViewById(R.id.tvId);
        tvAge = findViewById(R.id.tvAge);
        tvJob = findViewById(R.id.tvJob);
        tvOccupation = findViewById(R.id.tvOccupation);
        tvLanguage = findViewById(R.id.tvLanguage);

        String idFB = getIntent().getStringExtra("idFB");
        Toast.makeText(getApplicationContext(), idFB, Toast.LENGTH_LONG).show();

        DatabaseReference databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion01").child("chit/" + idFB);

        databaseReferenceChit.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one

                Chit currentChit = dataSnapshot.getValue(Chit.class);

                if (currentChit != null) {

                    Patient currentPatient = currentChit.getPatient();

                    Objects.requireNonNull(getSupportActionBar()).setTitle( currentPatient.getName());
                    tvName.setText("Name: " + currentPatient.getName());
                    tvId.setText("NRIC: " + currentPatient.getNric());
                    tvAge.setText("Age: " + currentPatient.getAge());
                    tvJob.setText("Office/School: " + currentPatient.getLocation());
                    tvOccupation.setText("Occupation: " + currentPatient.getOccupation());
                    tvLanguage.setText("Language: " + currentPatient.getLanguageSpoken());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        });
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
