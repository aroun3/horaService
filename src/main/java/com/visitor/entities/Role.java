package com.visitor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by Mory on 25/01/2021.
 */
@Entity
@Table(name = "h_roles")
@AllArgsConstructor
@NoArgsConstructor
@ToString
<<<<<<< HEAD
@Data
public class Role {
=======
public class Role implements Serializable{
>>>>>>> 81b41c2bca8e71c1c17b401455be6358aa835895
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public ERole getName(){
        return this.name;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    

}