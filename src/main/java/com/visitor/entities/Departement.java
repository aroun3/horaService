package com.visitor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "h_personnel_department_view")
public class Departement {

    @Id
    @Column(name = "dept_code")
    private String depCode;

    @Column(name = "dept_name")
    private String deptName;

    public String getDepCode() {
        return depCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }
}
