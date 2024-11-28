package com.dcode.spring.error.utils;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HttpStatusMapper {

    public HttpStatusCode getHttpStatus(Class<?> exceptionClass) {

        ResponseStatus responseStatus = AnnotationUtils.getAnnotation(exceptionClass, ResponseStatus.class);

        if (responseStatus != null) {
            return responseStatus.value();
        }

        return HttpStatusCode.valueOf(Integer.parseInt(Constants.HTTP_STATUS_500));
    }

}
