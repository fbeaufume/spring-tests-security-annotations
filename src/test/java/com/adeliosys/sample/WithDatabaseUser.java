package com.adeliosys.sample;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithDatabaseUserSecurityContextFactory.class)
public @interface WithDatabaseUser {

    String value(); // This is the username
}
