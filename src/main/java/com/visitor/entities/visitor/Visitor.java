package com.visitor.entities.visitor;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Data
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
    @Column(name = "cni_Number")
    private String idCardNumber;

    @NotBlank
    @Size(max = 100)
    @Column(name = "card_code")
    private String cardCode;

    @Column(name="in_date")
    @JsonIgnore
    @NotBlank
    private Date inDate = new Date();

    @Column(name="out_date")
    @JsonIgnore
    @NotBlank
    private Date outDate;

    @NotBlank
    private Short status;


}
