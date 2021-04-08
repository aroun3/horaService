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
@Data
@ToString
public class Nfc {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max =100)
    private String nfcId;

    @NotBlank
    @Size(max = 100)
    private String nfcRef;

    @NotBlank
    private Boolean status;
}
