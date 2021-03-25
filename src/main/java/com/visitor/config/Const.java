package com.visitor.config;


public enum Const{
    CHECKIN("255"),
    CHECKOUT("000"),
    INCOMPLET("0"),
    EARLYCHECKIN("1"),
    ONTIMECHECKIN("2"),
    LATECHECHIN("3"),
    EARLYCHECKOUT("4"),
    ONTIMECHECKOUT("5"),
    LATECHECKOUT("6");

    private String val;

    private Const(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }

    public String toString() {
        return this.val;
    }

}

