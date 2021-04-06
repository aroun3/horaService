package com.visitor.payload.response;

import java.io.Serializable;

public class AreaPresenceHistory extends PresenceHistory{
	
	private String area;
	private Double longitude;
	private Double latitude;
	
	public AreaPresenceHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AreaPresenceHistory(Integer below, Integer normal, Integer over, Integer absent, String area,
			Double longitude, Double latitude) {
		super(below, normal, over, absent);
		this.area = area;
		this.longitude = longitude;
		this.latitude = latitude;
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
}
