package com.dcode.spring.error.auto.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.REACTIVE;

@ControllerAdvice
@ConditionalOnWebApplication(type = REACTIVE)
public class GlobalReactiveExceptionHandler extends CustomReactiveExceptionHandler {

}
