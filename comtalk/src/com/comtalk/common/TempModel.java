package com.comtalk.common;

public class TempModel {
	private String temp;
	private String tempGroup;
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getTempGroup() {
		return tempGroup;
	}
	public void setTempGroup(String tempGroup) {
		this.tempGroup = tempGroup;
	}
	@Override
	public String toString() {
		return "TempModel [temp=" + temp + ", tempGroup=" + tempGroup + "]";
	}

}
