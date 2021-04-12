package com.visitor.entities;

import java.util.Date;

public interface EmployeTop {
    Integer getId();
    String getEmpCode();
    String getFirstName();
    String getLastName();
    String getGender();
    String getEmail();
    String getMobile();
    String getPositionName();
    String getDepartmentName();
    String getAreaName();
    Date getPresencePeriode();
}
