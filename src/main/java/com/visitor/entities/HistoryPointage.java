package com.visitor.entities;

import java.util.Date;

public interface HistoryPointage {
    Integer getId();
    String getEmpCode();
    String getFirstName();
    String getLastName();
    String getGender();
    String getEmail();
    String getMobile();
    String getAreaName();
    Date getArrivalTime();
    String getArrivalState();
    Date getDepartureTime();
    String getDepartureState();
    String getPresencePeriode();
}
