package com.visitor.entities.visitor;

import com.visitor.entities.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "h_cartes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Carte  extends UserDateAudit {

    /**
     *
     */
    private static final long serialVersionUID = 7276267557998410728L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String codeCarte;

    @NotBlank
    private Short status;
}
