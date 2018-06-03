package com.example.a16004118.cghversion10.Activities;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a16004118.cghversion10.ObjectPackage.AdmissionDetail;
import com.example.a16004118.cghversion10.ObjectPackage.Chit;
import com.example.a16004118.cghversion10.ObjectPackage.Consent;
import com.example.a16004118.cghversion10.ObjectPackage.Investigations;
import com.example.a16004118.cghversion10.ObjectPackage.Issues;
import com.example.a16004118.cghversion10.ObjectPackage.Notification;
import com.example.a16004118.cghversion10.ObjectPackage.Patient;
import com.example.a16004118.cghversion10.ObjectPackage.SurgeryDetails;
import com.example.a16004118.cghversion10.ObjectPackage.TimePickerFragment;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class AddPatientActivity extends AppCompatActivity {
    DatabaseReference databaseReferenceChit,databaseReferenceNotification;

    EditText etPODoagnosos,etDOA, etAdmission, etLastMeal, etLastClearFluid, etBloodCount, etRenalPenal, etGXM, etECG, etPTPTT, etCXR, etOthers, etAType, etOCIC, etCICR, etRIC, etRICR, etORA, etOTD, etLowest, etSNature, etEstTime, etPosition, etImplants;
    Spinner dSconsent, dAconsent, dImaging, dRepeat, dot;
    RadioGroup rgEMG, rgThreat;
    Button btnAdd;

    String day;
    String month;
    String yearString;
    String hour;
    String minuteString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        initialize();

        databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion01").child("chit");

        databaseReferenceNotification = FirebaseDatabase.getInstance().getReference("cghversion01").child("notification");

        etDOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        etDOA.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };
                int Year = now.get(Calendar.YEAR);
                int Month = now.get(Calendar.MONTH);
                int Day = now.get(Calendar.DAY_OF_MONTH);

                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(AddPatientActivity.this,
                        myDateListener, Year, Month, Day);

                myDateDialog.show();
            }
        });

        etLastMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar now = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        day = dayOfMonth + "";
                        month = monthOfYear + "";
                        yearString = year + "";
                        //set time
                        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                hour = hourOfDay + "";
                                minuteString = minute + "";
                                etLastMeal.setText(day + "/" + month + "/" + yearString  + " " + hour + ":" + minuteString);
                            }
                        };

                        // Create the Time Picker Dialog
                        /*TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, 20, 00, true);*/
                        ;
                        int hour = now.get(Calendar.HOUR_OF_DAY);
                        int min = now.get(Calendar.MINUTE);
                        TimePickerDialog myTimeDialog = new TimePickerDialog(AddPatientActivity.this, myTimeListener , hour,min,true);
                        myTimeDialog.show();

                    }
                };
                int Year = now.get(Calendar.YEAR);
                int Month = now.get(Calendar.MONTH);
                int Day = now.get(Calendar.DAY_OF_MONTH);

                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(AddPatientActivity.this,
                        myDateListener, Year, Month, Day);

                myDateDialog.show();

            }
        });

        etLastClearFluid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar now = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        day = dayOfMonth + "";
                        month = monthOfYear + "";
                        yearString = year + "";
                        //set time
                        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                hour = hourOfDay + "";
                                minuteString = minute + "";
                                etLastClearFluid.setText(day + "/" + month + "/" + yearString  + " " + hour + ":" + minuteString);
                            }
                        };

                        // Create the Time Picker Dialog
                        /*TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, 20, 00, true);*/
                        ;
                        int hour = now.get(Calendar.HOUR_OF_DAY);
                        int min = now.get(Calendar.MINUTE);
                        TimePickerDialog myTimeDialog = new TimePickerDialog(AddPatientActivity.this, myTimeListener , hour,min,true);
                        myTimeDialog.show();

                    }
                };
                int Year = now.get(Calendar.YEAR);
                int Month = now.get(Calendar.MONTH);
                int Day = now.get(Calendar.DAY_OF_MONTH);

                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(AddPatientActivity.this,
                        myDateListener, Year, Month, Day);

                myDateDialog.show();

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String DOA = etDOA.getText().toString();
                        String LastMeal = etLastMeal.getText().toString();
                        String LastClearFluid = etLastClearFluid.getText().toString();
                        String Admission = etAdmission.getText().toString();
                        String BloodCount = etBloodCount.getText().toString();
                        String RenalPenal = etRenalPenal.getText().toString();
                        String GXM = etGXM.getText().toString();
                        String ECG = etECG.getText().toString();
                        String PTPTT = etPTPTT.getText().toString();
                        String CXR = etCXR.getText().toString();
                        String Others = etOthers.getText().toString();
                        String AType = etAType.getText().toString();
                        String OCIC = etOCIC.getText().toString();
                        String CICR = etCICR.getText().toString();
                        String RIC = etRIC.getText().toString();
                        String RICR = etRICR.getText().toString();
                        String ORA = etORA.getText().toString();
                        String OTD = etOTD.getText().toString();
                        String Lowest = etLowest.getText().toString();
                        String SNature = etSNature.getText().toString();
                        String EstTime = etEstTime.getText().toString();
                        String Position = etPosition.getText().toString();
                        String Implants = etImplants.getText().toString();
                        String pODoagnosos = etPODoagnosos.getText().toString();
                        RadioButton rbEMG = findViewById(rgEMG.getCheckedRadioButtonId());
                        String EMG = rbEMG.getText().toString();
                        RadioButton rbThreat = findViewById(rgThreat.getCheckedRadioButtonId());
                        String Threat = rbThreat.getText().toString();

                        String SConsent = dSconsent.getSelectedItem().toString();
                        String AConsent = dAconsent.getSelectedItem().toString();
                        String Imaging = dImaging.getSelectedItem().toString();
                        String Repeat = dRepeat.getSelectedItem().toString();
                        String ot = dot.getSelectedItem().toString();

                        String idFbChit = databaseReferenceChit.push().getKey();
                        AdmissionDetail admissionDetail = new AdmissionDetail(DOA,Admission, LastMeal,LastClearFluid);
                        //dsConsent,dAconsent
                        Consent consent = new Consent("","",Others);
                        Investigations investigations = new Investigations(BloodCount,RenalPenal,GXM,PTPTT,CXR,ECG);

                        Issues issues = new Issues(pODoagnosos,"","");
                        Patient patient = new Patient("","","","","","","","","","","");
                        SurgeryDetails surgeryDetails = new SurgeryDetails(AType,OCIC,CICR,RIC,RICR,ORA,OTD,Lowest,SNature,EstTime,Position,Implants,Imaging,Repeat,"");
                        Chit chit = new Chit(idFbChit,patient,issues,admissionDetail,investigations,consent,"doctorId",surgeryDetails,"surgicalTable",true);
                        databaseReferenceChit.child(idFbChit).setValue(chit);


                        //notification part;
                        String idFBN = databaseReferenceNotification.push().getKey();
                        Date currentTime = Calendar.getInstance().getTime();
                        String currentTimeS = String.valueOf(currentTime);
                        Notification notification = new Notification(idFBN,"doctor Id","patient name","fin","setence",currentTimeS,false,false);
                        databaseReferenceNotification.child(idFBN).setValue(notification);
                        finish();
                    }
                });
            }
        });
    }


    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private void initialize(){
        etDOA = (EditText)findViewById(R.id.etDoA);
        etLastMeal = (EditText)findViewById(R.id.etLastMeal);
        etLastClearFluid = (EditText)findViewById(R.id.etLastClearFluid);
        etAdmission = (EditText)findViewById(R.id.etAdmission);
        etBloodCount = (EditText)findViewById(R.id.etBloodCount);
        etRenalPenal = (EditText)findViewById(R.id.etRenalPenal);
        etGXM = (EditText)findViewById(R.id.etGXM);
        etECG = (EditText)findViewById(R.id.etECG);
        etPTPTT = (EditText)findViewById(R.id.etPTPTT);
        etCXR = (EditText)findViewById(R.id.etCXR);
        etOthers = (EditText)findViewById(R.id.etOthers);
        etAType = (EditText)findViewById(R.id.etAType);
        etOCIC = (EditText)findViewById(R.id.etOCIC);
        etCICR = (EditText)findViewById(R.id.etCICR);
        etRIC = (EditText)findViewById(R.id.etRIC);
        etRICR = (EditText)findViewById(R.id.etRICR);
        etORA = (EditText)findViewById(R.id.etORA);
        etOTD = (EditText)findViewById(R.id.etOTD);
        etLowest = (EditText)findViewById(R.id.etLowest);
        etSNature = (EditText)findViewById(R.id.etSNature);
        etEstTime = (EditText)findViewById(R.id.etEstTime);
        etPosition = (EditText)findViewById(R.id.etPosition);
        etImplants = (EditText)findViewById(R.id.etImplants);
        etPODoagnosos = (EditText)findViewById(R.id.editTextPODiagnosis);
        rgEMG = (RadioGroup)findViewById(R.id.rgEMG);
        rgThreat = (RadioGroup)findViewById(R.id.rgThreat);

        dSconsent = (Spinner)findViewById(R.id.dSConsent);
        dAconsent = (Spinner)findViewById(R.id.dAConsent);
        dImaging = (Spinner)findViewById(R.id.dImaging);
        dRepeat = (Spinner)findViewById(R.id.dRepeat);
        dot = (Spinner)findViewById(R.id.dot);

        btnAdd = (Button)findViewById(R.id.buttonAdd);
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.medical_detail_action_bar_button, menu);
//        return true;
//    }

}
