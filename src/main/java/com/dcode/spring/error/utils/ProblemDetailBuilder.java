package com.dcode.spring.error.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ProblemDetailBuilder {

    private HttpStatusCode status;

    private String title;

    private URI type;

    private String detail;

    private URI instance;

    private final Map<String, Object> additionalProperties = new HashMap<>();

    public ProblemDetailBuilder status(HttpStatusCode status) {
        this.status = status;
        return this;
    }

    public ProblemDetailBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ProblemDetailBuilder detail(String detail) {
        this.detail = detail;
        return this;
    }

    public ProblemDetailBuilder type(URI type) {
        this.type = type;
        return this;
    }

    public ProblemDetailBuilder instance(URI instance) {
        this.instance = instance;
        return this;
    }

    public ProblemDetailBuilder addProperty(String key, Object value) {
        this.additionalProperties.put(key, value);
        return this;
    }

    public ProblemDetail build() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setType(type);
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setInstance(instance);
        additionalProperties.forEach(problemDetail::setProperty);
        return problemDetail;
    }

}
