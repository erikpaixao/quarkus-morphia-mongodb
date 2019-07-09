package paixao.erik.resources;

import paixao.erik.entitys.GenericEntity;
import paixao.erik.services.GenericService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Log
@Getter
@Setter
@NoArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class GenericResources<E extends GenericEntity, S extends GenericService> {

    private S service;

    public GenericResources(S service) {
        this.service = service;
    }

    @GET
    public Response getAll() {
        try {
            return Response.ok(service.getAll()).build();
        } catch (Exception e) {
            return returnErrorMessage(e);
        }
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam(value = "id") String id) {
        try {
            return Response.ok(service.getOne(id)).build();
        } catch (Exception e) {
            return returnErrorMessage(e);
        }
    }

    @POST
    @PUT
    public Response save(@Valid E object) {
        try {
            return Response.ok(service.save(object)).build();
        } catch (Exception e) {
            return returnErrorMessage(e);
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam(value = "id") String id) {
        try {
            return Response.ok(service.delete(id)).build();
        } catch (Exception e) {
            return returnErrorMessage(e);
        }
    }

    private Response returnErrorMessage(Throwable throwable) {
        log.log(Level.WARNING, throwable.getMessage(), throwable.getCause());
        return Response.serverError()
                .entity(throwable.getMessage())
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
}
