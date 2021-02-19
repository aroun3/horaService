package com.visitor.entities;

import com.visitor.entities.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "personnals")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Personnal extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String full_name;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

}
