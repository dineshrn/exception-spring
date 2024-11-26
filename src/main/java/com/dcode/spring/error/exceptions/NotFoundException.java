package com.dcode.spring.error.exceptions;

import com.dcode.spring.error.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class NotFoundException extends ErrorResponseException {

    public NotFoundException(HttpStatusCode status) {
        super(status);
    }

    public NotFoundException(HttpStatusCode status, ProblemDetail body) {
        super(status, body, null, null, null);
    }

    public NotFoundException(String message, String errorCode) {
        this(HttpStatus.NOT_FOUND, ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, errorCode));
        this.getBody().setProperty(Constants.ATTRIBUTE_MESSAGE, message);
    }

    public NotFoundException(ProblemDetail problemDetail) {
        this(HttpStatus.NOT_FOUND, problemDetail);
    }
}
