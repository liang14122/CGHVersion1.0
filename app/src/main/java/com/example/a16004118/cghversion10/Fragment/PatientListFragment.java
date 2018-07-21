package com.example.a16004118.cghversion10.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.a16004118.cghversion10.Adapter.PatientRecyclerViewAdapter;
import com.example.a16004118.cghversion10.Adapter.PatientTouchHelperCallback;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class PatientListFragment extends Fragment {
    ImageButton emergency,nonEmergency,viewAll;
    ArrayList<PatientAndMedicalDetail> alPatient = new ArrayList<>();
    DatabaseReference databaseReferenceChit;
    RecyclerView rvPatientList;
    PatientRecyclerViewAdapter pla;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_patient_list, container, false);

        Objects.requireNonNull(getActivity()).setTitle("Patient List");

        emergency = view.findViewById(R.id.imageButton2);
        nonEmergency = view.findViewById(R.id.imageButton3);
        viewAll = view.findViewById(R.id.imageButton4);
        rvPatientList = view.findViewById(R.id.rvPatientList);

//        currentArrayList = arrayListForAll;
        //setAdapterTry(currentArrayList);
        pla = new PatientRecyclerViewAdapter(alPatient);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rvPatientList.setLayoutManager(layoutManager);
        rvPatientList.setItemAnimator(new DefaultItemAnimator());
        rvPatientList.setAdapter(pla);

        ItemTouchHelper.Callback callback = new PatientTouchHelperCallback(pla);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rvPatientList);
        rvPatientList.setAdapter(pla);

//        lvPatientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String idFB = arrayListForAll.get(position).getIdFB();
//                Intent i = new Intent(view.getContext(), PatientMedicalDetailActivity.class);
//                i.putExtra("idFB", idFB);
//                startActivity(i);
//            }
//        });

        databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion01").child("chit");

        databaseReferenceChit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one
                alPatient.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Menu page", "Finding...");

                    PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                    alPatient.add(current);

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
                alPatient.clear();

                databaseReferenceChit.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Menu page", "Finding...");

                            PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                            assert current != null;
                            if(current.getLifeThreating())
                            alPatient.add(current);
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

                alPatient.clear();

                databaseReferenceChit.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Menu page", "Finding...");

                            PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                            assert current != null;
                            if(!current.getLifeThreating())
                                alPatient.add(current);
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
                alPatient.clear();

                databaseReferenceChit.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Menu page", "Finding...");

                            PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                            alPatient.add(current);
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
