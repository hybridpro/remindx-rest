package com.pandehoz.remindx.ws;
import java.net.URLDecoder;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RemindxInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ReactorPublisher publisher; 
	
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		String httpmethod = request.getMethod();
		String requestURI = request.getRequestURI();
		
		if(httpmethod.equalsIgnoreCase("POST") && requestURI.equalsIgnoreCase("/reminder")){
			String reminderidEncoded = null;
			String reminderUrl = response.getHeader("Location");
			StringTokenizer tokenizer = new StringTokenizer(reminderUrl, "/");
			while(tokenizer.hasMoreElements()){
				reminderidEncoded = tokenizer.nextToken();
			}
			String reminderid = URLDecoder.decode(reminderidEncoded, "UTF-8");
			
			publisher.publishReminder(reminderid);
		}
		/*String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();*/
		System.out.println(httpmethod + requestURI);
	}
}
