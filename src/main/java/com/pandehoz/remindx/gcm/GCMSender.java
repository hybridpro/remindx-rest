package com.pandehoz.remindx.gcm;

import java.util.Random;


public class GCMSender {
	
	/**
	   * Initial delay before first retry, without jitter.
	   */
	  protected static final int BACKOFF_INITIAL_DELAY = 1000;
	  
	  /**
	   * Maximum delay before a retry.
	   */
	  protected static final int MAX_BACKOFF_DELAY = 1024000;

	  protected final Random random = new Random();
	  
	
	private final String key;
	
	static <T> T nonNull(T argument) {
		if (argument == null) {
		  throw new IllegalArgumentException("argument cannot be null");
		    }
		    return argument;
	}
	
	/**
	   * Default constructor.
	   *
	   * @param key API key obtained through the Google API Console.
	   */
	  public GCMSender(String key) {
	    this.key = nonNull(key);
	  }
	  
	  

}
