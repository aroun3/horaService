package com.visitor.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.Immutable;
//import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Table(name = "`h_realtime_transaction`")
//@Subselect("select uuid() as id, rt.empcode as empcode, rt.punchtime as punchtime, rt.firstname as firstname, rt.lastname as lastname, rt.photo as photo from h_realtime_transaction as rt")
public class RealTimeTransaction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "emp_code")
    private String empCode;

    @Column(name = "punch_date")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    private Date punchDate;

    @Column(name = "punch_time")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
    private Date punchTime;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "ephoto")
    private String ePhoto;

    @Column(name = "department")
    private String department;

    @Column(name = "eposition")
    private String ePosition;

    @Column(name = "area")
    private String area;

    public Long getId() {
        return id;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEposition() {
        return ePosition;
    }

    public void setEposition(String eposition) {
        this.ePosition = eposition;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEPhoto() {
        return ePhoto;
    }

    public void setEPhoto(String ephoto) {
        this.ePhoto = ephoto;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public Date getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(Date punchtime) {
        this.punchDate = punchtime;
    }

    public String getEmpCode() {
        return this.empCode;
    }

    public void setEmpCode(String empcode) {
        this.empCode = empcode;
    }
    
}
