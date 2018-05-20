package com.example.a16004118.cghversion10.ObjectPackage;

public class Investigations {
    private String fullBloodCount, penalPanel, gxm, ptOrPtt, cxr, ecg;

    public Investigations(String fullBloodCount, String penalPanel, String gxm, String ptOrPtt, String cxr, String ecg) {
        this.fullBloodCount = fullBloodCount;
        this.penalPanel = penalPanel;
        this.gxm = gxm;
        this.ptOrPtt = ptOrPtt;
        this.cxr = cxr;
        this.ecg = ecg;
    }

    public String getFullBloodCount() {
        return fullBloodCount;
    }

    public void setFullBloodCount(String fullBloodCount) {
        this.fullBloodCount = fullBloodCount;
    }

    public String getPenalPanel() {
        return penalPanel;
    }

    public void setPenalPanel(String penalPanel) {
        this.penalPanel = penalPanel;
    }

    public String getGxm() {
        return gxm;
    }

    public void setGxm(String gxm) {
        this.gxm = gxm;
    }

    public String getPtOrPtt() {
        return ptOrPtt;
    }

    public void setPtOrPtt(String ptOrPtt) {
        this.ptOrPtt = ptOrPtt;
    }

    public String getCxr() {
        return cxr;
    }

    public void setCxr(String cxr) {
        this.cxr = cxr;
    }

    public String getEcg() {
        return ecg;
    }

    public void setEcg(String ecg) {
        this.ecg = ecg;
    }
}
