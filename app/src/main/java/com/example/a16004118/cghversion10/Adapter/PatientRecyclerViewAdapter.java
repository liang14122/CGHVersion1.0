package com.example.a16004118.cghversion10.Adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a16004118.cghversion10.Interface.PatientTouchHelperAdapter;
import com.example.a16004118.cghversion10.ObjectPackage.AdmissionDetail;
import com.example.a16004118.cghversion10.ObjectPackage.Chit;
import com.example.a16004118.cghversion10.ObjectPackage.Patient;
import com.example.a16004118.cghversion10.R;

import java.util.Collections;
import java.util.List;

public class PatientRecyclerViewAdapter  extends RecyclerView.Adapter<PatientRecyclerViewAdapter.PatientViewHolder>
        implements PatientTouchHelperAdapter{

    private List<Chit> chitList;

    public class PatientViewHolder extends RecyclerView.ViewHolder{

        public TextView tvPatientNameCard, tvGenderAgeCard, tvWaitingTimeCard, tvLastMealCard;
        public CardView cvPatientList;
        public ImageView ivGender;

        public PatientViewHolder(View itemView) {
            super(itemView);

            tvPatientNameCard = itemView.findViewById(R.id.tvPatientNameCard);
            tvGenderAgeCard = itemView.findViewById(R.id.tvGenderAgeCard);
            tvWaitingTimeCard = itemView.findViewById(R.id.tvWaitingTimeCard);
            tvLastMealCard = itemView.findViewById(R.id.tvLastMealCard);
            cvPatientList = itemView.findViewById(R.id.cvPatientList);
            ivGender = itemView.findViewById(R.id.ivGender);

        }
    }

    public PatientRecyclerViewAdapter(List<Chit> chitList) {
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
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {

        Chit currentChit = chitList.get(position);
        Patient currentPatient = currentChit.getPatient();
        AdmissionDetail currentAdmissionDetail = currentChit.getAdmissionDetail();

        holder.tvPatientNameCard.setText(currentPatient.getName());
        holder.tvWaitingTimeCard.setText(currentAdmissionDetail.getTimeOfAdmission()+" Mins");
        holder.tvLastMealCard.setText(currentAdmissionDetail.getLastMeal());

        String currentPatientGender = currentPatient.getGender();
        if (currentPatientGender.equalsIgnoreCase("male")){
            holder.ivGender.setImageResource(R.drawable.male);
            holder.tvGenderAgeCard.setText("Male " + currentPatient.getAge());
        }else if (currentPatient.getGender().equalsIgnoreCase("female")){
            holder.ivGender.setImageResource(R.drawable.female);
            holder.tvGenderAgeCard.setText("Female " + currentPatient.getAge());
        }
        Log.d("TAG", "getView: " + currentChit.getLifeThreatening());
//        Toast.makeText(parent_context, currentChit.getLifeThreatening()+"", Toast.LENGTH_LONG).show();
        if (currentChit.getLifeThreatening()){
            holder.cvPatientList.setBackgroundResource(R.color.EmergencyPatientList);
        }else{
//            if (currentChit.getReady()){
//                holder.cvPatientList.setCardBackgroundColor(R.color.ReadyPatientList);
//            }else{
//                holder.cvPatientList.setCardBackgroundColor(R.color.NotReadyPatientList);
//            }
            holder.cvPatientList.setBackgroundResource(R.color.NotReadyPatientList);

        }
    }

    @Override
    public int getItemCount() {
        return chitList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {

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

        return true;

    }
//
//    @Override
//    public void onItemDismiss(int position) {
//
//    }

}
