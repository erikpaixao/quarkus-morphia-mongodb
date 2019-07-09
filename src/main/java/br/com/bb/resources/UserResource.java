package br.com.bb.resources;

import br.com.bb.entitys.user.User;

import br.com.bb.services.GenericService;
import br.com.bb.services.user.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/user")
@ApplicationScoped
public class UserResource extends GenericResources<User, UserService> {

    @Inject
    public UserResource(UserService service) {
        super(service);
    }
}
