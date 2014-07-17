package com.pandehoz.remindx.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;

import com.pandehoz.remindx.pojos.Reminder;

@Service
public class ReactorPublisher {

	@Autowired
	private Reactor reactor;
	
	public void publishReminder(Reminder reminder){
		reactor.notify("reminder", Event.wrap(reminder));
	}
	
	public void publishReminder(String reminderid){
		reactor.notify("reminder", Event.wrap(reminderid));
	}
}
