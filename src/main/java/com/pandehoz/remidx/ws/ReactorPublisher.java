package com.pandehoz.remidx.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;

import com.pandehoz.remindx.pojos.Reminder;

@Service
public class ReactorPublisher {

	@Autowired
	private Reactor rootReactor;
	
	public void publishReminder(Reminder reminder){
		rootReactor.notify("reminder", Event.wrap(reminder));
	}
}
