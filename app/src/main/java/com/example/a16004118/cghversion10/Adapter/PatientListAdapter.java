package com.example.a16004118.cghversion10.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;

import java.util.ArrayList;

public class PatientListAdapter extends ArrayAdapter {

    private Context parent_context;
    private int layout_id;
    private ArrayList<PatientAndMedicalDetail> chitList;

    public PatientListAdapter(Context context,
                      int resource,
                      ArrayList<PatientAndMedicalDetail> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        chitList = objects;
    }

    @SuppressLint({"WrongViewCast", "ResourceAsColor", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) parent_context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            rowView = inflater.inflate(layout_id, parent, false);
        } else {
            rowView = convertView;
        }

        PatientAndMedicalDetail currentChit = chitList.get(position);



        TextView tvPatientNameCard = rowView.findViewById(R.id.tvPatientNameCard);
        TextView tvGenderAgeCard = rowView.findViewById(R.id.tvGenderAgeCard);
        TextView tvWaitingTimeCard = rowView.findViewById(R.id.tvWaitingTimeCard);
        TextView tvLastMealCard = rowView.findViewById(R.id.tvLastMealCard);
        CardView cvPatientList = rowView.findViewById(R.id.cvPatientList);

        ImageView ivGender = rowView.findViewById(R.id.ivGender);

        tvPatientNameCard.setText(currentChit.getName());
        tvWaitingTimeCard.setText(currentChit.getChitSubmission()+" Mins");
        tvLastMealCard.setText(currentChit.getLastMeal());

        String currentPatientGender = currentChit.getGender();
        if (currentPatientGender.equalsIgnoreCase("male")){
            ivGender.setImageResource(R.drawable.male);
            tvGenderAgeCard.setText("Male " + currentChit.getAge());
        }else if (currentChit.getGender().equalsIgnoreCase("female")){
            ivGender.setImageResource(R.drawable.female);
            tvGenderAgeCard.setText("Female " + currentChit.getAge());
        }
        Log.d("TAG", "getView: " + currentChit.getLifeThreating());
//        Toast.makeText(parent_context, currentChit.getLifeThreatening()+"", Toast.LENGTH_LONG).show();
        if (currentChit.getLifeThreating()){
            cvPatientList.setBackgroundResource(R.color.EmergencyPatientList);
        }else{
//            if (currentChit.getReady()){
//                cvPatientList.setCardBackgroundColor(R.color.ReadyPatientList);
//            }else{
//                cvPatientList.setCardBackgroundColor(R.color.NotReadyPatientList);
//            }
            cvPatientList.setBackgroundResource(R.color.NotReadyPatientList);

        }
        return rowView;

    }
    //test branch

}
