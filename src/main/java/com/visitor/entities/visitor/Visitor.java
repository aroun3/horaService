package com.visitor.entities.visitor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.visitor.entities.User;
import com.visitor.entities.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//import org.apache.tomcat.util.codec.binary.Base64;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
//import java.io.UnsupportedEncodingException;

@Entity
@Table(name = "h_visitors")
@AllArgsConstructor
@NoArgsConstructor
//@Data
@ToString
public class Visitor extends UserDateAudit {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max =100)
    @Column(name = "full_name")
    private String fullName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "by_appointment")
    private String byAppointment;

    @NotBlank
    @Size(max = 100)
    @Column(name = "company")
    private String company;

    @NotBlank
    @Size(max = 100)
    @Column(name = "personal_employee")
    private String personalEmployee;

    @NotBlank
    @Size(max = 100)
    @Column(name = "position")
    private String position;

    @NotBlank
    @Size(max = 100)
    @Column(name = "reason")
    private String reason;

    @NotBlank
    @Size(max = 100)
    @Column(name = "cni_type")
    private String cniType;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nfc_ref")
    private String nfcRef;

    @NotBlank
    @Size(max = 100)
    @Column(name = "card_code")
    private String cardCode;

    @Column(name="in_date", updatable = false)
    @JsonIgnore
    //@NotBlank
    private Date inDate = new Date();

    @Column(name="out_date")
    @JsonIgnore
    //@NotBlank
    private Date outDate = new Date();

    //@JsonIgnore
    private Boolean status;

    @NotBlank
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getByAppointment() {
        return byAppointment;
    }

    public void setByAppointment(String byAppointment) {
        this.byAppointment = byAppointment;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPersonalEmployee() {
        return personalEmployee;
    }

    public void setPersonalEmployee(String personalEmployee) {
        this.personalEmployee = personalEmployee;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCniType() {
        return cniType;
    }

    public void setCniType(String cniType) {
        this.cniType = cniType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNfcRef() {
        return nfcRef;
    }

    public void setNfcRef(String nfcRef) {
        this.nfcRef = nfcRef;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    //@JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    //@JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
