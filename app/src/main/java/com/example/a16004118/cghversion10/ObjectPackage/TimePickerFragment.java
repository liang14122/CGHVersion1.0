package com.example.a16004118.cghversion10.ObjectPackage;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    EditText etAdmission;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        //etAdmission=(EditText) getActivity().findViewById(R.id.etAdmission);
        //etAdmission.setText(hourOfDay + ":" + minute );

        /*
        final Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        int eatH = hours - view.getCurrentHour();
        int eatM = minutes - view.getCurrentMinute();
        if (eatM < 0){
            eatH = eatH - 1;
            eatM = eatM + 60;
        }
        TextView tvHours=(TextView) getActivity().findViewById(R.id.tvHours);
        tvHours.setText(eatH+" Hours "+eatM+" Minutes");*/

    }
}
