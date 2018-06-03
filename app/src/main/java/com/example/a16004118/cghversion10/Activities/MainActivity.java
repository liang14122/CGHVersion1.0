package com.example.a16004118.cghversion10.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a16004118.cghversion10.NotificationService.Common;
import com.example.a16004118.cghversion10.ObjectPackage.AdmissionDetail;
import com.example.a16004118.cghversion10.ObjectPackage.Chit;
import com.example.a16004118.cghversion10.ObjectPackage.Consent;
import com.example.a16004118.cghversion10.ObjectPackage.Doctor;
import com.example.a16004118.cghversion10.ObjectPackage.Investigations;
import com.example.a16004118.cghversion10.ObjectPackage.Issues;
import com.example.a16004118.cghversion10.ObjectPackage.Patient;
import com.example.a16004118.cghversion10.ObjectPackage.SurgeryDetails;
import com.example.a16004118.cghversion10.ObjectPackage.SurgicalTable;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {
    DatabaseReference databaseReference;

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find the table name and start to add

        Log.d("Test Purpose", "Refreshed token: " + Common.currentToken);

        btnLogin = findViewById(R.id.btnLogin);

        addDataInFirebase();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }





    public void addDataInFirebase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("chit");
        //get the key of the column
        String idFB = databaseReference.push().getKey();
        AdmissionDetail admissionDetail;
        Chit chit;
        Consent consent;
        Doctor doctor;
        Investigations investigations;
        Issues issues;
        Patient patient;
        SurgeryDetails surgeryDetails;
        SurgicalTable surgicalTable;
        admissionDetail = new AdmissionDetail("03/02/2018","100","1500","1300");
        consent = new Consent("a","b","c");
        doctor = new Doctor("Bob","G1234567A","08/08/2000","123465678","12345678","5","Nice doc","woodlands",true,false);
        investigations = new Investigations("1.0","1","1","2","3","4");
        issues = new Issues("ok","nice","idk");
        patient = new Patient("Mary","A1234657A","08/08/1999","13","female","chinese","English","idk","abc","12344567","no");
        surgeryDetails = new SurgeryDetails("a","b","c","1234","d","true","no","1","asd","es","q","d","c","a","123");
        surgicalTable = new SurgicalTable("c123","for patient",false);
        chit = new Chit(patient,issues,admissionDetail,investigations,consent,surgeryDetails,false);
        //add data.
        databaseReference.child(idFB).setValue(chit);
    }
}

