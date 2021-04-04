package com.visitor.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "h_transactions")
@Immutable
@NoArgsConstructor
@Data
public class Transactions implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String codeEmp;

    @Temporal(TemporalType.TIME)
    private java.util.Date firstPunch;

    @Temporal(TemporalType.TIME)
    private java.util.Date lastPunch;

    @Temporal(TemporalType.DATE)
    private java.util.Date dateTrans;

    @Column(name = "status")
    private String status;


    Transactions(Integer id){
        this.id = id;
    }

    public Transactions(Integer id, @NotBlank String codeEmp, Date firstPunch, Date lastPunch, Date dateTrans,
            String status) {
        this.id = id;
        this.codeEmp = codeEmp;
        this.firstPunch = firstPunch;
        this.lastPunch = lastPunch;
        this.dateTrans = dateTrans;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeEmp() {
        return codeEmp;
    }

    public void setCodeEmp(String codeEmp) {
        this.codeEmp = codeEmp;
    }

    public java.util.Date getFirstPunch() {
        return firstPunch;
    }

    public void setFirstPunch(java.util.Date firstPunch) {
        this.firstPunch = firstPunch;
    }

    public java.util.Date getLastPunch() {
        return lastPunch;
    }

    public void setLastPunch(java.util.Date lastPunch) {
        this.lastPunch = lastPunch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
