package com.pandehoz.remindx.pojos;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reminders")
public class Reminder {

	@Id
	private String reminderid;
	
	private String sender;
	
	private String sendee;
	
	private String group;
	
	private String text;
	
	private String requestingdevice;
	
	private Date remindfrom;
	
	private Date remindtill;
	
	private boolean recurring;
	
	private boolean sendtogroup;
	
	private boolean performed;
	
	private boolean accepted;
	
	private boolean expired;
	
	private boolean reachedourserver;
	
	private boolean reachedpushserver;
	
	private boolean reacheddevice;
	
	public boolean isPerformed() {
		return performed;
	}

	public void setPerformed(boolean performed) {
		this.performed = performed;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isReachedourserver() {
		return reachedourserver;
	}

	public void setReachedourserver(boolean reachedourserver) {
		this.reachedourserver = reachedourserver;
	}

	public boolean isReachedpushserver() {
		return reachedpushserver;
	}

	public void setReachedpushserver(boolean reachedpushserver) {
		this.reachedpushserver = reachedpushserver;
	}

	public boolean isReacheddevice() {
		return reacheddevice;
	}

	public void setReacheddevice(boolean reacheddevice) {
		this.reacheddevice = reacheddevice;
	}

	private boolean now;
	
	private String locationlong;
	
	private String locationlat;

	public String getReminderid() {
		return reminderid;
	}

	public void setReminderid(String reminderid) {
		this.reminderid = reminderid;
	}


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendee() {
		return sendee;
	}

	public void setSendee(String sendee) {
		this.sendee = sendee;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRequestingdevice() {
		return requestingdevice;
	}

	public void setRequestingdevice(String requestingdevice) {
		this.requestingdevice = requestingdevice;
	}

	public Date getRemindfrom() {
		return remindfrom;
	}

	public void setRemindfrom(Date remindfrom) {
		this.remindfrom = remindfrom;
	}

	public Date getRemindtill() {
		return remindtill;
	}

	public void setRemindtill(Date remindtill) {
		this.remindtill = remindtill;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public boolean isSendtogroup() {
		return sendtogroup;
	}

	public void setSendtogroup(boolean sendtogroup) {
		this.sendtogroup = sendtogroup;
	}

	public boolean isNow() {
		return now;
	}

	public void setNow(boolean now) {
		this.now = now;
	}

	public String getLocationlong() {
		return locationlong;
	}

	public void setLocationlong(String locationlong) {
		this.locationlong = locationlong;
	}

	public String getLocationlat() {
		return locationlat;
	}

	public void setLocationlat(String locationlat) {
		this.locationlat = locationlat;
	}
	
	
}
