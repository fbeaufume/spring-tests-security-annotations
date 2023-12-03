package com.adeliosys.sample;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithInMemoryUserSecurityContextFactory.class)
public @interface WithInMemoryUser {

    String value(); // This is the username
}
