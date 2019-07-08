package br.com.bb.resources;

import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Log
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenericResources {

    protected Response returnErrorMessage(Throwable throwable) {
        log.log(Level.WARNING, throwable.getMessage(), throwable.getCause());
        return Response.serverError()
                .entity(throwable.getMessage())
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
}
