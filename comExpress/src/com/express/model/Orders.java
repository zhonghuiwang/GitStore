package com.express.model;


public class Orders {
	private String id;
	private String inaddr;
	private String outaddr;
	private String state;
	private Users users;
	private Images image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInaddr() {
		return inaddr;
	}
	public void setInaddr(String inaddr) {
		this.inaddr = inaddr;
	}
	public String getOutaddr() {
		return outaddr;
	}
	public void setOutaddr(String outaddr) {
		this.outaddr = outaddr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Images getImage() {
		return image;
	}
	public void setImage(Images image) {
		this.image = image;
	}
	public Orders(String id, String inaddr, String outaddr, String state,
			Users users, Images image) {
		super();
		this.id = id;
		this.inaddr = inaddr;
		this.outaddr = outaddr;
		this.state = state;
		this.users = users;
		this.image = image;
	}
	public Orders() {
		super();
	}
	
	
}
