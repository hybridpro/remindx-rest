package com.pandehoz.remindx.pojos;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class GCMData {

	private String reminderId;

	private Date remindTo;
	
	private Date remindFrom;
	
	private String locationLong;
	
	private String locationLat;
	
	private String text;
	
	private boolean now;
	
	private String sender;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReminderId() {
		return reminderId;
	}

	public void setReminderId(String reminderId) {
		this.reminderId = reminderId;
	}
	
	public Date getRemindTo() {
		return remindTo;
	}

	public void setRemindTo(Date remindTo) {
		this.remindTo = remindTo;
	}

	public Date getRemindFrom() {
		return remindFrom;
	}

	public void setRemindFrom(Date remindFrom) {
		this.remindFrom = remindFrom;
	}

	public String getLocationLong() {
		return locationLong;
	}

	public void setLocationLong(String locationLong) {
		this.locationLong = locationLong;
	}

	public String getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(String locationLat) {
		this.locationLat = locationLat;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isNow() {
		return now;
	}

	public void setNow(boolean now) {
		this.now = now;
	}
}
