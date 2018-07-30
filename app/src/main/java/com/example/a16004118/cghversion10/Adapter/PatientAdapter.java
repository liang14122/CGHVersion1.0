package com.example.a16004118.cghversion10.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a16004118.cghversion10.Activities.PatientMedicalDetailActivity;
import com.example.a16004118.cghversion10.Interface.ItemTouchHelper;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {


    private ItemTouchHelper dragListener;

    private List<PatientAndMedicalDetail> chitList;

    public void setDragListener(ItemTouchHelper dragListener) {
        this.dragListener = dragListener;
    }

    public PatientAdapter(List<PatientAndMedicalDetail> chitList) {
        this.chitList = chitList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPatientNameCard, tvGenderAgeCard, tvWaitingTimeCard, tvLastMealCard, tvNameStamp;
        public CardView cvPatientList;
        public ImageView ivGender, iv;

        MyViewHolder(View itemView) {
            super(itemView);

            tvPatientNameCard = itemView.findViewById(R.id.tvPatientNameCard);
            tvGenderAgeCard = itemView.findViewById(R.id.tvGenderAgeCard);
            tvWaitingTimeCard = itemView.findViewById(R.id.tvWaitingTimeCard);
            tvLastMealCard = itemView.findViewById(R.id.tvLastMealCard);
            tvNameStamp = itemView.findViewById(R.id.tvNameStamp);
            cvPatientList = itemView.findViewById(R.id.cvPatientList);
            ivGender = itemView.findViewById(R.id.ivGender);
            iv = itemView.findViewById(R.id.iv);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final PatientAndMedicalDetail currentChit = chitList.get(position);

        holder.tvPatientNameCard.setText(currentChit.getName());
        holder.tvWaitingTimeCard.setText(currentChit.getChitSubmission() + " Mins");
        holder.tvLastMealCard.setText(currentChit.getLastMeal());
        //Temp
        String nameStamp = currentChit.getNameStamp();
        if (nameStamp != null) {
            Log.e("TAG", "onBindViewHolder: " + currentChit.getName() + "   " + currentChit.getNameStamp());

            //get doctor name here
            holder.tvNameStamp.setText(currentChit.getNameStamp());
        }

        String currentPatientGender = currentChit.getGender();
        if (currentPatientGender.equalsIgnoreCase("male")) {
            holder.ivGender.setImageResource(R.drawable.male);
            holder.tvGenderAgeCard.setText("Male " + currentChit.getAge());
        } else if (currentChit.getGender().equalsIgnoreCase("female")) {
            holder.ivGender.setImageResource(R.drawable.female);
            holder.tvGenderAgeCard.setText("Female " + currentChit.getAge());
        }
        Log.d("TAG", "getView: " + currentChit.getLifeThreating());
//        Toast.makeText(parent_context, currentChit.getLifeThreatening()+"", Toast.LENGTH_LONG).show();
        if (currentChit.getLifeThreating()) {
            holder.cvPatientList.setBackgroundResource(R.color.EmergencyPatientList);
        } else {
//            if (currentChit.getReady()){
//                holder.cvPatientList.setCardBackgroundColor(R.color.ReadyPatientList);
//            }else{
//                holder.cvPatientList.setCardBackgroundColor(R.color.NotReadyPatientList);
//            }
            holder.cvPatientList.setBackgroundResource(R.color.NotReadyPatientList);
        }

        holder.iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dragListener.startDragItem(holder);
                }

                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, PatientMedicalDetailActivity.class);
                intent.putExtra("patientDetail", currentChit);
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return chitList == null ? 0 : chitList.size();
    }

}
