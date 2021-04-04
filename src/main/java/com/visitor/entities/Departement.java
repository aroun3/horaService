package com.visitor.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "h_personnel_department_view")
public class Departement implements Serializable{

    @Id
    @Column(name = "dept_code")
    private String depCode;

    @Column(name = "dept_name")
    private String deptName;

    
    public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	public Departement(String depCode, String deptName) {
		super();
		this.depCode = depCode;
		this.deptName = deptName;
	}


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
