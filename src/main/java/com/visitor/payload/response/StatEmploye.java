package com.visitor.payload.response;

import java.io.Serializable;
import java.util.List;

import com.visitor.entities.Departement;
import com.visitor.entities.Employee;
import com.visitor.entities.Phantom;

public class StatEmploye implements Serializable{
	
	private Employee employee;
	private List<Phantom> phantoms;
	private Departement departement;
	
	public StatEmploye() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatEmploye(Employee employee, List<Phantom> phantoms, Departement departement) {
		super();
		this.employee = employee;
		this.phantoms = phantoms;
		this.departement = departement;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Phantom> getPhantoms() {
		return phantoms;
	}

	public void setPhantoms(List<Phantom> phantoms) {
		this.phantoms = phantoms;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

}
