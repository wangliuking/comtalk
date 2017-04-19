package com.comtalk.dto;

import java.util.Date;

/**
 * user实体类
 * @author 12878
 * @date 2017-4-16
 *
 */
public class User implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String password;
	private String authenticateCode;
	private int priority;
	private int type;
	private Date loginTime;
	private int loginStatus;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthenticateCode() {
		return authenticateCode;
	}
	public void setAuthenticateCode(String authenticateCode) {
		this.authenticateCode = authenticateCode;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getloginTime() {
		return loginTime;
	}
	public void setloginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", authenticateCode="
				+ authenticateCode + ", priority=" + priority + ", type=" + type + ", loginTime=" + loginTime
				+ ", loginStatus=" + loginStatus + "]";
	}
	
	
}
