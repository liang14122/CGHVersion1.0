package com.example.a16004118.cghversion10.ObjectPackage;


public class Chit {
    String idFB;
    Patient patient;
    Issues issues;
    AdmissionDetail admissionDetail;
    Investigations investigations;
    Consent consent;
    SurgeryDetails surgeryDetails;
    String surgicalTabel;
    String doctor ;
    private Boolean lifeThreatening;

    public Chit(String idFB,Patient patient, Issues issues, AdmissionDetail admissionDetail, Investigations investigations, Consent consent, String doctor,SurgeryDetails surgeryDetails, String surgicalTable,Boolean lifeThreatening) {
        this.idFB = idFB;
        this.patient = patient;
        this.issues = issues;
        this.admissionDetail = admissionDetail;
        this.investigations = investigations;
        this.consent = consent;
        this.surgeryDetails = surgeryDetails;
        this.surgicalTabel = surgicalTable;
        this.doctor = doctor;
        this.lifeThreatening = lifeThreatening;
    }

    public Chit() {
    }

    public String getIdFB() {
        return idFB;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Issues getIssues() {
        return issues;
    }

    public void setIssues(Issues issues) {
        this.issues = issues;
    }

    public AdmissionDetail getAdmissionDetail() {
        return admissionDetail;
    }

    public void setAdmissionDetail(AdmissionDetail admissionDetail) {
        this.admissionDetail = admissionDetail;
    }

    public Investigations getInvestigations() {
        return investigations;
    }

    public void setInvestigations(Investigations investigations) {
        this.investigations = investigations;
    }

    public Consent getConsent() {
        return consent;
    }

    public void setConsent(Consent consent) {
        this.consent = consent;
    }

    public SurgeryDetails getSurgeryDetails() {
        return surgeryDetails;
    }

    public void setSurgeryDetails(SurgeryDetails surgeryDetails) {
        this.surgeryDetails = surgeryDetails;
    }

    public Boolean getLifeThreatening() {
        return lifeThreatening;
    }

    public void setLifeThreatening(Boolean lifeThreatening) {
        this.lifeThreatening = lifeThreatening;
    }

    public String getSurgicalTabel() {
        return surgicalTabel;
    }

    public void setSurgicalTabel(String surgicalTabel) {
        this.surgicalTabel = surgicalTabel;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
