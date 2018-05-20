package com.example.a16004118.cghversion10.ObjectPackage;


public class Chit {
    Patient patient;
    Issues issues;
    AdmissionDetail admissionDetail;
    Investigations investigations;
    Consent consent;
    SurgeryDetails surgeryDetails;
    private Boolean lifeThreatening;

    public Chit(Patient patient, Issues issues, AdmissionDetail admissionDetail, Investigations investigations, Consent consent, SurgeryDetails surgeryDetails, Boolean lifeThreatening) {
        this.patient = patient;
        this.issues = issues;
        this.admissionDetail = admissionDetail;
        this.investigations = investigations;
        this.consent = consent;
        this.surgeryDetails = surgeryDetails;
        this.lifeThreatening = lifeThreatening;
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
}
