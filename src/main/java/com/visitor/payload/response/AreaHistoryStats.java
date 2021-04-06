package com.visitor.payload.response;

public class AreaHistoryStats{
	
	private String area;
	private Double longitude;
	private Double latitude;
	private GeneralHistoryStats arrivees;
	private GeneralHistoryStats departs;
	private PresenceHistory presences;
	private Integer absences;
	
	public AreaHistoryStats() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AreaHistoryStats(String area, Double longitude, Double latitude, GeneralHistoryStats arrivees,
			GeneralHistoryStats departs, PresenceHistory presences, Integer absences) {
		super();
		this.area = area;
		this.longitude = longitude;
		this.latitude = latitude;
		this.arrivees = arrivees;
		this.departs = departs;
		this.presences = presences;
		this.absences = absences;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
