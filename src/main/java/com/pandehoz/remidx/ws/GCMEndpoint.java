package com.pandehoz.remidx.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pandehoz.remindx.pojos.GCMMessage;
import com.pandehoz.remindx.pojos.GCMResponse;
import com.pandehoz.remindx.pojos.GCMResult;
import com.pandehoz.remindx.pojos.Reminder;
import com.pandehoz.remindx.constants.GCMConstants;

public class GCMEndpoint {

	protected static final String UTF8 = "UTF-8";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private ReminderRepository	   reminders;

	  /**
	   * Initial delay before first retry, without jitter.
	   */
	  protected static final int BACKOFF_INITIAL_DELAY = 1000;
	  /**
	   * Maximum delay before a retry.
	   */
	  protected static final int MAX_BACKOFF_DELAY = 1024000;

	  protected final Random random = new Random();
	  protected static final Logger logger =
			  LoggerFactory.getLogger(GCMEndpoint.class.getName());


	  /**
	   * Sends a message to one device, retrying in case of unavailability.
	   *
	   * <p>
	   * <strong>Note: </strong> this method uses exponential back-off to retry in
	   * case of service unavailability and hence could block the calling thread
	   * for many seconds.
	   *
	   * @param message message to be sent, including the device's registration id.
	   * @param registrationId device where the message will be sent.
	   * @param retries number of retries in case of service unavailability errors.
	   *
	   * @return result of the request (see its javadoc for more details).
	   *
	   * @throws IllegalArgumentException if registrationId is {@literal null}.
	   * @throws InvalidRequestException if GCM didn't returned a 200 or 5xx status.
	   * @throws IOException if message could not be sent.
	   */
	  public boolean send(GCMMessage message, int retries){
	    int attempt = 0;
	    boolean result = false;
	    int backoff = BACKOFF_INITIAL_DELAY;
	    boolean tryAgain;
	    do {
	      attempt++;
	      if (logger != null) {
	        logger.info("Attempt #" + attempt + " to send message " +
	            message + " to regIds " + message.getRegistration_ids());
	      }
	      result = sendNoRetry(message);
	      tryAgain = result == false && attempt <= retries;
	      if (tryAgain) {
	        int sleepTime = backoff / 2 + random.nextInt(backoff);
	        sleep(sleepTime);
	        if (2 * backoff < MAX_BACKOFF_DELAY) {
	          backoff *= 2;
	        }
	      }
	    } while (tryAgain);
	    return result;
	  }

	  /**
	   * Sends a message without retrying in case of service unavailability. See
	   * {@link #send(Message, String, int)} for more info.
	   *
	   * @return result of the post, or {@literal null} if the GCM service was
	   *         unavailable or any network exception caused the request to fail.
	   *
	   * @throws InvalidRequestException if GCM didn't returned a 200 or 5xx status.
	   * @throws IllegalArgumentException if registrationId is {@literal null}.
	   */
	  public boolean sendNoRetry(GCMMessage message){
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authorization", "key=AIzaSyBar85ye7ZzYCQyJwGx0Q2kOO7ObgE1XUE");
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));  
	    
	    HttpEntity<GCMMessage> entity = new HttpEntity<GCMMessage>(message, headers);
	    
		ResponseEntity<GCMResponse> result = restTemplate.postForEntity(GCMConstants.GCM_SEND_ENDPOINT, entity, GCMResponse.class);  
	    int status = result.getStatusCode().value();
	    GCMResponse gcmresponse = result.getBody();
	    List<GCMResult> gcmresult = gcmresponse.getResults();
	    if(status / 100 == 5) {
	        logger.info("GCM service is unavailable (status " + status + ")");
	        return false;
	    }else if (status != 200) {
	    	//TODO 
	    	return true;
	    } else {
	    	Reminder rem = reminders.findOne(message.getData().getReminderId());
	    	List<String> oldReceived = rem.getSendeeswhoreceived();
	    	List<String> newList = new ArrayList<String>(oldReceived);
	    	
	    	if(gcmresponse.getFailure() == 0 && gcmresponse.getCanonical_ids() == 0){
	    		List<String> recentlySucceded = message.getRegistration_ids();		    	
		    	newList.addAll(recentlySucceded);
	    	}else{
	    		int index = 0;
	    		for(GCMResult res : gcmresult){
	    			if(res.getError() == null){
	    				newList.add(message.getRegistration_ids().get(index));
	    			}else{
	    				String error_code = res.getError();
	    				if(error_code.equalsIgnoreCase("Unavailable")) return false;
	    		    	else if(error_code.equalsIgnoreCase("NotRegistered"))
	    		    		return true; //TODO handle re-registration
	    			}
	    		}
	    	}
	    	
	    	rem.setSendeeswhoreceived(newList);
	    	reminders.save(rem);
	    	return true;
	    }
	    
	  }

	  
	  static <T> T nonNull(T argument) {
	    if (argument == null) {
	      throw new IllegalArgumentException("argument cannot be null");
	    }
	    return argument;
	  }

	  void sleep(long millis) {
	    try {
	      Thread.sleep(millis);
	    } catch (InterruptedException e) {
	      Thread.currentThread().interrupt();
	    }
	  }
}
