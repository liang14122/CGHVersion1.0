package com.example.a16004118.cghversion10.ObjectPackage;

public class Doctor {
    private String name, fin, dateOfBirth,doctorLicense, contact, rank, description, address;
    private Boolean working, dayOff;

    public Doctor(String name, String fin, String dateOfBirth, String doctorLicense, String contact, String rank, String description, String address, Boolean working, Boolean dayOff) {
        this.name = name;
        this.fin = fin;
        this.dateOfBirth = dateOfBirth;
        this.doctorLicense = doctorLicense;
        this.contact = contact;
        this.rank = rank;
        this.description = description;
        this.address = address;
        this.working = working;
        this.dayOff = dayOff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDoctorLicense() {
        return doctorLicense;
    }

    public void setDoctorLicense(String doctorLicense) {
        this.doctorLicense = doctorLicense;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
