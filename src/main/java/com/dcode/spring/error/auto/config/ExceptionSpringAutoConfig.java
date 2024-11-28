package com.dcode.spring.error.auto.config;

import com.dcode.spring.error.handler.ApiExceptionHandler;
import com.dcode.spring.error.handler.FallbackExceptionHandler;
import com.dcode.spring.error.utils.ServletExceptionParser;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@AutoConfiguration
@ComponentScan(basePackages = {"com.dcode.spring.error.handler, com.dcode.spring.error.utils"})
public class ExceptionSpringAutoConfig {

    @Bean
    public GlobalServletExceptionHandler globalHandler(ServletExceptionParser servletExceptionParser) {
        return new GlobalServletExceptionHandler(servletExceptionParser);
    }

    @Bean
    public ServletExceptionParser servletExceptionParser(List<ApiExceptionHandler> handlers,
            FallbackExceptionHandler fallbackExceptionHandler) {
        return new ServletExceptionParser(handlers, fallbackExceptionHandler);
    }
}
