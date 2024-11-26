package com.dcode.spring.error.exceptions;

import com.dcode.spring.error.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class DataConflictException extends ErrorResponseException {

    public DataConflictException(HttpStatusCode status) {
        super(status);
    }

    public DataConflictException(HttpStatusCode status, ProblemDetail body) {
        super(status, body, null, null, null);
    }

    public DataConflictException(String message, String errorCode) {
        this(HttpStatus.CONFLICT, ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, errorCode));
        this.getBody().setProperty(Constants.ATTRIBUTE_MESSAGE, message);
    }

    public DataConflictException(ProblemDetail problemDetail) {
        this(HttpStatus.CONFLICT, problemDetail);
    }
}
