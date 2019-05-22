package es.upm.miw.business_services;

import es.upm.miw.TestConfig;
import es.upm.miw.exceptions.JwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class JwtServiceIT {

    @Autowired
    private JwtService jwtService;


    @BeforeEach
    void seed() {
    }

    @Test
    void testJwtExceptionNotBearer() {
        assertThrows(JwtException.class, () -> this.jwtService.user("Not Bearer"));
    }

    @Test
    void testJwtExceptionTokenError() {
        assertThrows(JwtException.class, () -> this.jwtService.user("Bearer error.error.error"));
    }

}
