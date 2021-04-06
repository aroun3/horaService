package com.visitor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Mory on 25/01/2021.
 */
@Entity
@Table(name = "h_roles")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public ERole getName(){
        return this.name;
    }

}