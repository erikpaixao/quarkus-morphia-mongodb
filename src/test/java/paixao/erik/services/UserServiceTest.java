package paixao.erik.services;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import paixao.erik.entitys.user.User;
import paixao.erik.services.user.UserService;

@QuarkusTest
public class UserServiceTest {

    public UserService userService;
    public String uuid;
    public User user;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        uuid = "5d23d0aa79c88e4e4e2747ab";
        user = new User(uuid, "erik@erik.com", "123456");
    }

    @Test
    public void getAll() {
        userService.getAll();
        Mockito.verify(userService).getAll();
    }

    @Test
    public void get() {
        userService.getOne(uuid);
        Mockito.verify(userService).getOne(uuid);
    }

    @Test
    public void save() {
        userService.save(user);
        Mockito.verify(userService).save(user);
    }

    @Test
    public void delete() {
        userService.delete(uuid);
        Mockito.verify(userService).delete(uuid);
    }
}