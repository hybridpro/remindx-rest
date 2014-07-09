package com.pandehoz.remidx.ws;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pandehoz.remindx.pojos.Group;

@RepositoryRestResource(collectionResourceRel = "group", path = "group")
public interface GroupRepository extends MongoRepository<Group, String> {

	
}
