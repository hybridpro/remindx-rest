package com.pandehoz.remidx.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.pandehoz.remidx.reactor.ReactorPublisher;
import com.pandehoz.remindx.pojos.Reminder;

@Controller
@RequestMapping("/remindx/v1.0")
public class RemindxController {

	//ReactorPublisher publisher;
	
    @RequestMapping(method=RequestMethod.POST, value="/addReminder")
    public void addReminder(@RequestBody Reminder reminder ) {
    	RestTemplate restTemplate = new RestTemplate();
    	restTemplate.postForObject("http://localhost:8080/reminder", reminder, Reminder.class);
    	//publisher.publishReminder(reminder);
    }

}
