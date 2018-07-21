package com.example.a16004118.cghversion10.ObjectPackage;

public class SurgicalTable {
    private String surgicalCode, tableCode, detail;

    public SurgicalTable(String surgicalCode, String tableCode, String detail) {
        this.surgicalCode = surgicalCode;
        this.tableCode = tableCode;
        this.detail = detail;
    }
    public SurgicalTable(){

    }

    public String getSurgicalCode() {
        return surgicalCode;
    }

    public void setSurgicalCode(String surgicalCode) {
        this.surgicalCode = surgicalCode;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
