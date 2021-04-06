package com.visitor.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Immutable
@Table(name = "punch_h")
public class PunchHistory implements Serializable{

	@Id
    @Column(name = "id")
	private Integer id;
	
	private Date punchDay;
	
	private String empCode;
	
	private String firstName;
	
	private String lastName;
	
	private String position;
	
	private String department;
	
	@JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
	private Date arrival;
	
	@JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
	private Date departure;
	
	@JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
	private Date presence;
	
	private String arrivalState;
	
	private String departureState;
	
	private String presenceState;
	
	private Boolean isAbsent;
	

	public PunchHistory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PunchHistory(Date punchDay, String empCode, String firstName, String lastName, String position,
			String department, Date arrival, Date departure, Date presence, String arrivalState, String departureState,
			String presenceState, Boolean isAbsent) {
		super();
		this.punchDay = punchDay;
		this.empCode = empCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.department = department;
		this.arrival = arrival;
		this.departure = departure;
		this.presence = presence;
		this.arrivalState = arrivalState;
		this.departureState = departureState;
		this.presenceState = presenceState;
		this.isAbsent = isAbsent;
	}


	public Date getPunchDay() {
		return punchDay;
	}


	public void setPunchDay(Date punchDay) {
		this.punchDay = punchDay;
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


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public Date getArrival() {
		return arrival;
	}


	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}


	public Date getDeparture() {
		return departure;
	}


	public void setDeparture(Date departure) {
		this.departure = departure;
	}


	public Date getPresence() {
		return presence;
	}


	public void setPresence(Date presence) {
		this.presence = presence;
	}


	public String getArrivalState() {
		return arrivalState;
	}


	public void setArrivalState(String arrivalState) {
		this.arrivalState = arrivalState;
	}


	public String getDepartureState() {
		return departureState;
	}


	public void setDepartureState(String departureState) {
		this.departureState = departureState;
	}


	public String getPresenceState() {
		return presenceState;
	}


	public void setPresenceState(String presenceState) {
		this.presenceState = presenceState;
	}


	public Boolean getIsAbsent() {
		return isAbsent;
	}


	public void setIsAbsent(Boolean isAbsent) {
		this.isAbsent = isAbsent;
	}
}
