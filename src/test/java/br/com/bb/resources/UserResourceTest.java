package br.com.bb.resources;

import br.com.bb.entitys.user.User;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class UserResourceTest {

    private br.com.bb.resources.UserResource userResource;
    private String uuid;
    private User user;

    @BeforeEach
    void setUp() {
        userResource = Mockito.mock(br.com.bb.resources.UserResource.class);
        uuid = "5d23d0aa79c88e4e4e2747ab";
        user = new User(uuid, "erik@erik.com", "123456");
    }

    @Test
    void get() {
        userResource.get(uuid);
        Mockito.verify(userResource).get(uuid);
    }

    @Test
    void save() {
        userResource.save(user);
        Mockito.verify(userResource).save(user);
    }

    @Test
    void delete() {
        userResource.delete(uuid);
        Mockito.verify(userResource).delete(uuid);
    }

    @Test
    void getAll() {
        userResource.getAll();
        Mockito.verify(userResource).getAll();
    }
}