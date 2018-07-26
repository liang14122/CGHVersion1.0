package com.example.a16004118.cghversion10.Activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import com.example.a16004118.cghversion10.Fragment.TabAssign;
import com.example.a16004118.cghversion10.Fragment.TabMedical;
import com.example.a16004118.cghversion10.Fragment.TabPatient;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddPatient extends AppCompatActivity {
    Button btnUpload;

    private TabAssign tabAssign;
    private TabMedical tabMedical;
    private TabPatient tabPatient;
    DatabaseReference databaseReferenceChit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion20").child("Chit");



        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        /*
      The {@link ViewPager} that will host the section contents.
     */
        ViewPager mViewPager =  findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout =  findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });
        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //patient tab
                String name = tabPatient.etName.getText().toString();
                Boolean lifeThreating = true;
                if(tabPatient.rbNonEmergency.isChecked()){
                    lifeThreating = false;
                }
                String mrin = tabPatient.etMRIN.getText().toString();
                String account = tabPatient.etAccount.getText().toString();
                String age = tabPatient.etAge.getText().toString();
                String gender = "male";
                if(tabPatient.rbFemale.isChecked()){
                    gender = "female";
                }
                String dept = tabPatient.etDept.getText().toString();
                String ward = tabPatient.etWard.getText().toString();
                String room = tabPatient.etRoom.getText().toString();
                String bed = tabPatient.etBed.getText().toString();


                //medical tab
                String lastMeal = tabMedical.etLastMeal.getText().toString();
                if((lastMeal.charAt(0)) == '0'){
                    lastMeal = "0" + 0 + lastMeal.substring(2,3);
                }

                String lastFluid = tabMedical.etLastFluid.getText().toString();
                String typeOfAnaesthesia = "";
                if(tabMedical.cbGA.isChecked()){
                    typeOfAnaesthesia += "GA ";
                }
                if(tabMedical.cbRA.isChecked()){
                    typeOfAnaesthesia += "RA ";
                }
                if(tabMedical.cbLA.isChecked()){
                    typeOfAnaesthesia += "LA ";
                }
                if(tabMedical.cbIV.isChecked()){
                    typeOfAnaesthesia += "IV Sedation ";
                }
                if(tabMedical.cbOthers.isChecked()){
                    typeOfAnaesthesia += "Others: "+tabMedical.etOthers.getText().toString();
                }
                if(tabMedical.cbNone.isChecked()){
                    typeOfAnaesthesia += "None ";
                }
                String preOp = tabMedical.etPreOp.getText().toString();
                String contact = "None";
                String blood = "None";
                String airBorne = "None";
                String otherHighRisk = "None";
                if(tabMedical.toggleButton.isChecked()){
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
                int hour = Calendar.getInstance().get(Calendar.HOUR);
                int min = Calendar.getInstance().get(Calendar.MINUTE);
                String time = "";
                if(hour < 10){
                    time = 0 + "" + hour + min;
                }else{
                    time = hour + min + "";
                }

                //put them all into object;
                PatientAndMedicalDetail patientAndMedicalDetail
                        = new PatientAndMedicalDetail(mrin, account, name, age, gender,
                        lastMeal, lastFluid, lifeThreating, typeOfAnaesthesia, preOp,
                        contact, blood, airBorne, otherHighRisk, doctor, location, ot,
                        time,dept, ward, room, bed, table, null);
                //firebase part;

                String idFbChit = databaseReferenceChit.push().getKey();
                assert idFbChit != null;
                databaseReferenceChit.child(idFbChit).setValue(patientAndMedicalDetail);

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
            switch (position){
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
