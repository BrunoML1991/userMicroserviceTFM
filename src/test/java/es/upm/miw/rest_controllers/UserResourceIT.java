package es.upm.miw.rest_controllers;

import es.upm.miw.documents.User;
import es.upm.miw.dtos.UserDto;
import es.upm.miw.dtos.UserMinimumDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
class UserResourceIT {

    @Autowired
    private RestService restService;

    @Test
    void testLogin() {
        this.restService.loginAdmin();
        assertTrue(this.restService.getTokenDto().getToken().length() > 10);
    }

    @Test
    void testLoginAdminPassNull() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                this.restService.logout().restBuilder().basicAuth(restService.getAdminMobile(), "kk")
                        .path(UserResource.USERS).path(UserResource.TOKEN).post().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testLoginNonMobile() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                this.restService.logout().restBuilder().basicAuth("1", "kk")
                        .path(UserResource.USERS).path(UserResource.TOKEN).post().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testReadAdminAdmin() {
        UserDto userDto = this.restService.loginAdmin().restBuilder(new RestBuilder<UserDto>()).clazz(UserDto.class)
                .path(UserResource.USERS).path(UserResource.MOBILE_ID).expand(this.restService.getAdminMobile())
                .get().build();
        assertNotNull(userDto);
    }

    @Test
    void testReadManagerOperator() {
        UserDto userDto = this.restService.loginManager().restBuilder(new RestBuilder<UserDto>()).clazz(UserDto.class)
                .path(UserResource.USERS).path(UserResource.MOBILE_ID).expand("666666002")
                .get().build();
        assertNotNull(userDto);
    }

    @Test
    void testReadOperatorCustomer() {
        UserDto userDto = this.restService.loginOperator().restBuilder(new RestBuilder<UserDto>()).clazz(UserDto.class)
                .path(UserResource.USERS).path(UserResource.MOBILE_ID).expand("666666004")
                .get().build();
        assertNotNull(userDto);
    }

    @Test
    void testReadOperatorOperator() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                this.restService.loginOperator().restBuilder(new RestBuilder<User>()).clazz(User.class).log()
                        .path(UserResource.USERS).path(UserResource.MOBILE_ID).expand("666666003")
                        .get().build());
        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
    }

    @Test
    void testReadAll() {
        List<UserMinimumDto> userMinimumDtoList = Arrays.asList(this.restService.loginAdmin()
                .restBuilder(new RestBuilder<UserMinimumDto[]>()).clazz(UserMinimumDto[].class)
                .path(UserResource.USERS)
                .get().build());
        assertTrue(userMinimumDtoList.size() > 1);
    }

}
