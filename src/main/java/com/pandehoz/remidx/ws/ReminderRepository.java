package com.pandehoz.remidx.ws;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pandehoz.remindx.pojos.Reminder;

@RepositoryRestResource(collectionResourceRel = "reminder", path = "reminder")
public interface ReminderRepository extends MongoRepository<Reminder, String> {

	List<Reminder> findBySendee(@Param("sendee") String sendee);
}
