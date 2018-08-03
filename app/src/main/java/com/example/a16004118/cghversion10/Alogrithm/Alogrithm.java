package com.example.a16004118.cghversion10.Alogrithm;

import android.util.Log;

import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Alogrithm {
    ArrayList<PatientAndMedicalDetail> patientAndMedicalDetailArrayList = new ArrayList<>();
    ArrayList<String> patientIdArr = new ArrayList<>();
    Map<String, Integer> map = new HashMap<String, Integer>();
    public Alogrithm(ArrayList<PatientAndMedicalDetail> patientAndMedicalDetailArrayList, ArrayList<String> patientIdArr) {
        this.patientAndMedicalDetailArrayList = patientAndMedicalDetailArrayList;
        this.patientIdArr = patientIdArr;
    }
    ArrayList<String> currentIDFBList = new ArrayList<>();
    ArrayList<Integer> currentScoreList = new ArrayList<>();
    //return Map<String, Double>
    public ArrayList<String> getMap(){
        ArrayList<String> sortedIdFbArr = new ArrayList<>();
        for(int i= 0; i<patientIdArr.size(); i++){
            String idFB = patientIdArr.get(i);
            PatientAndMedicalDetail current = patientAndMedicalDetailArrayList.get(i);
            findtheScore(idFB, current);
        }

        Log.i("Alogrithem map size",map.size()+"");
        for(int i = 0; i<map.size(); i++){
            int biggestPosition = i;
            String biggestIdFb = patientIdArr.get(i);
            int biggestInt = map.get(biggestIdFb);
            for (int a=i; a<map.size(); a++){
                String currentIdFb = patientIdArr.get(a);
                int currentInt = currentScoreList.get(a);
                if(currentInt>biggestInt){
                    biggestPosition = a;
                }
            }
            sortedIdFbArr.add(biggestIdFb);
            int tempScore = currentScoreList.get(i);
            currentScoreList.set(i,currentScoreList.get(biggestPosition)) ;
            currentScoreList.set(biggestPosition,tempScore);

            String tempString = currentIDFBList.get(i);
            currentIDFBList.set(i,currentIDFBList.get(biggestPosition)) ;
            currentIDFBList.set(biggestPosition,tempString);
        }
        Log.i("Algorithem sorted size",""+sortedIdFbArr.size());
        return sortedIdFbArr;
    }

    //return int score
    public void findtheScore(String idFB, PatientAndMedicalDetail patient){
//        int lastMeal= -1;
//        int chitSubmission = -1;
        boolean life = patient.getLifeThreating();
//        lastMeal= Integer.parseInt(patient.getLastMeal());
//        chitSubmission = Integer.parseInt(patient.getChitSubmission());

        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        int chitHours = Integer.parseInt(patient.getChitSubmission().substring(0, 2));
        int chitMins= Integer.parseInt(patient.getChitSubmission().substring(1,2));
        int waitHrs = hours - chitHours;
        int waitMins = minutes - chitMins;

        if (waitHrs < 0){
            waitHrs = waitHrs + 24;
        }
        if (waitMins < 0){
            waitHrs = waitHrs - 1;
            waitMins = waitMins + 60;
        }



        int eatHours = Integer.parseInt(patient.getLastMeal().substring(0, 2));
        int eatMins= Integer.parseInt(patient.getLastMeal().substring(1,2));
        int lastEatHrs = hours - eatHours;
        int lastEatMins = minutes - eatMins;
        if (lastEatMins < 0){
            lastEatHrs = lastEatHrs - 1;
            lastEatMins = lastEatMins + 60;
        }
        if (lastEatHrs < 0){
            lastEatHrs = lastEatHrs + 24;
        }

        int score = (lastEatHrs*60 + lastEatMins) * 2 + (waitHrs*60+waitMins);
        if (life == true) {
            score += 10000;
        }
        Log.i("Score for "+idFB, ""+score);
//            String a = String.format(".%2f", score);
//            Double b = Double.parseDouble(a) *100;
//            int d = Integer.valueOf(String.valueOf(b));
        currentIDFBList.add(idFB);
        currentScoreList.add(score);
        map.put(idFB, score);

    }
}
