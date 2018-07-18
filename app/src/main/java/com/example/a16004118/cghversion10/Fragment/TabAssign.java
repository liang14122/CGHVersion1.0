package com.example.a16004118.cghversion10.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a16004118.cghversion10.Adapter.DoctorAdapter;
import com.example.a16004118.cghversion10.Adapter.TableAdapter;
import com.example.a16004118.cghversion10.ObjectPackage.Doctor;
import com.example.a16004118.cghversion10.ObjectPackage.SurgicalTable;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TabAssign extends Fragment {
    public ListView lvDoctor, lvTable;
    public TextView tvDoctor, tvTable;
    public DatabaseReference databaseReferenceDoctor, databaseReferenceTable;
    public ArrayList<Doctor> doctorArrayList;
    public ArrayList<SurgicalTable> surgicalTableArrayList;
    public ArrayAdapter<Doctor> aaDoctor;
    public ArrayAdapter<SurgicalTable> aaTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_assign, container, false);
        lvDoctor = rootView.findViewById(R.id.lvDoctor);
        lvTable = rootView.findViewById(R.id.lvTable);
        tvDoctor = rootView.findViewById(R.id.tvDoctor);
        tvTable = rootView.findViewById(R.id.tvTable);
        doctorArrayList = new ArrayList<>();
        surgicalTableArrayList = new ArrayList<>();
        databaseReferenceDoctor = FirebaseDatabase.getInstance().getReference("cghversion20").child("Doctor");
        databaseReferenceTable = FirebaseDatabase.getInstance().getReference("cghversion20").child("SurgicalTable");
        aaDoctor = new DoctorAdapter(getContext(), R.layout.list_adapter, doctorArrayList);
        aaTable = new TableAdapter(getContext(), R.layout.list_adapter, surgicalTableArrayList);
        lvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Update notification;
                SurgicalTable current = surgicalTableArrayList.get(position);
                String currentId = current.getSurgicalCode()+"/"+current.getTableCode();
                tvTable.setText(currentId);
            }
        });
        lvDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Update notification;
                Doctor current = doctorArrayList.get(position);
                String currentId = current.getSurgeronId()+"/"+current.getName();
                tvDoctor.setText(currentId);
            }
        });






        databaseReferenceDoctor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one
                doctorArrayList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Menu page", "Finding...");

                    Doctor current = child.getValue(Doctor.class);
                    doctorArrayList.add(current);

                }
                lvDoctor.setAdapter(aaDoctor);
                //pla.notifyDataSetChanged();
                //listView.setAdapter(notificationAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        });
        databaseReferenceTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one
                surgicalTableArrayList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Menu page", "Finding...");

                    SurgicalTable current = child.getValue(SurgicalTable.class);
                    surgicalTableArrayList.add(current);

                }
                lvTable.setAdapter(aaTable);

                //pla.notifyDataSetChanged();
                //listView.setAdapter(notificationAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        });
        return rootView;
    }
}
