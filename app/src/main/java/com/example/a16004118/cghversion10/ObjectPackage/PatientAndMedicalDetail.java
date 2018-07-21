package com.example.a16004118.cghversion10.ObjectPackage;

public class PatientAndMedicalDetail {
    //patient part
    String mrin, acountNo, name, age, gender;
    //medical part
    String lastMeal, lastClearFluid;
    Boolean lifeThreating;
    String typeOfAnaesthesiaSedation, preOpDiagnosis;

    String contactPrecautionsString, bloodBorneInfectionString, airbornePrecautionsString, otherHighRiskInfectionsString;
    String assignDoctorId,location, ot, chitSubmission;
    String dept, ward, room, bed, table;
    //chitSubmission is the data for calculation;


    public PatientAndMedicalDetail(String mrin, String acountNo, String name, String age, String gender, String lastMeal, String lastClearFluid, Boolean lifeThreating, String typeOfAnaesthesiaSedation, String preOpDiagnosis, String contactPrecautionsString, String bloodBorneInfectionString, String airbornePrecautionsString, String otherHighRiskInfectionsString, String assignDoctorId, String location, String ot, String chitSubmission, String dept, String ward, String room, String bed, String table) {
        this.mrin = mrin;
        this.acountNo = acountNo;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.lastMeal = lastMeal;
        this.lastClearFluid = lastClearFluid;
        this.lifeThreating = lifeThreating;
        this.typeOfAnaesthesiaSedation = typeOfAnaesthesiaSedation;
        this.preOpDiagnosis = preOpDiagnosis;
        this.contactPrecautionsString = contactPrecautionsString;
        this.bloodBorneInfectionString = bloodBorneInfectionString;
        this.airbornePrecautionsString = airbornePrecautionsString;
        this.otherHighRiskInfectionsString = otherHighRiskInfectionsString;
        this.assignDoctorId = assignDoctorId;
        this.location = location;
        this.ot = ot;
        this.chitSubmission = chitSubmission;
        this.dept = dept;
        this.ward = ward;
        this.room = room;
        this.bed = bed;
        this.table = table;
    }

    public String getMrin() {
        return mrin;
    }

    public void setMrin(String mrin) {
        this.mrin = mrin;
    }

    public String getAcountNo() {
        return acountNo;
    }

    public void setAcountNo(String acountNo) {
        this.acountNo = acountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLastMeal() {
        return lastMeal;
    }

    public void setLastMeal(String lastMeal) {
        this.lastMeal = lastMeal;
    }

    public String getLastClearFluid() {
        return lastClearFluid;
    }

    public void setLastClearFluid(String lastClearFluid) {
        this.lastClearFluid = lastClearFluid;
    }

    public String getTypeOfAnaesthesiaSedation() {
        return typeOfAnaesthesiaSedation;
    }

    public void setTypeOfAnaesthesiaSedation(String typeOfAnaesthesiaSedation) {
        this.typeOfAnaesthesiaSedation = typeOfAnaesthesiaSedation;
    }

    public String getPreOpDiagnosis() {
        return preOpDiagnosis;
    }

    public void setPreOpDiagnosis(String preOpDiagnosis) {
        this.preOpDiagnosis = preOpDiagnosis;
    }



    public String getContactPrecautionsString() {
        return contactPrecautionsString;
    }

    public void setContactPrecautionsString(String contactPrecautionsString) {
        this.contactPrecautionsString = contactPrecautionsString;
    }

    public String getBloodBorneInfectionString() {
        return bloodBorneInfectionString;
    }

    public void setBloodBorneInfectionString(String bloodBorneInfectionString) {
        this.bloodBorneInfectionString = bloodBorneInfectionString;
    }

    public String getAirbornePrecautionsString() {
        return airbornePrecautionsString;
    }

    public void setAirbornePrecautionsString(String airbornePrecautionsString) {
        this.airbornePrecautionsString = airbornePrecautionsString;
    }

    public String getOtherHighRiskInfectionsString() {
        return otherHighRiskInfectionsString;
    }

    public void setOtherHighRiskInfectionsString(String otherHighRiskInfectionsString) {
        this.otherHighRiskInfectionsString = otherHighRiskInfectionsString;
    }

    public String getAssignDoctorId() {
        return assignDoctorId;
    }

    public void setAssignDoctorId(String assignDoctorId) {
        this.assignDoctorId = assignDoctorId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getChitSubmission() {
        return chitSubmission;
    }

    public void setChitSubmission(String chitSubmission) {
        this.chitSubmission = chitSubmission;
    }

    public Boolean getLifeThreating() {
        return lifeThreating;
    }

    public void setLifeThreating(Boolean lifeThreating) {
        this.lifeThreating = lifeThreating;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
    public String showAlert(){
        String emptyField = "You haven't entered: \n";

        if(mrin.equals("")){
            emptyField += "MRIN ";
        }
        if(acountNo.equals("")){
            emptyField += "Account field ";
        }
        if(name.equals("")){
            emptyField += "Name ";
        }
        if(age.equals("")){
            emptyField += "Age ";
        }
        if(gender.equals("")){
            emptyField += "Gender ";
        }
        if(lastMeal.equals("")){
            emptyField += "Last meal ";
        }
        if(lastClearFluid.equals("")){
            emptyField += "Last clear fluid ";
        }
        if(preOpDiagnosis.equals("")){
            emptyField += "Pre-op diagnosis ";
        }
        if(assignDoctorId.equals("None")){
            emptyField += "Assign a doctor ";
        }
        if(location.equals("None")){
            emptyField += "Location ";
        }
        if(ot.equals("None")){
            emptyField += "OT ";
        }
        if(dept.equals("")||ward.equals("")){
            emptyField += "Dept/Ward ";
        }
        if(room.equals("")||bed.equals("")){
            emptyField += "Room/Bed ";
        }
        if(table.equals("None")){
            emptyField += "Assign a table ";
        }
        return emptyField;
    }
}
