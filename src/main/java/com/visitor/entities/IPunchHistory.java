package com.visitor.entities;

import java.util.Date;

public interface IPunchHistory {

	String getEmpCode();
	Date getArrivalTime();
	Integer getArrivalId();
	Integer getArrivalTerminalId();
	Date getDepartureTime();
	Integer getDepartureId();
	Integer getDepartureTerminalId();
	Date getPresencePeriode();
	String getArrivalState();
	String getDepartureState();
	String getPresenceState();
	Boolean isAbsent();
	Date getLogDate();
	String getFirstName();
	String getLastName();
	String getPosition();
	String getDepartment();
}
