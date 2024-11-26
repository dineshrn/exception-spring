package com.dcode.spring.error.exceptions;

import com.dcode.spring.error.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ServiceUnavailableException extends ErrorResponseException {

    public ServiceUnavailableException(HttpStatusCode status) {
        super(status);
    }

    public ServiceUnavailableException(HttpStatusCode status, ProblemDetail body) {
        super(status, body, null, null, null);
    }

    public ServiceUnavailableException(String message, String errorCode) {
        this(HttpStatus.SERVICE_UNAVAILABLE,
                ProblemDetail.forStatusAndDetail(HttpStatus.SERVICE_UNAVAILABLE, errorCode));
        this.getBody().setProperty(Constants.ATTRIBUTE_MESSAGE, message);
    }

    public ServiceUnavailableException(ProblemDetail problemDetail) {
        this(HttpStatus.SERVICE_UNAVAILABLE, problemDetail);
    }
}
