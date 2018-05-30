package com.example.a16004118.cghversion10.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.a16004118.cghversion10.ObjectPackage.Chit;
import com.example.a16004118.cghversion10.ObjectPackage.Notification;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientListFragment extends Fragment {
    ImageButton emergency,nonEmergency,viewAll;
    ArrayList<Chit> arrayListForAll;
    ArrayList<Chit> currentArrayList;
    DatabaseReference databaseReferenceChit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_list, container, false);
        emergency = view.findViewById(R.id.imageButton2);
        nonEmergency = view.findViewById(R.id.imageButton3);
        viewAll = view.findViewById(R.id.imageButton4);
        arrayListForAll = new ArrayList<>();
        currentArrayList = new ArrayList<>();
        databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion01").child("chit");
        databaseReferenceChit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one
                arrayListForAll.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Menu page", "Finding...");

                    Chit current = child.getValue(Chit.class);
                    arrayListForAll.add(current);
                }
                //listView.setAdapter(notificationAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());

            }
        });
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentArrayList = getCurrentArrayList(false, true);
            }
        });
        nonEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentArrayList = getCurrentArrayList(false, false);

            }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentArrayList = getCurrentArrayList(true, true);
            }
        });
        return view;
    }

    public ArrayList<Chit> getCurrentArrayList(boolean viewAll, boolean emergency) {
        currentArrayList.clear();
        ArrayList<Chit> arrayList = new ArrayList<>();
        if(viewAll){
            return arrayListForAll;
        }
        for(int i = 0 ; i<arrayListForAll.size(); i++){
            if(arrayListForAll.get(i).getLifeThreatening()==emergency){
                arrayList.add(arrayListForAll.get(i));
            }
        }
        return arrayList;
    }
}
