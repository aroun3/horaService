package com.visitor.payload.response;

import java.io.Serializable;

public class GraphStat implements Serializable{
	
	private String title;
	private GeneralHistoryStats arrivees;
	private GeneralHistoryStats departs;
	private Integer absences;
	private PresenceHistory presenceHistory;
	
	public GraphStat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GraphStat(String title, GeneralHistoryStats arrivees, GeneralHistoryStats departs, Integer absences,
			PresenceHistory presenceHistory) {
		super();
		this.title = title;
		this.arrivees = arrivees;
		this.departs = departs;
		this.absences = absences;
		this.presenceHistory = presenceHistory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getAbsences() {
		return absences;
	}

	public void setAbsences(Integer absences) {
		this.absences = absences;
	}

	public PresenceHistory getPresenceHistory() {
		return presenceHistory;
	}

	public void setPresenceHistory(PresenceHistory presenceHistory) {
		this.presenceHistory = presenceHistory;
	}
}
