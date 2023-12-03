package com.adeliosys.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithDatabaseUserSecurityContextFactory implements WithSecurityContextFactory<WithDatabaseUser> {

    @Autowired
    private DatabaseUserDetailsService databaseUserDetailsService;

    @Override
    public SecurityContext createSecurityContext(WithDatabaseUser annotation) {
        // Load the user details using the values from the annotation and from a chosen user detail service
        // Note that the actual return type is CustomUserDetails
        UserDetails userDetails = databaseUserDetailsService.loadUserByUsername(annotation.value());

        // Build the authentication and set it to the Spring security context
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        return context;
    }
}
