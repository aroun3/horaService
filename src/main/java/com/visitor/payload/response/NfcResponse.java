package com.visitor.payload.response;

public class NfcResponse {
    private String nfcId;
    private String nfcRef;
    private Boolean status;

    public String getNfcId() {
        return nfcId;
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNfcRef() {
        return nfcRef;
    }

    public void setNfcRef(String nfcRef) {
        this.nfcRef = nfcRef;
    }
}
