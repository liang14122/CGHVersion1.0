package com.example.a16004118.cghversion10.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a16004118.cghversion10.Fragment.TabAssign;
import com.example.a16004118.cghversion10.Fragment.TabMedical;
import com.example.a16004118.cghversion10.Fragment.TabPatient;
import com.example.a16004118.cghversion10.ObjectPackage.Doctor;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class EditPatient extends AppCompatActivity {


    Button btnUpdate, btnDelete;
    private TabAssign tabAssign;
    private TabMedical tabMedical;
    private TabPatient tabPatient;
    DatabaseReference databaseReferenceChit;
    PatientAndMedicalDetail patientDetail;

    //patient part
    String mrin, acountNo, name, age, gender;
    //medical part
    String lastMeal, lastClearFluid;
    Boolean lifeThreating;
    String typeOfAnaesthesiaSedation, preOpDiagnosis;
    String contactPrecautionsString, bloodBorneInfectionString, airbornePrecautionsString, otherHighRiskInfectionsString;
    String assignDoctorId, location, ot, chitSubmission;
    String dept, ward, room, bed, table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);

        //init tabs layout ask leo

        //======================================================================================================
        //here
        //String idFB = getIntent().getStringExtra("idFB");
        databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion20").child("Chit");
        //======================================================================================================
        //Toast.makeText(getApplicationContext(), idFB, Toast.LENGTH_LONG).show();
        //databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion20").child("Chit/" + idFB);

        patientDetail = (PatientAndMedicalDetail) getIntent().getSerializableExtra("patientDetail");


        if(patientDetail != null){
            //TabPatient
            mrin = patientDetail.getMrin();
            acountNo = patientDetail.getAccountNo();
            name = patientDetail.getName();
            age = patientDetail.getAge();
            gender = patientDetail.getGender();

            //TabMedical
            lastMeal = patientDetail.getLastMeal();
            lastClearFluid = patientDetail.getLastClearFluid();
            lifeThreating = patientDetail.getLifeThreating();
            typeOfAnaesthesiaSedation = patientDetail.getTypeOfAnaesthesiaSedation();
            preOpDiagnosis = patientDetail.getPreOpDiagnosis();
            contactPrecautionsString = patientDetail.getContactPrecautionsString();
            bloodBorneInfectionString = patientDetail.getBloodBorneInfectionString();
            airbornePrecautionsString = patientDetail.getAirbornePrecautionsString();
            otherHighRiskInfectionsString = patientDetail.getOtherHighRiskInfectionsString();

            assignDoctorId = patientDetail.getAssignDoctorId();
            location = patientDetail.getLocation();
            ot = patientDetail.getOt();
            chitSubmission = patientDetail.getChitSubmission();
            dept = patientDetail.getDept();
            ward = patientDetail.getWard();
            room = patientDetail.getRoom();
            bed = patientDetail.getBed();
            table = patientDetail.getTable();

            tabPatient.etMRIN.setText(mrin);
            tabPatient.etAccount.setText(acountNo);
            tabPatient.etName.setText(name);
            tabPatient.etAge.setText(age);
            if (gender.equals("male")) {
                tabPatient.rbMale.setChecked(true);
            } else {
                tabPatient.rbFemale.setChecked(true);
            }
            tabPatient.etWard.setText(ward);
            tabPatient.etRoom.setText(room);
            tabPatient.etBed.setText(bed);

            tabMedical.etLastMeal.setText(lastMeal);
            tabMedical.etLastFluid.setText(lastClearFluid);
            if (lifeThreating) {
                tabPatient.rbEmergency.setChecked(true);
            } else {
                tabPatient.rbNonEmergency.setChecked(true);
            }
            tabMedical.etPreOp.setText(preOpDiagnosis);
            //this part will not work
            DatabaseReference docRef = FirebaseDatabase.getInstance().getReference("cghversion20/Doctor");
            docRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Doctor doctor = (Doctor) dataSnapshot.getValue(Doctor.class);
                    if (doctor != null) {
                        tabAssign.tvDoctor.setText(doctor.getName());
                        tabAssign.tvTable.setText(table);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        //======================================================================================================

        /*
        databaseReferenceChit.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one

                PatientAndMedicalDetail currentChit = dataSnapshot.getValue(PatientAndMedicalDetail.class);


                name = currentChit.getName();

                if (currentChit != null) {
                    //TabPatient
                    mrin = patientDetail.getMrin();
                    acountNo = patientDetail.getAccountNo();
                    name = patientDetail.getName();
                    age = patientDetail.getAge();
                    gender = patientDetail.getGender();

                    //TabMedical
                    lastMeal = patientDetail.getLastMeal();
                    lastClearFluid = patientDetail.getLastClearFluid();
                    lifeThreating = patientDetail.getLifeThreating();
                    typeOfAnaesthesiaSedation = patientDetail.getTypeOfAnaesthesiaSedation();
                    preOpDiagnosis = patientDetail.getPreOpDiagnosis();
                    contactPrecautionsString = patientDetail.getContactPrecautionsString();
                    bloodBorneInfectionString = patientDetail.getBloodBorneInfectionString();
                    airbornePrecautionsString = patientDetail.getAirbornePrecautionsString();
                    otherHighRiskInfectionsString = patientDetail.getOtherHighRiskInfectionsString();

                    assignDoctorId = patientDetail.getAssignDoctorId();
                    location = patientDetail.getLocation();
                    ot = patientDetail.getOt();
                    chitSubmission = patientDetail.getChitSubmission();
                    dept = patientDetail.getDept();
                    ward = patientDetail.getWard();
                    room = patientDetail.getRoom();
                    bed = patientDetail.getBed();
                    table = patientDetail.getTable();

                    tabPatient.etMRIN.setText(mrin);
                    tabPatient.etAccount.setText(acountNo);
                    tabPatient.etName.setText(name);
                    tabPatient.etAge.setText(age);
                    if (gender.equals("male")) {
                        tabPatient.rbMale.setChecked(true);
                    } else {
                        tabPatient.rbFemale.setChecked(true);
                    }
                    tabPatient.etWard.setText(ward);
                    tabPatient.etRoom.setText(room);
                    tabPatient.etBed.setText(bed);

                    tabMedical.etLastMeal.setText(lastMeal);
                    tabMedical.etLastFluid.setText(lastClearFluid);
                    if (lifeThreating) {
                        tabPatient.rbEmergency.setChecked(true);
                    } else {
                        tabPatient.rbNonEmergency.setChecked(true);
                    }
                    tabMedical.etPreOp.setText(preOpDiagnosis);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        }); */



        //======================================================================================================

        EditPatient.SectionsPagerAdapter mSectionsPagerAdapter = new EditPatient.SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        btnUpdate = findViewById(R.id.btnUpload);
        btnDelete = findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //patient tab
                String name = tabPatient.etName.getText().toString();
                Boolean lifeThreating = true;
                if (tabPatient.rbNonEmergency.isChecked()) {
                    lifeThreating = false;
                }
                String mrin = tabPatient.etMRIN.getText().toString();
                String account = tabPatient.etAccount.getText().toString();
                String age = tabPatient.etAge.getText().toString();
                String gender = "male";
                if (tabPatient.rbFemale.isChecked()) {
                    gender = "female";
                }
                String dept = tabPatient.etDept.getText().toString();
                String ward = tabPatient.etWard.getText().toString();
                String room = tabPatient.etRoom.getText().toString();
                String bed = tabPatient.etBed.getText().toString();


                //medical tab
                String lastMeal = tabMedical.etLastMeal.getText().toString();
//                if((lastMeal.charAt(10)) == '0'){
//                    lastMeal = "0" + 0 + lastMeal.substring(11,1);
//                    Log.i("last meal time format:",lastMeal);
//
//                }else{
//                    lastMeal = lastMeal.substring(10,2)+lastMeal.substring(13,2);
//                    Log.i("last meal time format:",lastMeal);
//                }

                String lastFluid = tabMedical.etLastFluid.getText().toString();
//                if((lastFluid.charAt(10)) == '0'){
//                    lastFluid = "0" + lastFluid.charAt(10) + lastFluid.substring(11,12);
//                    Log.i("last fluid time format",lastFluid);
//
//                }else{
//                    lastFluid = lastFluid.substring(9,10)+lastFluid.substring(12,13);
//                    Log.i("last fluid time format",lastFluid);
//                }
                String typeOfAnaesthesia = "";
                if (tabMedical.cbGA.isChecked()) {
                    typeOfAnaesthesia += "GA ";
                }
                if (tabMedical.cbRA.isChecked()) {
                    typeOfAnaesthesia += "RA ";
                }
                if (tabMedical.cbLA.isChecked()) {
                    typeOfAnaesthesia += "LA ";
                }
                if (tabMedical.cbIV.isChecked()) {
                    typeOfAnaesthesia += "IV Sedation ";
                }
                if (tabMedical.cbOthers.isChecked()) {
                    typeOfAnaesthesia += "Others: " + tabMedical.etOthers.getText().toString();
                }
                if (tabMedical.cbNone.isChecked()) {
                    typeOfAnaesthesia += "None ";
                }
                String preOp = tabMedical.etPreOp.getText().toString();
                String contact = "None";
                String blood = "None";
                String airBorne = "None";
                String otherHighRisk = "None";
                if (tabMedical.toggleButton.isChecked()) {
                    contact = tabMedical.spContact.getSelectedItem().toString();
                    blood = tabMedical.spBlood.getSelectedItem().toString();
                    airBorne = tabMedical.spAirBorne.getSelectedItem().toString();
                    otherHighRisk = tabMedical.spOthers.getSelectedItem().toString();
                }
                String location = tabMedical.spLocation.getSelectedItem().toString();
                String ot = tabMedical.spOT.getSelectedItem().toString();


                //assign part;
                String doctor = tabAssign.tvDoctor.getText().toString();
                String table = tabAssign.tvTable.getText().toString();

                //Time
                Calendar c = Calendar.getInstance();

                int hours = c.get(Calendar.HOUR_OF_DAY);
                int minutes = c.get(Calendar.MINUTE);
                String time = "";

                if (hours < 10) {
                    time += "0" + String.valueOf(hours);
                } else {
                    time += String.valueOf(hours);
                }
                if (minutes < 10) {
                    time += "0" + String.valueOf(minutes);
                } else {
                    time += String.valueOf(minutes);

                }

                Log.i("chit submission", time);


                //put them all into object;
                PatientAndMedicalDetail patientAndMedicalDetail
                        = new PatientAndMedicalDetail(mrin, account, name, age, gender,
                        lastMeal, lastFluid, lifeThreating, typeOfAnaesthesia, preOp,
                        contact, blood, airBorne, otherHighRisk, doctor, location, ot,
                        time, dept, ward, room, bed, table, null, null, -1);
                //firebase part;

//                String idFbChit = databaseReferenceChit.push().getKey();
//                assert idFbChit != null;
//                databaseReferenceChit.child(idFbChit).setValue(patientAndMedicalDetail);

                databaseReferenceChit.child(patientDetail.getIdFB()).setValue(patientAndMedicalDetail);


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_patient, menu);
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    tabPatient = new TabPatient();
                    return tabPatient;
                case 1:
                    tabMedical = new TabMedical();
                    return tabMedical;
                case 2:
                    tabAssign = new TabAssign();
                    return tabAssign;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
