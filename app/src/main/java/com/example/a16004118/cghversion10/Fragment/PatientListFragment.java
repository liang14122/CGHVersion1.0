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
    ImageButton emergency, nonEmergency, viewAll;
    ArrayList<PatientAndMedicalDetail> alPatient;
    ArrayList<PatientAndMedicalDetail> alRawPatient;
    ArrayList<String> patientIdArr;
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
        alPatient = new ArrayList<>();
        alRawPatient = new ArrayList<>();
        patientIdArr = new ArrayList<>();

        emergency = view.findViewById(R.id.imageButton2);
        nonEmergency = view.findViewById(R.id.imageButton3);
        viewAll = view.findViewById(R.id.imageButton4);
        rvPatientList = view.findViewById(R.id.rvPatientList);

//        viewAll.performClick();


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
//                alRawPatient.clear();
                alPatient.clear();
                Log.i("Menu page", String.valueOf(dataSnapshot));
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Menu page", String.valueOf(child));

                    PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                    assert current != null;
                    current.setIdFB(child.getKey());
//                    alRawPatient.add(current);
//                    patientIdArr.add(child.getKey());
                    alPatient.add(current);
                }
//                alPatient.addAll(sort(alRawPatient, patientIdArr));
                pla.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        });

        emergency.setOnClickListener(new View.OnClickListener() {
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
                            assert current != null;
                            if (current.getLifeThreating()) {
                                alPatient.add(current);
                            }
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
                        Log.i("Menu page", String.valueOf(dataSnapshot));
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i("Menu page", String.valueOf(child));
                            PatientAndMedicalDetail current = child.getValue(PatientAndMedicalDetail.class);
                            assert current != null;
                            if (!current.getLifeThreating()) {
                                alPatient.add(current);
                            }
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
                alPatient.addAll(sort(alRawPatient, patientIdArr));
                Log.i("PatientList", "name: " + alPatient.get(0).getName());
                Log.i("Patient list", "Size of alPatient " + alPatient.size());
                pla.notifyDataSetChanged();

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

    private ArrayList<PatientAndMedicalDetail> sort(ArrayList<PatientAndMedicalDetail> alrPatient, ArrayList<String> patientIdArr) {

        Alogrithm alogrithm = new Alogrithm(alrPatient, patientIdArr);

        ArrayList<String> arrayList = alogrithm.getMap();
        Log.i("soutFunction", "size of arr " + arrayList.toString());
        Log.i("soutFunction", "size of patientIdArr " + patientIdArr.toString());

        ArrayList<PatientAndMedicalDetail> alSorted = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            String idFB = arrayList.get(i);
            for (int a = 0; a < alRawPatient.size(); a++) {
                PatientAndMedicalDetail current = alRawPatient.get(a);
                if (idFB.equals(patientIdArr.get(a))) {
                    alSorted.add(current);
                    Log.i("alSorted", "add one " + idFB);
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
        Log.i("sort function", "size of sorted " + alSorted.size());
        return alSorted;
    }

    @Override
    public void startDragItem(RecyclerView.ViewHolder holder) {
        touchHelper.startDrag(holder);
    }
}
