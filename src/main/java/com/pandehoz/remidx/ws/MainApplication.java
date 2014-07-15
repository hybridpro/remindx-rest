
package com.pandehoz.remidx.ws;

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
import reactor.spring.context.config.EnableReactor;

@Configuration
@EnableMongoRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@ComponentScan
@EnableReactor
public class MainApplication{
    
	@Autowired
	private ReactorReceiver receiver;

    @Bean
    public CountDownLatch latch(Integer numberOfJokes) {
        return new CountDownLatch(numberOfJokes);
    }
    
	@Bean
	  public Reactor reactor(Environment env) {
		Logger log = LoggerFactory.getLogger("reactor");
		Reactor r = Reactors.reactor(env);
	    
	    r.on($("reminder"), receiver);
	    return r;
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
