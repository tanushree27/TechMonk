package com.project.techmonk.userservice.configuration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Configuration
@Slf4j
public class RequestLoggingFilterConfig extends AbstractRequestLoggingFilter {

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.info("Received request: {} {}", request.getMethod(), request.getRequestURI());
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
       //
    }
}
