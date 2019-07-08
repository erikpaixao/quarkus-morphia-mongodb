package br.com.bb.resources;

import br.com.bb.entitys.user.User;

import br.com.bb.services.user.UserService;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("/user")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public Response getAll() {
        return Response.ok(userService.getUsers()).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam(value = "id") String id) {
        return Response.ok(userService.getOne(id)).build();
    }

    @POST
    @PUT
    public Response save(@Valid User user) {
        userService.save(user);
        return Response
                .ok(user)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam(value = "id") String id) {
        return Response
                .ok(userService.delete(id))
                .build();
    }

}
