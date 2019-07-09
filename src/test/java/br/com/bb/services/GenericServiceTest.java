package br.com.bb.services;

import br.com.bb.entitys.user.User;
import br.com.bb.repositorys.user.UserRepository;
import br.com.bb.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GenericServiceTest {

    private UserService userService;
    private String uuid;
    private User user;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        uuid = "5d23d0aa79c88e4e4e2747ab";
        user = new User(uuid, "erik@erik.com", "123456");
    }

    @Test
    void getAll() {
        userService.getUsers();
        Mockito.verify(userService).getUsers();
    }

    @Test
    void get() {
        userService.getOne(uuid);
        Mockito.verify(userService).getOne(uuid);
    }

    @Test
    void save() {
        userService.save(user);
        Mockito.verify(userService).save(user);
    }

    @Test
    void delete() {
        userService.delete(uuid);
        Mockito.verify(userService).delete(uuid);
    }
}