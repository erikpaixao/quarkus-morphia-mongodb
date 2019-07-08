package br.com.bb.resources;

import br.com.bb.entitys.user.User;

import br.com.bb.services.user.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/user")
public class UserResource extends GenericResources {

    @Inject
    UserService userService;

    @GET
    public Response getAll() {
        try {
            return Response.ok(userService.getUsers()).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(e.getMessage())
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam(value = "id") String id) {
        try {
            return Response.ok(userService.getOne(id)).build();
        } catch (Exception e) {
            return returnErrorMessage(e);
        }
    }

    @POST
    @PUT
    public Response save(@Valid User user) {
        try {
            return Response.ok(userService.save(user)).build();
        } catch (Exception e) {
            return returnErrorMessage(e);
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam(value = "id") String id) {
        try {
            return Response.ok(userService.delete(id)).build();
        } catch (Exception e) {
            return returnErrorMessage(e);
        }
    }

}
