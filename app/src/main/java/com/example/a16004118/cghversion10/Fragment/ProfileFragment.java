package com.example.a16004118.cghversion10.Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a16004118.cghversion10.Activities.HomeActivity;
import com.example.a16004118.cghversion10.ObjectPackage.Doctor;
import com.example.a16004118.cghversion10.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView tvName, tvId, tvAge, tvJob;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        tvName = v.findViewById(R.id.tvName);
        tvId = v.findViewById(R.id.tvId);
        tvAge = v.findViewById(R.id.tvAge);
        tvJob = v.findViewById(R.id.tvJob);
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(getContext());

        Gson gson = new GsonBuilder().setLenient().create();
        String doctorString = prefs.getString("doctorString", null);
        Doctor currentDoctor = gson.fromJson(doctorString, Doctor.class);
        Log.i("ProfileFragment","doctor idfb: "+currentDoctor.getIdFB());
        tvName.setText("Name: "+currentDoctor.getName());
        tvId.setText("Id: "+currentDoctor.getSurgeronId());
        tvAge.setText("Pager: "+currentDoctor.getPager());
        tvJob.setText("Rank: "+currentDoctor.getRank());
        return v;
    }

}
