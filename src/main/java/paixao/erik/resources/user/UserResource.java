package paixao.erik.resources.user;

import paixao.erik.entitys.user.User;

import paixao.erik.services.user.UserService;
import paixao.erik.resources.GenericResources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;


@Path("/user")
@ApplicationScoped
public class UserResource extends GenericResources<User, UserService> {

    @Inject
    public UserResource(UserService service) {
        super(service);
    }
}
