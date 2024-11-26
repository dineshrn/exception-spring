package com.dcode.spring.error.exceptions;

import com.dcode.spring.error.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class BadRequestException extends ErrorResponseException {

    public BadRequestException(HttpStatusCode status) {
        super(status);
    }

    public BadRequestException(HttpStatusCode status, ProblemDetail body) {
        super(status, body, null, null, null);
    }

    public BadRequestException(String message, String errorCode) {
        this(HttpStatus.BAD_REQUEST, ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errorCode));
        this.getBody().setProperty(Constants.ATTRIBUTE_MESSAGE, message);
    }

    public BadRequestException(ProblemDetail problemDetail) {
        this(HttpStatus.BAD_REQUEST, problemDetail);
    }
}
