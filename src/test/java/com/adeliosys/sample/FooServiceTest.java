package com.adeliosys.sample;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FooServiceTest {

    @Autowired
    private FooService fooService;

    @BeforeAll
    static void beforeAll(@Autowired UserRepository userRepository) {
        userRepository.save(new User("paul-smith", "ADMIN"));
    }

    @AfterAll
    static void afterAll(@Autowired UserRepository userRepository) {
        userRepository.deleteAll();
    }

    @Test
    void getDate() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> fooService.getDate());
    }

    @Test
    @WithMockUser
    void getDateAsUser() {
        assertThrows(AccessDeniedException.class, () -> fooService.getDate());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getDateAsAdmin1() {
        assertNotNull(fooService.getDate());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void getDateAsAdmin2() {
        assertNotNull(fooService.getDate());
    }

    @Test
    @WithAdmin
    void getDateAsAdmin3() {
        assertNotNull(fooService.getDate());
    }

    @Test
    @WithUserDetails(value = "paul-smith", userDetailsServiceBeanName = "databaseUserDetailsService")
    void getDateAsAdmin4() {
        assertNotNull(fooService.getDate());
    }

    @Test
    @WithUserDetails(value = "test-admin", userDetailsServiceBeanName = "inMemoryUserDetailsService")
    void getDateAsAdmin5() {
        assertNotNull(fooService.getDate());
    }

    @Test
    @WithDatabaseUser("paul-smith")
    void getDateAsAdmin6() {
        assertNotNull(fooService.getDate());
    }

    @Test
    @WithInMemoryUser("test-admin")
    void getDateAsAdmin7() {
        assertNotNull(fooService.getDate());
    }
}
