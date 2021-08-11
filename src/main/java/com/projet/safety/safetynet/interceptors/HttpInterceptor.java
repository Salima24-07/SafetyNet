package com.projet.safety.safetynet.interceptors;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HttpInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LogManager.getLogger("SafetyNet");
	
	@Override
	public boolean preHandle
		(HttpServletRequest request, HttpServletResponse response, Object handler) 
				throws Exception {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	    logger.info("A new client request: ");
	    logger.info("Request URI: " + request.getRequestURI());
	    logger.info("Request Method: " + request.getMethod());
	    logger.info("Start Time: " + timeStamp);
	    return true;
	   }
	
	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
	      Object handler, ModelAndView modelAndView) throws Exception {
	      
		   String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		    logger.info("Sending response: ");
		    logger.info("Response status: " + response.getStatus());
		    logger.info("Start Time: " + timeStamp);
	   }
	   @Override
	   public void afterCompletion
	      (HttpServletRequest request, HttpServletResponse response, Object 
	      handler, Exception exception) throws Exception {
	      
		   logger.info("Request and Response is completed");
	   }

}
