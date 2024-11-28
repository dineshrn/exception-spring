package com.dcode.spring.error.auto.config;

import com.dcode.spring.error.logging.CommonLogger;
import com.dcode.spring.error.utils.ExceptionParserFacade;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

@ControllerAdvice
@ConditionalOnWebApplication(type = SERVLET)
public class GlobalServletExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionParserFacade exceptionParserFacade;

    public GlobalServletExceptionHandler(ExceptionParserFacade exceptionParserFacade) {
        this.exceptionParserFacade = exceptionParserFacade;
    }

    @ResponseBody
    @ExceptionHandler
    public ProblemDetail handleException(Throwable exception, HttpServletRequest request, Locale locale) {
        CommonLogger.error("Request to URI" + request.getRequestURI() + "failed with Exception");
        ProblemDetail problemDetail = exceptionParserFacade.parse(exception, locale);
        CommonLogger.error(problemDetail.toString());
        return problemDetail;
    }

}
