package com.pandehoz.remindx.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class RepositoryConfig extends RepositoryRestMvcConfiguration {

	@Autowired
	RemindxInterceptor remindxint;
	
	@Override
    public RequestMappingHandlerMapping repositoryExporterHandlerMapping() {
        RequestMappingHandlerMapping mapping = super
                .repositoryExporterHandlerMapping();
        mapping.setInterceptors(new Object[] { remindxint});
        return mapping;
	}
}
