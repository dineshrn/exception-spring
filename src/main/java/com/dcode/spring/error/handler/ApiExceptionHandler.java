package com.dcode.spring.error.handler;

import org.springframework.context.MessageSource;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.Nullable;

import java.util.Locale;
import java.util.function.Function;

public interface ApiExceptionHandler {

    Function<Throwable, ProblemDetail> getHandler();

    boolean handles(Throwable ex);

    default Object[] getDetailMessageArguments(MessageSource messageSource, Locale locale) {
        return new Object[0];
    }

    default String getDetailMessageCode(Throwable ex) {
        return getDefaultDetailMessageCode(ex.getClass(), (String) null);
    }

    static String getDefaultDetailMessageCode(Class<?> exceptionType, @Nullable String suffix) {
        String var10000 = exceptionType.getName();
        return "problemDetail." + var10000 + (suffix != null ? "." + suffix : "");
    }

    default String getTitleMessageCode() {
        return getDefaultTitleMessageCode(this.getClass());
    }

    static String getDefaultTitleMessageCode(Class<?> exceptionType) {
        return "problemDetail.title." + exceptionType.getName();
    }

}

