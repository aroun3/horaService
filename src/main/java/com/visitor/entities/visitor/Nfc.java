package com.visitor.entities.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "h_nfc")
@AllArgsConstructor
@NoArgsConstructor
//@Data
@ToString
public class Nfc {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@NotBlank
    @Size(max =100)
    @Column(unique = true)
    private String nfcId;

    //@NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String nfcRef;

    //@NotBlank
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNfcId() {
        return nfcId;
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public String getNfcRef() {
        return nfcRef;
    }

    public void setNfcRef(String nfcRef) {
        this.nfcRef = nfcRef;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
