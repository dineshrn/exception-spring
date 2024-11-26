package com.dcode.spring.error.exceptions;

import com.dcode.spring.error.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class AccessForbiddenException extends ErrorResponseException {

    public AccessForbiddenException(HttpStatusCode status) {
        super(status);
    }

    public AccessForbiddenException(HttpStatusCode status, ProblemDetail body) {
        super(status, body, null, null, null);
    }

    public AccessForbiddenException(String message, String errorCode) {
        this(HttpStatus.FORBIDDEN, ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, errorCode));
        this.getBody().setProperty(Constants.ATTRIBUTE_MESSAGE, message);
    }

    public AccessForbiddenException(ProblemDetail problemDetail) {
        this(HttpStatus.FORBIDDEN, problemDetail);
    }
}
