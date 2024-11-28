package com.dcode.spring.error.handler;

import com.dcode.spring.error.utils.Constants;
import com.dcode.spring.error.utils.HttpStatusMapper;
import com.dcode.spring.error.utils.ProblemDetailBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;
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
            String exceptionMessage = ex.getMessage();

            ProblemDetailBuilder builder = new ProblemDetailBuilder();
            ProblemDetail pb = builder.status(httpStatus).detail(exceptionMessage).build();

            updateProblemDetail(ex, pb, messageSource, LocaleContextHolder.getLocale());

            Optional.ofNullable(pb.getProperties())
                    .ifPresentOrElse(props -> props.computeIfAbsent(Constants.ATTRIBUTE_MESSAGE, k -> exceptionMessage),
                            () -> pb.setProperty(Constants.ATTRIBUTE_MESSAGE, exceptionMessage));

            return pb;
        };
    }

    private void updateProblemDetail(Throwable exception, ProblemDetail problemDetail,
            @Nullable MessageSource messageSource, Locale locale) {

        if (messageSource != null) {
            Object[] arguments = this.getDetailMessageArguments(messageSource, locale);
            String detail = messageSource.getMessage(this.getDetailMessageCode(exception), arguments, (String) null,
                    locale);

            if (detail != null) {
                problemDetail.setProperty(Constants.ATTRIBUTE_MESSAGE, detail);
            }

            String title = messageSource.getMessage(this.getTitleMessageCode(), (Object[]) null, (String) null, locale);

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
