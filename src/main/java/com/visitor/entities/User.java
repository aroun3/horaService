package com.visitor.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.visitor.entities.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mory on 25/01/2021.
 */
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User extends UserDateAudit{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        /*@NotBlank
        @Size(max = 20)*/
        private String matricule;

        @NotBlank
        @Size(max = 20)
        private String fullName;

        @NotBlank
        @Size(max = 20)
        private String username;

        @NotBlank
        @Size(max = 50)
        @Column(unique = true)
        private String phone;

        @NotBlank
        @Size(max = 50)
        @Email
        private String email;

        @NotBlank
        @Size(max = 120)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;

        /*@NotBlank
        @Size(max =  120)*/
        private String fonction;

        private Short status;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(	name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public User(String fullName,String username,String phone,String email, String password, Short status) {
                this.fullName = fullName;
                this.username = username;
                this.phone = phone;
                this.email = email;
                this.password = password;
                this.status = status;
        }
}
