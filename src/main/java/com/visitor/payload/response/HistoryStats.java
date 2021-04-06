package com.visitor.payload.response;

import java.io.Serializable;

public class HistoryStats implements Serializable{

	private GeneralHistoryStats arrivees;
	private GeneralHistoryStats departs;
	private PresenceHistory presences;
	private Integer absences;
	
	public HistoryStats() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoryStats(GeneralHistoryStats arrivees, GeneralHistoryStats departs, PresenceHistory presences,
			Integer absences) {
		super();
		this.arrivees = arrivees;
		this.departs = departs;
		this.presences = presences;
		this.absences = absences;
	}

	public GeneralHistoryStats getArrivees() {
		return arrivees;
	}

	public void setArrivees(GeneralHistoryStats arrivees) {
		this.arrivees = arrivees;
	}

	public GeneralHistoryStats getDeparts() {
		return departs;
	}

	public void setDeparts(GeneralHistoryStats departs) {
		this.departs = departs;
	}

	public PresenceHistory getPresences() {
		return presences;
	}

	public void setPresences(PresenceHistory presences) {
		this.presences = presences;
	}

	public Integer getAbsences() {
		return absences;
	}

	public void setAbsences(Integer absences) {
		this.absences = absences;
	}
}
