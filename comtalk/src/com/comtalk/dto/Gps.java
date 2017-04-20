package com.comtalk.dto;

import java.util.Date;

/**
 * Gps数据处理实体类
 * @author 12878
 *
 */
public class Gps implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String userId;
	private double longtitude;
	private double latitude;
	private int type;
	private Date recvTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getRecvTime() {
		return recvTime;
	}
	public void setRecvTime(Date recvTime) {
		this.recvTime = recvTime;
	}
	@Override
	public String toString() {
		return "Gps [id=" + id + ", userId=" + userId + ", longtitude=" + longtitude + ", latitude=" + latitude
				+ ", type=" + type + ", recvTime=" + recvTime + "]";
	}
	
	

}
