package com.example.a16004118.cghversion10.Fragment;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Toast;

import com.example.a16004118.cghversion10.Activities.MedicalDetailActivity;
import com.example.a16004118.cghversion10.Adapter.PatientListAdapter;
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
    ArrayList<Chit> arrayListForAll = new ArrayList<>();
    ArrayList<Chit> currentArrayList = new ArrayList<>();
    DatabaseReference databaseReferenceChit;
    ListView lvPatientList;
    PatientListAdapter pla;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_patient_list, container, false);

        emergency = view.findViewById(R.id.imageButton2);
        nonEmergency = view.findViewById(R.id.imageButton3);
        viewAll = view.findViewById(R.id.imageButton4);
        lvPatientList = view.findViewById(R.id.lvPatientList);


        currentArrayList = arrayListForAll;
        //setAdapterTry(currentArrayList);
        pla = new PatientListAdapter(view.getContext(), R.layout.patient_list_row, currentArrayList);
        lvPatientList.setAdapter(pla);

        lvPatientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chit currentChit = arrayListForAll.get(position);
                Intent i = new Intent(getContext(), MedicalDetailActivity.class);
                i.putExtra("currentChit", currentChit);
                startActivity(i);
            }
        });

        databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion01").child("chit");

        databaseReferenceChit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one
                //arrayListForAll.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Menu page", "Finding...");

                    Chit current = child.getValue(Chit.class);
                    arrayListForAll.add(current);

                }
                pla.notifyDataSetChanged();
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
//                currentArrayList = getCurrentArrayList(false, true);
                //setAdapterTry(currentArrayList);
                arrayListForAll.clear();

                databaseReferenceChit.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Menu page", "Finding...");

                            Chit current = child.getValue(Chit.class);
                            if(current.getLifeThreatening()==true)
                            currentArrayList.add(current);
                        }
                        pla.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        nonEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayListForAll.clear();

                databaseReferenceChit.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Menu page", "Finding...");

                            Chit current = child.getValue(Chit.class);
                            if(current.getLifeThreatening()==false)
                                currentArrayList.add(current);
                        }
                        pla.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListForAll.clear();

                databaseReferenceChit.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Menu page", "Finding...");

                            Chit current = child.getValue(Chit.class);
                            currentArrayList.add(current);
                        }
                        pla.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;

    }

//    public ArrayList<Chit> getCurrentArrayList(Boolean viewAll, Boolean emergencyBoolean) {
//        currentArrayList.clear();
//        ArrayList<Chit> arrayList = new ArrayList<>();
//        if(viewAll == true){
//            Toast.makeText(getContext(),"view all == : "+viewAll+arrayListForAll.size(),Toast.LENGTH_LONG).show();
//
//            arrayList =  arrayListForAll;
//
//        }else {
//            Toast.makeText(getContext(),"filter == "+ emergencyBoolean+": "+currentArrayList.size(),Toast.LENGTH_LONG).show();
//
//            for (int i = 0; i < arrayListForAll.size(); i++) {
//                if (arrayListForAll.get(i).getLifeThreatening() == emergencyBoolean) {
//                    arrayList.add(arrayListForAll.get(i));
//                }
//            }
//        }
//        return arrayList;
//    }
//    public void setAdapterTry(ArrayList<Chit> arrayList){
//        pla = new PatientListAdapter(getContext(), R.layout.patient_list_row, arrayList);
//        lvPatientList.setAdapter(pla);
//    }

}
