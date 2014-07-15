package com.pandehoz.remindx.pojos;

public class GCMResult {

	private String messageId;
	private String canonicalRegistrationId;
	private String errorCode;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getCanonicalRegistrationId() {
		return canonicalRegistrationId;
	}
	public void setCanonicalRegistrationId(String canonicalRegistrationId) {
		this.canonicalRegistrationId = canonicalRegistrationId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
