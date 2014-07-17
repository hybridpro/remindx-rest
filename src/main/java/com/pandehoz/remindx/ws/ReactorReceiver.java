package com.pandehoz.remindx.ws;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import reactor.event.Event;
import reactor.function.Consumer;

import com.pandehoz.remindx.pojos.GCMData;
import com.pandehoz.remindx.pojos.GCMMessage;
import com.pandehoz.remindx.pojos.Reminder;
import com.pandehoz.remindx.pojos.User;

@Service
@ComponentScan(basePackages = {"com.pandehoz.remindx.pojos"})
public class ReactorReceiver implements Consumer<Event<String>> {
	
	@Autowired
	private ReminderRepository reminders;
	
	@Autowired
	private UserRepository users;
	
	@Autowired
	private GCMEndpoint endpoint;
	
	@Autowired
	GCMData data;
	
	@Autowired
	GCMMessage message;
	
	public void accept(Event<String> e){
		
		data = new GCMData();
		message = new GCMMessage();
		String reminderid = e.getData();
		
		Reminder reminder = reminders.findOne(reminderid);
		
		data.setReminderId(reminderid);
		data.setSender(reminder.getSender());
		data.setLocationLat(reminder.getLocationlat());
		data.setLocationLong(reminder.getLocationlong());
		data.setNow(reminder.isNow());
		data.setRemindFrom(reminder.getRemindfrom());
		data.setRemindTo(reminder.getRemindtill());
		data.setText(reminder.getText());
		
		message.setData(data);
		
		List<String> allSendees = reminder.getSendeeids();
		List<String> allRegistrationIds = new ArrayList<String>();
		
		Map<String, String> sendeeRegidsMap = new HashMap<>();
		for(String sendee : allSendees){
			User user = users.findOne(sendee);
			String regId = user.getRegistrationid();
			allRegistrationIds.add(regId);		
			sendeeRegidsMap.put(regId, sendee);
		}
		message.setRegistration_ids(allRegistrationIds);
		
		endpoint.send(message, 5);
	}
	
	/*public void accept(Event<Reminder> e) {
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
	}*/

}
