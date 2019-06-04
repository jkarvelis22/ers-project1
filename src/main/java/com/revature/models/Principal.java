package com.revature.models;

public class Principal {
	
	//JWT will take a string for the id and Role
	private int id;
	private String role;
	
	public Principal() {
		super();
	}
	
	public Principal(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Principal [id=" + id + ", role=" + role + "]";
	}

}

