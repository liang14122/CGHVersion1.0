package com.example.a16004118.cghversion10.Adapter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.a16004118.cghversion10.ObjectPackage.Doctor;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;

public class RecyclerViewTouchHelperCallback extends ItemTouchHelper.Callback {

    private final PatientAdapter mAdapter;

    private ArrayList<PatientAndMedicalDetail> patientList;

    private int finalPosition;
    private int startPosition;

    private PatientAndMedicalDetail currentPatient;

    private DatabaseReference mDatabase;

    private String TAG = "RecyclerViewTouchHelperCallback";

    public RecyclerViewTouchHelperCallback(PatientAdapter mAdapter,
                                           ArrayList<PatientAndMedicalDetail> patientList) {
        this.mAdapter = mAdapter;
        this.patientList = patientList;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int swipFlag = 0;
        int dragflag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        return makeMovementFlags(dragflag, swipFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        //alertDialog

        mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        Collections.swap(patientList, viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if (finalPosition != startPosition) {
            //record user action here
            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(recyclerView.getContext());

            Gson gson = new GsonBuilder().setLenient().create();
            String doctorString = prefs.getString("doctorString", null);
            Doctor currentDoctor = gson.fromJson(doctorString, Doctor.class);

            currentPatient = patientList.get(finalPosition);
            currentPatient.setNameStamp(currentDoctor.getName());

            String idFB = patientList.get(finalPosition).getIdFB();

            mDatabase = FirebaseDatabase.getInstance().getReference("cghversion20");

            mDatabase.child("Chit").child(idFB).setValue(currentPatient)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.w(TAG, "onSuccess: " + currentPatient.toString());
                        }
                    })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "onFailure: " );
                }
            });

            mAdapter.notifyDataSetChanged();

            Log.e("TAG", "clearView: " + String.valueOf(patientList));

//            Toast.makeText(recyclerView.getContext(), "FinalPosition" + finalPosition, Toast.LENGTH_SHORT).show();

            finalPosition = 0;
            startPosition = 0;
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);

        finalPosition = toPos;
        startPosition = fromPos;

//        Toast.makeText(recyclerView.getContext(), "Moved " + toPos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
