package com.visitor.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "departurepunch")
public class DeparturePunch implements Serializable{
	
	@Id
    @Column(name = "id")
	private Integer id;
	
    @Column(name = "emp_code")
	private String empCode;
	
    @Column(name = "first_name")
	private String firstName;
    
    @Column(name = "last_name")
	private String lastName;
    
    @Column(name = "ephoto")
	private String ephoto;
    
    @Column(name = "edepartment")
	private String edepartment;
    
    @Column(name = "eposition")
	private String eposition;
    
    @Column(name = "area")
	private String area;
    
    @Column(name = "departure_time")
	private String departureTime;
    
    @Column(name = "punch_status")
	private String punchStatus;

	public DeparturePunch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeparturePunch(String empCode, String firstName, String lastName, String ephoto, String edepartment,
			String eposition, String area, String departureTime, String punchStatus) {
		super();
		this.empCode = empCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ephoto = ephoto;
		this.edepartment = edepartment;
		this.eposition = eposition;
		this.area = area;
		this.departureTime = departureTime;
		this.punchStatus = punchStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEphoto() {
		return ephoto;
	}

	public void setEphoto(String ephoto) {
		this.ephoto = ephoto;
	}

	public String getEdepartment() {
		return edepartment;
	}

	public void setEdepartment(String edepartment) {
		this.edepartment = edepartment;
	}

	public String getEposition() {
		return eposition;
	}

	public void setEposition(String eposition) {
		this.eposition = eposition;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getPunchStatus() {
		return punchStatus;
	}

	public void setPunchStatus(String punchStatus) {
		this.punchStatus = punchStatus;
	}

}
