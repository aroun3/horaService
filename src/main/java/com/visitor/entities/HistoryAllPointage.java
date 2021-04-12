package com.visitor.entities;

import java.util.Date;

public interface HistoryAllPointage {
    Integer getId();
    String getEmpCode();
    String getFirstName();
    String getLastName();
    String getGender();
    String getEmail();
    String getMobile();
    String getAreaName();
    Date getPunchTime();
    String getTerminalName();
}
