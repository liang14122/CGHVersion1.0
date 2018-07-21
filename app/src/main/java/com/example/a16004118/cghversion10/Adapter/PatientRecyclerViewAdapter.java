package com.example.a16004118.cghversion10.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a16004118.cghversion10.Activities.PatientMedicalDetailActivity;
import com.example.a16004118.cghversion10.Interface.PatientTouchHelperAdapter;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;

import java.util.Collections;
import java.util.List;



public class PatientRecyclerViewAdapter extends RecyclerView.Adapter<PatientRecyclerViewAdapter.PatientViewHolder> implements PatientTouchHelperAdapter{

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(chitList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(chitList, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);

        //record user action here
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView tvPatientNameCard, tvGenderAgeCard, tvWaitingTimeCard, tvLastMealCard;
        public CardView cvPatientList;
        public ImageView ivGender;
        private PatientTouchHelperAdapter itemClickLinstener;

        PatientViewHolder(View itemView) {
            super(itemView);

            tvPatientNameCard = itemView.findViewById(R.id.tvPatientNameCard);
            tvGenderAgeCard = itemView.findViewById(R.id.tvGenderAgeCard);
            tvWaitingTimeCard = itemView.findViewById(R.id.tvWaitingTimeCard);
            tvLastMealCard = itemView.findViewById(R.id.tvLastMealCard);
            cvPatientList = itemView.findViewById(R.id.cvPatientList);
            ivGender = itemView.findViewById(R.id.ivGender);
            itemView.setOnClickListener(this);
        }

        void setItemClickLinstener(PatientTouchHelperAdapter itemClickLinstener) {
            this.itemClickLinstener = itemClickLinstener;
        }

        @Override
        public void onClick(View v) {
            itemClickLinstener.onClick(v, getAdapterPosition());
        }
    }
    
    private List<PatientAndMedicalDetail> chitList;

    public PatientRecyclerViewAdapter(List<PatientAndMedicalDetail> chitList) {
        this.chitList = chitList;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_row, parent, false);

        return new PatientViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final PatientViewHolder holder, int position) {

        final PatientAndMedicalDetail currentChit = chitList.get(position);

        holder.tvPatientNameCard.setText(currentChit.getName());
        holder.tvWaitingTimeCard.setText(currentChit.getChitSubmission() + " Mins");
        holder.tvLastMealCard.setText(currentChit.getLastMeal());

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
        holder.setItemClickLinstener(new PatientTouchHelperAdapter() {
            @Override
            public void onClick(View view, int position) {
                    Intent intent = new Intent(view.getContext(), PatientMedicalDetailActivity.class);
                    intent.putExtra("patientDetail", chitList.get(position));
                    view.getContext().startActivity(intent);
                    Toast.makeText(view.getContext(), "Short", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemMove(int fromPosition, int toPosition) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return chitList.size();
    }

//
//    @Override
//    public void onItemDismiss(int position) {
//
//    }

}
