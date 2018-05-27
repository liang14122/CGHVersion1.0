package com.example.a16004118.cghversion10.ObjectPackage;

public class Notification {
    String notificationIdFB, doctorId, patientName, patientFin,sentence, time;
    boolean read, alert;

    public Notification(String notificationIdFB, String doctorId, String patientName, String patientFin, String sentence, String time, boolean read, boolean alert) {
        this.notificationIdFB = notificationIdFB;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.patientFin = patientFin;
        this.sentence = sentence;
        this.time = time;
        this.read = read;
        this.alert = alert;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientFin() {
        return patientFin;
    }

    public void setPatientFin(String patientFin) {
        this.patientFin = patientFin;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Notification() {
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getNotificationIdFB() {
        return notificationIdFB;
    }

    public void setNotificationIdFB(String notificationIdFB) {
        this.notificationIdFB = notificationIdFB;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
