package es.upm.miw.data_services;

import es.upm.miw.TestConfig;
import es.upm.miw.documents.User;
import es.upm.miw.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class DatabaseSeederServiceIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DatabaseSeederService databaseSeederService;

    @Test
    void testDeleteAllAndInitialize() {
        this.databaseSeederService.deleteAllAndInitialize();
        assertFalse(userRepository.findByMobile("666666001").isPresent());
        this.databaseSeederService.seedDatabase();
        User user = userRepository.findByMobile("666666001").get();
        assertEquals("u001", user.getUsername());
        assertEquals("u001@gmail.com", user.getEmail());
        assertEquals("66666600L", user.getDni());
        assertTrue(user.getRoles().length >= 1);
    }

}
