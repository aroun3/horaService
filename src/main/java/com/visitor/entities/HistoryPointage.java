package com.visitor.entities;

import java.util.Date;

public interface HistoryPointage {
    Integer getId();
    String getEmpCode();
    String getFirstName();
    String getGender();
    String getEmail();
    String getMobile();
    String getLastName();
    String getAreaName();
    Date getarrivalTime();
    String getArrivalState();
    Date getDepartureTime();
    String getDepartureState();
    String getPresencePeriode();
}
