package com.visitor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "couplings")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coupling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="personnal_id")
    private Personnal personnal;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="carte_id")
    private Carte carte;

    @ManyToOne
    @JoinColumn(name="visitor_id")
    private Visitor visitor;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    private Date insideAt = new Date();

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    private Date outsideAt;

    @NotBlank
    private Boolean isCoupling;


}
