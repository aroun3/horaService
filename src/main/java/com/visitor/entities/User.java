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
@Table(	name = "h_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User extends UserDateAudit{

        /**
	 *
	 */
	private static final long serialVersionUID = 1L;

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
        @JoinTable(	name = "h_user_roles",
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

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getMatricule() {
			return matricule;
		}

		public void setMatricule(String matricule) {
			this.matricule = matricule;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFonction() {
			return fonction;
		}

		public void setFonction(String fonction) {
			this.fonction = fonction;
		}

		public Short getStatus() {
			return status;
		}

		public void setStatus(Short status) {
			this.status = status;
		}

		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
        
}
