package com.example.a16004118.cghversion10.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.a16004118.cghversion10.R;

public class TabPatient extends Fragment {
    public RadioGroup rg, rg1;
    public RadioButton rbEmergency, rbNonEmergency, rbMale, rbFemale;
    public EditText etName, etMRIN, etAccount, etAge, etDept, etWard, etRoom, etBed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patient, container, false);
        rg = rootView.findViewById(R.id.radioGroup);
        rbEmergency = rootView.findViewById(R.id.rbEmergency);
        rbNonEmergency = rootView.findViewById(R.id.rbNonEmergency);
        etName = rootView.findViewById(R.id.etName);
        etMRIN = rootView.findViewById(R.id.etMRIN);
        etAccount = rootView.findViewById(R.id.etAccount);
        etAge = rootView.findViewById(R.id.etAge);
        etDept = rootView.findViewById(R.id.etDept);
        etWard = rootView.findViewById(R.id.etWard);
        etRoom = rootView.findViewById(R.id.etRoom);
        etBed = rootView.findViewById(R.id.etBed);
        rg1 = rootView.findViewById(R.id.radioGroup1);
        rbFemale = rootView.findViewById(R.id.rbFemale);
        rbMale = rootView.findViewById(R.id.rbMale);
        rg.check(R.id.rbNonEmergency);
        rg1.check(R.id.rbMale);
        return rootView;
    }


}
