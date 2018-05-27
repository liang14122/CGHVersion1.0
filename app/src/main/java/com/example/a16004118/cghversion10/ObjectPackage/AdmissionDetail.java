package com.example.a16004118.cghversion10.ObjectPackage;

public class AdmissionDetail {
    private String dateOfAdmission, timeOfAdmission, lastMeal, lastFluid;

    public AdmissionDetail(String dateOfAdmission, String timeOfAdmission, String lastMeal, String lastFluid) {
        this.dateOfAdmission = dateOfAdmission;
        this.timeOfAdmission = timeOfAdmission;
        this.lastMeal = lastMeal;
        this.lastFluid = lastFluid;
    }

    public AdmissionDetail() {
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getTimeOfAdmission() {
        return timeOfAdmission;
    }

    public void setTimeOfAdmission(String timeOfAdmission) {
        this.timeOfAdmission = timeOfAdmission;
    }

    public String getLastMeal() {
        return lastMeal;
    }

    public void setLastMeal(String lastMeal) {
        this.lastMeal = lastMeal;
    }

    public String getLastFluid() {
        return lastFluid;
    }

    public void setLastFluid(String lastFluid) {
        this.lastFluid = lastFluid;
    }
}
