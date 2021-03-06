package com.example.a16004118.cghversion10.Alogrithm;

import android.util.Log;

import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alogrithm {
    ArrayList<PatientAndMedicalDetail> patientAndMedicalDetailArrayList = new ArrayList<>();
    ArrayList<String> patientIdArr = new ArrayList<>();
    ArrayList<Integer> patientScore = new ArrayList<>();

    Map<String, Integer> map = new HashMap<String, Integer>();
    public Alogrithm(ArrayList<PatientAndMedicalDetail> patientAndMedicalDetailArrayList, ArrayList<String> patientIdArr) {
        this.patientAndMedicalDetailArrayList = patientAndMedicalDetailArrayList;
        this.patientIdArr = patientIdArr;
    }

    //return Map<String, Double>
    public ArrayList<String> getMap(){
        ArrayList<String> sortedIdFbArr = new ArrayList<>();
        for(int i= 0; i<patientIdArr.size(); i++){
            String idFB = patientIdArr.get(i);
            PatientAndMedicalDetail current = patientAndMedicalDetailArrayList.get(i);
            findtheScore(idFB, current);
        }


            for (int i = 0; i < patientIdArr.size(); i++) {
                int currentIndex = i;
                String biggestIdFb = patientIdArr.get(i);
                int biggestScore = patientScore.get(i);
                Log.i("Alogrithem score", biggestScore + "= current biggest" + "idfb: " + biggestIdFb);

                for (int a = i + 1; a < map.size(); a++) {
                    String currentIdFb = patientIdArr.get(a);
                    int currentScore = patientScore.get(a);
                    Log.i("Alogrithem score", currentScore + "= current biggest" + "idfb: " + currentIdFb);

                    if (currentScore > biggestScore) {
                        biggestIdFb = currentIdFb;
                        biggestScore = currentScore;
                        currentIndex = a;
                    }
                }
                patientIdArr.remove(currentIndex);
                patientScore.remove(currentIndex);
                patientIdArr.add(i,biggestIdFb);
                patientScore.add(i,biggestScore);
                Log.i("The " + i + " score", "" + biggestScore + " id=" + biggestIdFb);
                sortedIdFbArr.add(biggestIdFb);

            }

        Log.i("Algorithem sorted size",""+sortedIdFbArr.toString());
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
            map.put(idFB, score);
            patientScore.add(score);
            Log.i("map value",String.valueOf(map));

    }
}
