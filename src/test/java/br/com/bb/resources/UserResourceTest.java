package br.com.bb.resources;

import br.com.bb.entitys.user.User;
import br.com.bb.services.user.UserService;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTest {

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void get() throws IOException {
        userService.save(new User("erik paixao", "123456"));
        given()
                .when().get("/user")
                .then()
                .statusCode(200)
                .body("$.size()", is(1),
                        "[0].username", is("erik paixao"),
                        "[0].password", is("123456")
                );
    }

    @Test
    void save() {
        given()
                .when().post("/user", new User("erik paixao", "123456"))
                .then()
                .statusCode(200)
                .body(
                        "username", is("erik paixao"),
                        "password", is("123456")
                );
    }
}