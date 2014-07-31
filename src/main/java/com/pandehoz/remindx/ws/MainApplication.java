
package com.pandehoz.remindx.ws;

import static reactor.event.selector.Selectors.$;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.pandehoz.remindx.pojos.Reminder;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;
import reactor.event.selector.ClassSelector;
import reactor.function.Consumer;
import reactor.spring.context.config.EnableReactor;

@Configuration
@EnableMongoRepositories
@Import(RepositoryConfig.class)
@EnableAutoConfiguration
@ComponentScan
@EnableReactor
public class MainApplication{
    
	@Autowired
	private ReminderReceiver receiver;
	
	@Bean
	  public Reactor reactor(Environment env) {
		Reactor r = Reactors.reactor(env);
	    
	    r.on($("reminder"), receiver);
	    r.on($("reminderid"), receiver);
	    r.on(new ClassSelector(NullPointerException.class), new Consumer<Event<NullPointerException>>() {

			@Override
			public void accept(Event<NullPointerException> t) {
				System.out.println("Exception thrown : " + t.getData().getStackTrace());
				
			} });
	    return r;
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
