package com.visitor.payload.response;

public class VisitorTotalResponse {
    private Integer total;
    private Integer total_srdv;
    private Integer total_rdv;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_srdv() {
        return total_srdv;
    }

    public void setTotal_srdv(Integer total_srdv) {
        this.total_srdv = total_srdv;
    }

    public Integer getTotal_rdv() {
        return total_rdv;
    }

    public void setTotal_rdv(Integer total_rdv) {
        this.total_rdv = total_rdv;
    }
}
