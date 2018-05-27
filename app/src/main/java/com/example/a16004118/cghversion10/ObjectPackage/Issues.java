package com.example.a16004118.cghversion10.ObjectPackage;

public class Issues {
    private String preOpDiagnosis, remarks, infectionControlConcerns;

    public Issues() {
    }

    public Issues(String preOpDiagnosis, String remarks, String infectionControlConcerns) {
        this.preOpDiagnosis = preOpDiagnosis;
        this.remarks = remarks;
        this.infectionControlConcerns = infectionControlConcerns;
    }

    public String getPreOpDiagnosis() {
        return preOpDiagnosis;
    }

    public void setPreOpDiagnosis(String preOpDiagnosis) {
        this.preOpDiagnosis = preOpDiagnosis;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInfectionControlConcerns() {
        return infectionControlConcerns;
    }

    public void setInfectionControlConcerns(String infectionControlConcerns) {
        this.infectionControlConcerns = infectionControlConcerns;
    }
}
