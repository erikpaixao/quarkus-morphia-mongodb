package paixao.erik.resources;

import paixao.erik.entitys.user.User;
import io.quarkus.test.junit.QuarkusTest;
import paixao.erik.resources.user.UserResource;

@QuarkusTest
class UserResourceTest extends GenericResourceTest<User, UserResource> {

    UserResourceTest() {
        super(UserResource.class, new User("5d23d0aa79c88e4e4e2747ab", "erik@erik.com", "123456"));
    }

}
