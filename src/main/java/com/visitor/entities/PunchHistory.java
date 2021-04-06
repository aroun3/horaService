package com.visitor.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "punch_history")
public class PunchHistory implements Serializable{

	
	@Id
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "punch_day")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
	private Date punchDay;
	
	@Column(name = "emp_code")
	private String empCode;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "arrival")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
	private Date arrival;
	
	@Column(name = "arrival_id")
	private Integer arrivalId;
	
	@Column(name = "departure")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
	private Date departure;
	
	@Column(name = "departure_id")
	private Integer departureId;
	
	@Column(name = "presence")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
	private Date presence;
	
	@Column(name = "arrival_state")
	private String arrivalState;
	
	@Column(name = "departure_state")
	private String departureState;
	
	@Column(name = "presence_state")
	private String presenceState;
	
	@Column(name = "is_absent")
	private String isAbsent;
	
	@Column(name = "arrival_terminal_id")
	private Integer arrivalTerminalId;

	public PunchHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PunchHistory(Date punchDay, String empCode, String firstName, String position, String department,
			Date arrival, Integer arrivalId, Date departure, Integer departureId, Date presence, String arrivalState,
			String departureState, String presenceState, String isAbsent, Integer arrivalTerminalId) {
		super();
		this.punchDay = punchDay;
		this.empCode = empCode;
		this.firstName = firstName;
		this.position = position;
		this.department = department;
		this.arrival = arrival;
		this.arrivalId = arrivalId;
		this.departure = departure;
		this.departureId = departureId;
		this.presence = presence;
		this.arrivalState = arrivalState;
		this.departureState = departureState;
		this.presenceState = presenceState;
		this.isAbsent = isAbsent;
		this.arrivalTerminalId = arrivalTerminalId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getArrivalId() {
		return arrivalId;
	}

	public void setArrivalId(Integer arrivalId) {
		this.arrivalId = arrivalId;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Integer getDepartureId() {
		return departureId;
	}

	public void setDepartureId(Integer departureId) {
		this.departureId = departureId;
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

	public String getIsAbsent() {
		return isAbsent;
	}

	public void setIsAbsent(String isAbsent) {
		this.isAbsent = isAbsent;
	}

	public Integer getArrivalTerminalId() {
		return arrivalTerminalId;
	}

	public void setArrivalTerminalId(Integer arrivalTerminalId) {
		this.arrivalTerminalId = arrivalTerminalId;
	}
}
