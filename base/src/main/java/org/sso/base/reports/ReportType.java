package org.sso.base.reports;

public enum ReportType {
    PDF(0),
    EXCEL(1),
    CSV(2);

    private int value;
    ReportType(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
