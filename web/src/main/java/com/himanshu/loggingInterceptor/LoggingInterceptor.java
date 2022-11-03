package com.himanshu.loggingInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    final static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        final String logMessage = "Incoming request : " + request.getMethod() + " " + request.getRequestURI().toString() + " " + response.getStatus() ;

        logger.info(logMessage);
    }

}
