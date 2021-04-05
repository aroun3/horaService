package com.visitor.payload.response;

import java.io.Serializable;

public class GeneralStat implements Serializable{
	
	private Integer early;
	private Integer ontime;
	private Integer late;
	private Integer absent;
	
	public GeneralStat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeneralStat(Integer early, Integer ontime, Integer late, Integer absent) {
		super();
		this.early = early;
		this.ontime = ontime;
		this.late = late;
		this.absent = absent;
	}

	public Integer getEarly() {
		return early;
	}

	public void setEarly(Integer early) {
		this.early = early;
	}

	public Integer getOntime() {
		return ontime;
	}

	public void setOntime(Integer ontime) {
		this.ontime = ontime;
	}

	public Integer getLate() {
		return late;
	}

	public void setLate(Integer late) {
		this.late = late;
	}

	public Integer getAbsent() {
		return absent;
	}

	public void setAbsent(Integer absent) {
		this.absent = absent;
	}

}
