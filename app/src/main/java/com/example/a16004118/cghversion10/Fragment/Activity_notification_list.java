package com.example.a16004118.cghversion10.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a16004118.cghversion10.Adapter.NotificationAdapter;
import com.example.a16004118.cghversion10.ObjectPackage.Notification;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class Activity_notification_list extends Fragment {
    DatabaseReference databaseReferenceNotification;
    ArrayList<Notification> notificationArrayList;
    ListView listView;
    NotificationAdapter notificationAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_notification_list, container, false);

        Objects.requireNonNull(getActivity()).setTitle("Notifications");

        listView = view.findViewById(R.id.listView);
        notificationArrayList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(getContext(), R.layout.notification_list_adapter, notificationArrayList);
        Log.i("Notification_activity","set notification adapter");
        databaseReferenceNotification = FirebaseDatabase.getInstance().getReference("cghversion20").child("notification");
        Log.i("Notification_activity","set notification adapter and firebase");

        databaseReferenceNotification.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check for each one
                notificationArrayList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Menu page", "Finding...");

                    Notification current = child.getValue(Notification.class);

                    notificationArrayList.add(current);
                }
                listView.setAdapter(notificationAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());

            }
        });
        listView.setAdapter(notificationAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Update notification;
                Notification current = notificationArrayList.get(position);
                String idFB = current.getNotificationIdFB();
                current.setRead(true);
                databaseReferenceNotification.child(idFB).setValue(current);

            }
        });

        return view;
    }



}
