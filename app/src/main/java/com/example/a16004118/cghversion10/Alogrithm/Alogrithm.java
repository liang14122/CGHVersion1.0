package com.example.a16004118.cghversion10.Alogrithm;

import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Alogrithm {
    ArrayList<PatientAndMedicalDetail> patientAndMedicalDetailArrayList;
    ArrayList<String> patientIdArr;
    Map<String, Integer> map = new HashMap<String, Integer>();
    public Alogrithm() {
    }

    //return Map<String, Double>
    public Map<String, Integer> getMap(){
        for(int i= 0; i<patientIdArr.size(); i++){
            String idFB = patientIdArr.get(i);
            PatientAndMedicalDetail current = patientAndMedicalDetailArrayList.get(i);
            findtheScore(idFB, current);
        }
        return map;
    }

    //return int score
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
            String a = String.format(".%2f", score);
            Double b = Double.parseDouble(a) *100;
            int c = Integer.valueOf(String.valueOf(b));
            map.put(idFB, c);
        }
    }
}
