package com.express.model;


public class Orders {
	private String id;
	private String inaddr;
	private String outaddr;
	private String state;
	private Users users;
	private Images image;
	private String year;
	private String month;
	private String date;
	private String time;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Orders() {
		super();
	}
	public Orders(String id, String inaddr, String outaddr, String state,
			Users users, Images image, String year, String month, String date,
			String time) {
		super();
		this.id = id;
		this.inaddr = inaddr;
		this.outaddr = outaddr;
		this.state = state;
		this.users = users;
		this.image = image;
		this.year = year;
		this.month = month;
		this.date = date;
		this.time = time;
	}
	
	
}
