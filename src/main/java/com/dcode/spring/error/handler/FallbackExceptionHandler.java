package com.dcode.spring.error.handler;

import com.dcode.spring.error.utils.HttpStatusMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import java.util.Locale;
import java.util.function.Function;

@Component
public class FallbackExceptionHandler implements ApiExceptionHandler, MessageSourceAware {

    private final HttpStatusMapper httpStatusMapper;

    private MessageSource messageSource;

    public FallbackExceptionHandler(HttpStatusMapper httpStatusMapper) {
        this.httpStatusMapper = httpStatusMapper;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Function<Throwable, ProblemDetail> getHandler() {
        return ex -> {
            HttpStatusCode httpStatus = httpStatusMapper.getHttpStatus(ex.getClass());
            String defaultDetail = "";

            ErrorResponse.Builder builder = ErrorResponse.builder(ex, httpStatus, defaultDetail);
            ErrorResponse response = builder.build();



            ProblemDetail pb = response.getBody();
            pb.setTitle("Handled by Fallback Handler");

            return pb;
        };
    }

    private void updateProblemDetail(Throwable exception, ProblemDetail problemDetail, @Nullable MessageSource messageSource, Locale locale) {
        if (messageSource != null) {
            Object[] arguments = this.getDetailMessageArguments(messageSource, locale);
            String detail = messageSource.getMessage(this.getDetailMessageCode(exception), arguments, (String)null, locale);
            if (detail != null) {
                problemDetail.setDetail(detail);
            }
            String title = messageSource.getMessage(this.getTitleMessageCode(), (Object[])null, (String)null, locale);
            if (title != null) {
                problemDetail.setTitle(title);
            }
        }
    }

    @Override
    public boolean handles(Throwable ex) {
        return false;
    }

}
