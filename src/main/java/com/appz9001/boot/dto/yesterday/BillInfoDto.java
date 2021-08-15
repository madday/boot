package com.appz9001.boot.dto.yesterday;

public class BillInfoDto {

    private String saleSortId;
    private String saleSort;
    private String start;
    private String end;
    private String occu;
    private String settle;


    public String getSaleSortId() {
        return saleSortId;
    }

    public void setSaleSortId(String saleSortId) {
        this.saleSortId = saleSortId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getOccu() {
        return occu;
    }

    public void setOccu(String occu) {
        this.occu = occu;
    }

    public String getSettle() {
        return settle;
    }

    public void setSettle(String settle) {
        this.settle = settle;
    }

    public String getSaleSort() {
        return saleSort;
    }

    public void setSaleSort(String saleSort) {
        this.saleSort = saleSort;
    }
}
