package com.visitor.entities;

import java.io.Serializable;

public class Area implements Serializable{
	
	private Integer id;
	private String area;
	/*private Double longitude;
	private Double latitude;*/
	
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Area(Integer id, String area) {
		super();
		this.id = id;
		this.area = area;
	}


	/*public Area(Integer id, String area, Double longitude, Double latitude) {
		super();
		this.id = id;
		this.area = area;
		this.longitude = longitude;
		this.latitude = latitude;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	/*public Double getLongitude() {
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
	}*/
	
	
}
