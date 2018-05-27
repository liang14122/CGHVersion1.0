package com.example.a16004118.cghversion10.ObjectPackage;

public class SurgicalTable {
    private String tableCode, description;
    private Boolean working;

    public SurgicalTable(String tableCode, String description, Boolean working) {
        this.tableCode = tableCode;
        this.description = description;
        this.working = working;
    }

    public SurgicalTable() {
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }
}
