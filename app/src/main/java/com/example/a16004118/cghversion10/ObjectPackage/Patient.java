package com.example.a16004118.cghversion10.ObjectPackage;

public class Patient {
    private String name, nric, dateOfBirth, age, gender, race, languageSpoken, occupation, location, nextOfKinContact, drugAllergy;

    public Patient() {
    }

    public Patient(String name, String nric, String dateOfBirth, String age, String gender, String race, String languageSpoken, String occupation, String location, String nextOfKinContact, String drugAllergy) {
        this.name = name;
        this.nric = nric;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
        this.race = race;
        this.languageSpoken = languageSpoken;
        this.occupation = occupation;
        this.location = location;
        this.nextOfKinContact = nextOfKinContact;
        this.drugAllergy = drugAllergy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(String languageSpoken) {
        this.languageSpoken = languageSpoken;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNextOfKinContact() {
        return nextOfKinContact;
    }

    public void setNextOfKinContact(String nextOfKinContact) {
        this.nextOfKinContact = nextOfKinContact;
    }

    public String getDrugAllergy() {
        return drugAllergy;
    }

    public void setDrugAllergy(String drugAllergy) {
        this.drugAllergy = drugAllergy;
    }
}
