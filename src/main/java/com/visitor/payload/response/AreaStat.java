package com.visitor.payload.response;

public class AreaStat extends GeneralStat{
	
	private String area;
	private Double longitude;
	private Double latitude;

	public AreaStat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AreaStat(Integer early, Integer ontime, Integer late, Integer absent, String area) {
		super(early, ontime, late, absent);
		this.area = area;
	}

	
	public AreaStat(Integer early, Integer ontime, Integer late, Integer absent, String area, Double longitude,
			Double latitude) {
		super(early, ontime, late, absent);
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
