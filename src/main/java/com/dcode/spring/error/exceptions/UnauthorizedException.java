package com.dcode.spring.error.exceptions;

import com.dcode.spring.error.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class UnauthorizedException extends ErrorResponseException {

    public UnauthorizedException(HttpStatusCode status) {
        super(status);
    }

    public UnauthorizedException(HttpStatusCode status, ProblemDetail body) {
        super(status, body, null, null, null);
    }

    public UnauthorizedException(String message, String errorCode) {
        this(HttpStatus.UNAUTHORIZED, ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, errorCode));
        this.getBody().setProperty(Constants.ATTRIBUTE_MESSAGE, message);
    }

    public UnauthorizedException(ProblemDetail problemDetail) {
        this(HttpStatus.UNAUTHORIZED, problemDetail);
    }
}
