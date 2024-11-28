package com.dcode.spring.error.utils;

import com.dcode.spring.error.handler.ApiExceptionHandler;
import com.dcode.spring.error.handler.FallbackExceptionHandler;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class ServletExceptionParser implements ExceptionParserFacade {

    private final List<ApiExceptionHandler> handlers;

    private final FallbackExceptionHandler fallbackExceptionHandler;

    public ServletExceptionParser(List<ApiExceptionHandler> handlers,
            FallbackExceptionHandler fallbackExceptionHandler) {
        this.handlers = handlers;
        this.fallbackExceptionHandler = fallbackExceptionHandler;
    }

    @Override
    public ProblemDetail parse(Throwable exception, Locale locale) {
        ProblemDetail problemDetail = null;
        for (ApiExceptionHandler handler : handlers) {
            if (handler.handles(exception)) {
                problemDetail = handler.getHandler().apply(exception);
                break;
            }
        }
        if (problemDetail == null) {
            problemDetail = fallbackExceptionHandler.getHandler().apply(exception);
        }
        return problemDetail;
    }
}
