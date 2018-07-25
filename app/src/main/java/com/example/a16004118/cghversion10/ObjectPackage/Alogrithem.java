package com.example.a16004118.cghversion10.ObjectPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Alogrithem {
    ArrayList<PatientAndMedicalDetail> patientAndMedicalDetailArrayList;
    ArrayList<String> patientIdArr;
    Map<String, Double> map = new HashMap<String, Double>();
    public Alogrithem() {
    }
    public void getMap(){
        for(int i= 0; i<patientIdArr.size(); i++){
            String idFB = patientIdArr.get(i);
            PatientAndMedicalDetail current = patientAndMedicalDetailArrayList.get(i);
            findtheScore(idFB, current);
        }
    }
    public void findtheScore(String idFB, PatientAndMedicalDetail patient){
        int lastMeal= -1;
        int chitSubmission = -1;
        boolean life = patient.getLifeThreating();
        lastMeal= Integer.parseInt(patient.getLastMeal());
        chitSubmission = Integer.parseInt(patient.getChitSubmission());
        if(lastMeal != -1 && chitSubmission != -1) {
            double score = (lastMeal / 0.5) * 2 + chitSubmission / 0.5;
            if (life == true) {
                score += 50;
            }
            map.put(idFB, score);
        }
    }
}
