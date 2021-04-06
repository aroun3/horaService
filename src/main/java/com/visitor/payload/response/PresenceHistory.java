package com.visitor.payload.response;

import java.io.Serializable;

public class PresenceHistory implements Serializable{
	
	private Integer below;
	private Integer normal;
	private Integer over;
	private Integer absent;
	
	public PresenceHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PresenceHistory(Integer below, Integer normal, Integer over, Integer absent) {
		super();
		this.below = below;
		this.normal = normal;
		this.over = over;
		this.absent = absent;
	}

	public Integer getBelow() {
		return below;
	}

	public void setBelow(Integer below) {
		this.below = below;
	}

	public Integer getNormal() {
		return normal;
	}

	public void setNormal(Integer normal) {
		this.normal = normal;
	}

	public Integer getOver() {
		return over;
	}

	public void setOver(Integer over) {
		this.over = over;
	}

	public Integer getAbsent() {
		return absent;
	}

	public void setAbsent(Integer absent) {
		this.absent = absent;
	}
}
