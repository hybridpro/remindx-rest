package com.pandehoz.remindx.pojos;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GCMMessage {

	private  String collapseKey;
	  private  Boolean delayWhileIdle;
	  private  Integer timeToLive;
	  private  GCMData data;
	  
	  private  Boolean dryRun;
	  private  String restrictedPackageName;
	  
	  private List<String> registration_ids;
	  
	  public List<String> getRegistration_ids() {
		return registration_ids;
	}
	public void setRegistration_ids(List<String> registration_ids) {
		this.registration_ids = registration_ids;
	}
	public String getCollapseKey() {
		return collapseKey;
	}
	public void setCollapseKey(String collapseKey) {
		this.collapseKey = collapseKey;
	}
	public Boolean getDelayWhileIdle() {
		return delayWhileIdle;
	}
	public void setDelayWhileIdle(Boolean delayWhileIdle) {
		this.delayWhileIdle = delayWhileIdle;
	}
	public Integer getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}
	public GCMData getData() {
		return data;
	}
	public void setData(GCMData data) {
		this.data = data;
	}
	public Boolean getDryRun() {
		return dryRun;
	}
	public void setDryRun(Boolean dryRun) {
		this.dryRun = dryRun;
	}
	public String getRestrictedPackageName() {
		return restrictedPackageName;
	}
	public void setRestrictedPackageName(String restrictedPackageName) {
		this.restrictedPackageName = restrictedPackageName;
	}
	
	  
}
