
package com.pandehoz.remidx.ws;

import static reactor.event.selector.Selectors.$;

import java.util.concurrent.CountDownLatch;

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

import com.pandehoz.remidx.reactor.ReactorPublisher;
import com.pandehoz.remidx.reactor.ReactorReceiver;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

@Configuration
@EnableMongoRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@ComponentScan
@EnableReactor
public class MainApplication{
    
	@Bean
	  public Reactor rootReactor(Environment env) {
	    // implicit Environment is injected into bean def method
	    return Reactors.reactor().env(env).get();
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
