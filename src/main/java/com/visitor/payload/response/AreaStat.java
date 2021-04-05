package com.visitor.payload.response;

public class AreaStat extends GeneralStat{
	
	private String area;

	public AreaStat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AreaStat(Integer early, Integer ontime, Integer late, Integer absent, String area) {
		super(early, ontime, late, absent);
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	
}
