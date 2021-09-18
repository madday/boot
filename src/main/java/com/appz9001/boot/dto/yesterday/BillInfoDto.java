package com.appz9001.boot.dto.yesterday;

import java.math.BigDecimal;

public class BillInfoDto {
    private String sort;
    private String saleSortId;
    private String saleSort;
    private BigDecimal start;
    private String startShow;
    private BigDecimal end;
    private String endShow;
    private BigDecimal occu;
    private String occuShow;
    private BigDecimal settle;
    private String settleShow;
    private BigDecimal adjust;
    private String dep;
    // 1汇总，2明细,3空白
    private String type;

    public String getStartShow() {
        return startShow;
    }

    public void setStartShow(String startShow) {
        this.startShow = startShow;
    }

    public String getEndShow() {
        return endShow;
    }

    public void setEndShow(String endShow) {
        this.endShow = endShow;
    }

    public String getOccuShow() {
        return occuShow;
    }

    public void setOccuShow(String occuShow) {
        this.occuShow = occuShow;
    }

    public String getSettleShow() {
        return settleShow;
    }

    public void setSettleShow(String settleShow) {
        this.settleShow = settleShow;
    }

    public String getSaleSortId() {
        return saleSortId;
    }

    public void setSaleSortId(String saleSortId) {
        this.saleSortId = saleSortId;
    }

    public BigDecimal getStart() {
        return start;
    }

    public void setStart(BigDecimal start) {
        this.start = start;
    }

    public BigDecimal getEnd() {
        return end;
    }

    public void setEnd(BigDecimal end) {
        this.end = end;
    }

    public BigDecimal getOccu() {
        return occu;
    }

    public void setOccu(BigDecimal occu) {
        this.occu = occu;
    }

    public BigDecimal getSettle() {
        return settle;
    }

    public void setSettle(BigDecimal settle) {
        this.settle = settle;
    }

    public String getSaleSort() {
        return saleSort;
    }

    public void setSaleSort(String saleSort) {
        this.saleSort = saleSort;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAdjust() {
        return adjust;
    }

    public void setAdjust(BigDecimal adjust) {
        this.adjust = adjust;
    }
}
