package com.pandehoz.remindx.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pendingusers")
public class PendingUser {
	
	@Id
	private String pendinguserid;

}
