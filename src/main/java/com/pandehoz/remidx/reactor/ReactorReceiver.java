package com.pandehoz.remidx.reactor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import reactor.event.Event;
import reactor.function.Consumer;
import reactor.spring.annotation.Selector;

import com.pandehoz.remindx.pojos.Reminder;

@Component
public class ReactorReceiver{

	RestTemplate restTemplate = new RestTemplate();
	
	@Selector(value="reminder", reactor="@rootReactor")
	public void handleReminderEvent(Event<Reminder> rem) {
		System.out.println("Location lat : " + rem.getData().getLocationlat());
	}

}
