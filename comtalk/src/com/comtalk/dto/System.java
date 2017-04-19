package com.comtalk.dto;

public class System implements java.io.Serializable{

	/**
	 * 系统参数实体类
	 * @author 12878
	 */
	private static final long serialVersionUID = 1L;
	private int callTime;
	private int pttTime;
	private int gpsReportInterval;
	private int appHeartInterval;
	private int audioHeartInterval;
	public int getCallTime() {
		return callTime;
	}
	public void setCallTime(int callTime) {
		this.callTime = callTime;
	}
	public int getPttTime() {
		return pttTime;
	}
	public void setPttTime(int pttTime) {
		this.pttTime = pttTime;
	}
	public int getGpsReportInterval() {
		return gpsReportInterval;
	}
	public void setGpsReportInterval(int gpsReportInterval) {
		this.gpsReportInterval = gpsReportInterval;
	}
	public int getAppHeartInterval() {
		return appHeartInterval;
	}
	public void setAppHeartInterval(int appHeartInterval) {
		this.appHeartInterval = appHeartInterval;
	}
	public int getAudioHeartInterval() {
		return audioHeartInterval;
	}
	public void setAudioHeartInterval(int audioHeartInterval) {
		this.audioHeartInterval = audioHeartInterval;
	}
	@Override
	public String toString() {
		return "System [callTime=" + callTime + ", pttTime=" + pttTime + ", gpsReportInterval=" + gpsReportInterval
				+ ", appHeartInterval=" + appHeartInterval + ", audioHeartInterval=" + audioHeartInterval + "]";
	}
	
	

}
