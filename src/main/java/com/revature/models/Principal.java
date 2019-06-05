package com.revature.models;

public class Principal {
	
	//JWT will take a string for the id and Role
	private int id;
	private String role;
	private String username;
	
	public Principal() {
		super();
	}
	
	public Principal(int id, String role, String username) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Principal [id=" + id + ", role=" + role + ", username=" + username + "]";
	}

}
	
