package paixao.erik.services;

import paixao.erik.entitys.user.User;
import paixao.erik.services.user.UserService;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class UserServiceTest extends GenericServiceTest<User, UserService> {

    UserServiceTest() {
        super(UserService.class, new User("5d23d0aa79c88e4e4e2747ab", "erik@erik.com", "123456"));
    }

}
