package com.dcode.spring.error.auto.config;

import com.dcode.spring.error.logging.CommonLogger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class CustomServletExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> createResponseEntity(@Nullable Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {
        if (body instanceof ProblemDetail problemDetail) {
            //customize the exception
            CommonLogger.info(body.toString() + statusCode.toString() + request.toString() + problemDetail.toString());

        }
        return new ResponseEntity<>(body, headers, statusCode);
    }
}
