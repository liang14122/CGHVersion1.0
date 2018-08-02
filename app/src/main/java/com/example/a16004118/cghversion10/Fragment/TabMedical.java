package com.example.a16004118.cghversion10.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;


import com.example.a16004118.cghversion10.R;

import java.util.Calendar;

public class TabMedical extends Fragment {
    public EditText etLastMeal, etLastFluid, etOthers, etPreOp;
    public CheckBox cbGA, cbRA, cbLA, cbIV, cbNone, cbOthers;
    public ToggleButton toggleButton;
    public Spinner spContact, spBlood, spAirBorne, spOthers, spLocation, spOT;
    String day;
    String month;
    String yearString;
    String hour;
    String minuteString;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_medical, container, false);
        etLastMeal = rootView.findViewById(R.id.etLastMeal);
        etLastFluid = rootView.findViewById(R.id.etLastFluid);
        etOthers = rootView.findViewById(R.id.etOthers);
        etPreOp = rootView.findViewById(R.id.etPreOp);
        cbGA = rootView.findViewById(R.id.cbGA);
        cbRA = rootView.findViewById(R.id.cbRA);
        cbLA = rootView.findViewById(R.id.cbLA);
        cbIV = rootView.findViewById(R.id.cbIV);
        cbNone = rootView.findViewById(R.id.cbNone);
        cbOthers = rootView.findViewById(R.id.cbOthers);
        toggleButton = rootView.findViewById(R.id.toggleButton);
        spContact = rootView.findViewById(R.id.spContact);
        spBlood = rootView.findViewById(R.id.spBlood);
        spAirBorne = rootView.findViewById(R.id.spAirBorne);
        spOthers = rootView.findViewById(R.id.spOthers);
        spLocation = rootView.findViewById(R.id.spLocation);
        spOT = rootView.findViewById(R.id.spOT);
        //TODO
//
//        etLastMeal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                final Calendar now = Calendar.getInstance();
//                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        day = dayOfMonth + "";
//                        month = monthOfYear + "";
//                        yearString = year + "";
//                        //set time
//                        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                String minuteString = "";
//                                String hour = "";
//                                if(minute < 10){
//                                    minuteString = "0" + minute + "";
//                                }else{
//                                    minuteString = "0" + minute + "";
//                                }
//
//                                if(hourOfDay < 10){
//                                    hour = "0" + hourOfDay + "";
//                                }else{
//                                    hour = hourOfDay + "";
//                                }
//
//                                etLastMeal.setText(day + "/" + month + "/" + yearString  + " " + hour + ":" + minuteString);
//                            }
//                        };
//
//                        // Create the Time Picker Dialog
//                        /*TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
//                        myTimeListener, 20, 00, true);*/
//                        ;
//                        int hour = now.get(Calendar.HOUR_OF_DAY);
//                        int min = now.get(Calendar.MINUTE);
//                        TimePickerDialog myTimeDialog = new TimePickerDialog(getContext(), myTimeListener , hour,min,true);
//                        myTimeDialog.show();
//
//                    }
//                };
//                int Year = now.get(Calendar.YEAR);
//                int Month = now.get(Calendar.MONTH);
//                int Day = now.get(Calendar.DAY_OF_MONTH);
//
//                // Create the Date Picker Dialog
//                DatePickerDialog myDateDialog = new DatePickerDialog(getContext(),
//                        myDateListener, Year, Month, Day);
//
//                myDateDialog.show();
//
//            }
//        });
//        etLastFluid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                final Calendar now = Calendar.getInstance();
//                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        day = dayOfMonth + "";
//                        month = monthOfYear + "";
//                        yearString = year + "";
//                        //set time
//                        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//                                String hour = hourOfDay + "";
//                                String minuteString = minute + "";
//                                etLastFluid.setText(day + "/" + month + "/" + yearString  + " " + hour + ":" + minuteString);
//                            }
//                        };
//
//                        // Create the Time Picker Dialog
//                        /*TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
//                        myTimeListener, 20, 00, true);*/
//                        ;
//                        int hour = now.get(Calendar.HOUR_OF_DAY);
//                        int min = now.get(Calendar.MINUTE);
//                        TimePickerDialog myTimeDialog = new TimePickerDialog(getContext(), myTimeListener , hour,min,true);
//                        myTimeDialog.show();
//
//                    }
//                };
//                int Year = now.get(Calendar.YEAR);
//                int Month = now.get(Calendar.MONTH);
//                int Day = now.get(Calendar.DAY_OF_MONTH);
//
//                // Create the Date Picker Dialog
//                DatePickerDialog myDateDialog = new DatePickerDialog(getContext(),
//                        myDateListener, Year, Month, Day);
//
//                myDateDialog.show();
//
//            }
//        });

        return rootView;
    }


}
