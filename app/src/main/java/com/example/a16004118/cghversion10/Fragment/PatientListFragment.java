package com.example.a16004118.cghversion10.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.a16004118.cghversion10.Alogrithm.Alogrithm;
import com.example.a16004118.cghversion10.Alogrithm.Sort;
import com.example.a16004118.cghversion10.Interface.ItemTouchHelper;
import com.example.a16004118.cghversion10.Adapter.PatientAdapter;
import com.example.a16004118.cghversion10.Adapter.RecyclerViewTouchHelperCallback;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class PatientListFragment extends Fragment implements ItemTouchHelper {
    ImageButton emergency,nonEmergency,viewAll;
    ArrayList<PatientAndMedicalDetail> alPatient = new ArrayList<>();
    ArrayList<PatientAndMedicalDetail> alRawPatient = new ArrayList<>();
    DatabaseReference databaseReferenceChit;
    RecyclerView rvPatientList;
    PatientAdapter pla;
    private android.support.v7.widget.helper.ItemTouchHelper touchHelper;

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

        viewAll.performClick();



        pla = new PatientAdapter(alPatient);
        pla.setDragListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rvPatientList.setLayoutManager(layoutManager);
        rvPatientList.setItemAnimator(new DefaultItemAnimator());
        rvPatientList.setAdapter(pla);

        android.support.v7.widget.helper.ItemTouchHelper.Callback callback = new RecyclerViewTouchHelperCallback(pla, alPatient);
        touchHelper = new android.support.v7.widget.helper.ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rvPatientList);

        databaseReferenceChit = FirebaseDatabase.getInstance().getReference("cghversion20").child("Chit");

        databaseReferenceChit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //check for each one
                alPatient.clear();
                alRawPatient.clear();
                ArrayList<String> patientIdArr = new ArrayList<>();
                Log.i("Menu page", String.valueOf(dataSnapshot));
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Menu page", String.valueOf(child));

                    PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);

                    assert current != null;
                    current.setIdFB(child.getKey());
//                    alRawPatient.add(current);
//                    alPatient = sort(alRawPatient);
                    alRawPatient.add(current);
                    patientIdArr.add(child.getKey());

                }
                //after using the scoring system, use this
//                for(int i = 0; i<alPatient.size(); i++){
//                    PatientAndMedicalDetail current = alPatient.get(i);
//                    if(current.getIndex()!=-1){
//                        alPatient.remove(i);
//                        alPatient.add(current.getIndex(),current);
//                    }
//                }

                alPatient = sort(alRawPatient, patientIdArr);
                Log.i("PatientList size",""+alPatient.get(0).getName());
                pla.notifyDataSetChanged();
                //listView.setAdapter(notificationAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("emergency", "Finding...");

                            PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                            assert current != null;
                            if(current.getLifeThreating())
                            alPatient.add(current);
                        }
                        pla.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Non-emer", "Finding...");

                            PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                            assert current != null;
                            if(!current.getLifeThreating())
                                alPatient.add(current);
                        }
                        pla.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.i("Menu page", String.valueOf(dataSnapshot));
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Menu page", String.valueOf(child));

                            PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                            alPatient.add(current);
                        }
                        pla.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        return view;
    }

    private ArrayList<PatientAndMedicalDetail> sort(ArrayList<PatientAndMedicalDetail> alPatient, ArrayList<String> patientIdArr) {

        Alogrithm alogrithm = new Alogrithm(alPatient,patientIdArr);

        ArrayList<String> arrayList = alogrithm.getMap();

        ArrayList<PatientAndMedicalDetail> alSorted = new ArrayList<PatientAndMedicalDetail>();

        for(int i = 0; i< arrayList.size(); i++){
            String idFB = arrayList.get(i);
            for(int a = 0; a<alPatient.size(); a++){
                PatientAndMedicalDetail current = alPatient.get(i);
                if(idFB.equals(patientIdArr.get(i))){
                    alSorted.add(current);
                }
            }
        }
//        Map<String, Integer> mapUnsort = alogrithm.getMap();
//        Sort sort = new Sort();
//
//        Map<String, Integer> mapSort = sort.sortByValue(mapUnsort);
//
//        for (Map.Entry<String, Integer> entry : mapSort.entrySet()) {
//
//
//            Log.e("TAG", "Key : " + entry.getKey()
//                    + " Value : " + entry.getValue());
//
//            for (int i = 0; i< alPatient.size(); i++){
//                PatientAndMedicalDetail currentPatient = alPatient.get(i);
//                if (currentPatient.getIdFB().equals(entry.getValue())){
//                    alSorted.add(currentPatient);
//                }
//            }
//        }
        return alSorted;
    }

    @Override
    public void startDragItem(RecyclerView.ViewHolder holder) {
        touchHelper.startDrag(holder);
    }
}
