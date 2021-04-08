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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "h_params")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hparams implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "min_checkin")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date minCheckin;

    @Column(name = "h_checkin")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date hCheckin;

    @Column(name = "late_checkin")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date lateCheckin;

    @Column(name = "max_checkin")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date maxCheckin;

    @Column(name = "min_checkout")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date minCheckout;

    @Column(name = "early_checkout")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date hearlyCheckout;
    
    @Column(name = "late_checkout")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date lateCheckout;

    @Column(name = "h_checkout")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date hCheckout;

    @Column(name = "max_checkout")
    @Temporal(TemporalType.TIME)
    @NotBlank
    private java.util.Date maxChectout;

    @Column(name = "h_group")
    @NotBlank
    private String hGroup;

    @Column(name = "callback_date")
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date callbackDate;
}