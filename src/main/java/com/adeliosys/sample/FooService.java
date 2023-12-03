package com.adeliosys.sample;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FooService {

    @Secured("ROLE_ADMIN")
    public Date getDate() {
        return new Date();
    }
}
