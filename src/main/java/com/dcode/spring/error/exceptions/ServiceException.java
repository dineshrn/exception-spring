package com.dcode.spring.error.exceptions;

import com.dcode.spring.error.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ServiceException extends ErrorResponseException {

    public ServiceException(HttpStatusCode status) {
        super(status);
    }

    public ServiceException(HttpStatusCode status, ProblemDetail body) {
        super(status, body, null, null, null);
    }

    public ServiceException(String message, String errorCode) {
        this(HttpStatus.INTERNAL_SERVER_ERROR,
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, errorCode));
        this.getBody().setProperty(Constants.ATTRIBUTE_MESSAGE, message);
    }

    public ServiceException(ProblemDetail problemDetail) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, problemDetail);
    }
}
