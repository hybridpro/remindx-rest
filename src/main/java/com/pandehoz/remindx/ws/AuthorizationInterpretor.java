package com.pandehoz.remindx.ws;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class AuthorizationInterpretor implements ClientHttpRequestInterceptor{

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		HttpHeaders headers = request.getHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "key=AIzaSyBar85ye7ZzYCQyJwGx0Q2kOO7ObgE1XUE");
        return execution.execute(request, body);
	}

}
