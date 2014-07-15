package com.pandehoz.remidx.ws;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import reactor.event.Event;
import reactor.function.Consumer;
import reactor.spring.annotation.Selector;

import com.pandehoz.remindx.pojos.GCMData;
import com.pandehoz.remindx.pojos.GCMMessage;
import com.pandehoz.remindx.pojos.Reminder;

@Service
public class ReactorReceiver implements Consumer<Event<Reminder>> {

	@Autowired
    CountDownLatch latch;
	
	@Autowired
	GCMEndpoint endpoint;
	
	RestTemplate restTemplate = new RestTemplate();
	
	public void accept(Event<Reminder> e) {
		GCMData data = new GCMData();
		GCMMessage message = new GCMMessage();
		Reminder rem = e.getData();
		
		data.setLocationLat(rem.getLocationlat());
		data.setLocationLong(rem.getLocationlong());
		data.setNow(rem.isNow());
		data.setRemindFrom(rem.getRemindfrom());
		data.setRemindTo(rem.getRemindtill());
		data.setText(rem.getText());
		
		message.setData(data);
		message.setRegistration_ids(rem.getSendeeregistrationids());
		
		endpoint.send(message, 5);
	}

}
