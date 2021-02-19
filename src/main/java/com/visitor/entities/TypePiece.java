package com.visitor.entities;

import com.visitor.entities.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "type_pieces")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypePiece extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

}
