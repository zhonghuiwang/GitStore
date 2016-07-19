package com.express.model;

public class Images {
	private String id;
	private String image;
	private String iyear;
	private String imonth;
	private String idate;
	private String itime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getIyear() {
		return iyear;
	}
	public void setIyear(String iyear) {
		this.iyear = iyear;
	}
	public String getImonth() {
		return imonth;
	}
	public void setImonth(String imonth) {
		this.imonth = imonth;
	}
	public String getIdate() {
		return idate;
	}
	public void setIdate(String idate) {
		this.idate = idate;
	}
	public String getItime() {
		return itime;
	}
	public void setItime(String itime) {
		this.itime = itime;
	}
	public Images(String id, String image, String iyear, String imonth,
			String idate, String itime) {
		super();
		this.id = id;
		this.image = image;
		this.iyear = iyear;
		this.imonth = imonth;
		this.idate = idate;
		this.itime = itime;
	}
	public Images() {
		super();
	}
		
	
}
