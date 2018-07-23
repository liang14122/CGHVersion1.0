package com.example.a16004118.cghversion10.ObjectPackage;

public class Doctor {
    private String idFB,surgeronId, rank, name, pager;

    public Doctor(String idFB, String surgeronId, String rank,
                  String name, String pager) {
        this.idFB = idFB;
        this.surgeronId = surgeronId;
        this.rank = rank;
        this.name = name;
        this.pager = pager;
    }
    public Doctor(){

    }

    public Doctor(String idFB, String surgeronId, String name) {
        this.idFB = idFB;
        this.surgeronId = surgeronId;
        this.name = name;
    }

    public String getIdFB() {
        return idFB;
    }

    public String getSurgeronId() {
        return surgeronId;
    }

    public void setSurgeronId(String surgeronId) {
        this.surgeronId = surgeronId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }
}
