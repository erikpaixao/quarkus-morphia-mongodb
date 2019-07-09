package paixao.erik.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import paixao.erik.entitys.user.User;
import paixao.erik.resources.user.UserResource;

@QuarkusTest
public class UserResourceTest {

    public UserResource userResource;
    public String uuid;
    public User user;

    @BeforeEach
    public void setUp() {
        userResource = Mockito.mock(UserResource.class);
        uuid = "5d23d0aa79c88e4e4e2747ab";
        user = new User(uuid, "erik@erik.com", "123456");
    }

    @Test
    public void get() {
        userResource.get(uuid);
        Mockito.verify(userResource).get(uuid);
    }

    @Test
    public void save() {
        userResource.save(user);
        Mockito.verify(userResource).save(user);
    }

    @Test
    public void delete() {
        userResource.delete(uuid);
        Mockito.verify(userResource).delete(uuid);
    }

    @Test
    public void getAll() {
        userResource.getAll();
        Mockito.verify(userResource).getAll();
    }
}