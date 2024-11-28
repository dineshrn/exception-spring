package com.dcode.spring.error.utils;

import org.springframework.http.ProblemDetail;

import java.util.Locale;

public interface ExceptionParserFacade {

    ProblemDetail parse(Throwable exception, Locale locale);
}
