package com.visitor.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.Immutable;

/**
 * Copie des données personnalisé de la base biotime
 */
@Entity
@Immutable
@Table(name = "h_phantom_view")
public class Phantom implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    
    @Id
    @Column(name = "emp_code")
    private String empCode;

    @Column(name = "punch_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date punchDate;

    @Column(name = "first_punch")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
    private Date firstPunch;

    @Column(name = "last_punch")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
    private Date lastPunch;

    @Column(name = "first_name")
    private String firstName; 

    @Column(name = "last_name")
    private String lastName; 

    @Column(name = "ephoto")
    private String ephoto; 

    @Column(name = "department")
    private String department; 

    @Column(name = "eposition")
    private String eposition;

    @Column(name = "area")
    private String area;

    @Column(name = "incomplet")
    private boolean incomplet;

    @Column(name = "early_checkin")
    private boolean earlyCheckin;

    @Column(name = "ontime_checkin")
    private boolean ontimeCheckin;

    @Column(name = "late_checkin")
    private boolean lateCheckin;

    @Column(name = "early_checkout")
    private boolean earlyCheckout;

    @Column(name = "ontime_checkout")
    private boolean ontimeCheckout;

    @Column(name = "late_checkout")
    private boolean lateCheckout;

    @Column(name = "checkin_status")
    private boolean checkinStatus;
    
    @Column(name = "checkout_status")
    private boolean checkoutStatus;
    
    public String getEmpCode() {
        return empCode;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public boolean isLateCheckout() {
        return lateCheckout;
    }
    public void setLateCheckout(boolean lateCheckout) {
        this.lateCheckout = lateCheckout;
    }
    public boolean isOntimeCheckout() {
        return ontimeCheckout;
    }
    public void setOntimeCheckout(boolean ontimeCheckout) {
        this.ontimeCheckout = ontimeCheckout;
    }
    public boolean isEarlyCheckout() {
        return earlyCheckout;
    }
    public void setEarlyCheckout(boolean earlyCheckout) {
        this.earlyCheckout = earlyCheckout;
    }
    public boolean isLateCheckin() {
        return lateCheckin;
    }
    public void setLateCheckin(boolean lateCheckin) {
        this.lateCheckin = lateCheckin;
    }
    public boolean isOntimeCheckin() {
        return ontimeCheckin;
    }
    public void setOntimeCheckin(boolean ontimeCheckin) {
        this.ontimeCheckin = ontimeCheckin;
    }
    public boolean isEarlyCheckin() {
        return earlyCheckin;
    }
    public void setEarlyCheckin(boolean earlyCheckin) {
        this.earlyCheckin = earlyCheckin;
    }
    public boolean isIncomplet() {
        return incomplet;
    }
    public void setIncomplet(boolean incomplet) {
        this.incomplet = incomplet;
    }
    public Date getPunchDate() {
        return punchDate;
    }
    public void setPunchDate(Date punchDate) {
        this.punchDate = punchDate;
    }
    public boolean getCheckoutStatus() {
        return checkoutStatus;
    }
    public void setCheckoutStatus(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }
    public boolean getCheckinStatus() {
        return checkinStatus;
    }
    public void setCheckinStatus(boolean checkinStatus) {
        this.checkinStatus = checkinStatus;
    }
    public String getEposition() {
        return eposition;
    }
    public void setEposition(String eposition) {
        this.eposition = eposition;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getEphoto() {
        return ephoto;
    }
    public void setEphoto(String ephoto) {
        this.ephoto = ephoto;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Date getLastPunch() {
        return lastPunch;
    }
    public void setLastPunch(Date lastPunch) {
        this.lastPunch = lastPunch;
    }
    public Date getFirstPunch() {
        return firstPunch;
    }
    public void setFirstPunch(Date firstPunch) {
        this.firstPunch = firstPunch;
    }
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
}
