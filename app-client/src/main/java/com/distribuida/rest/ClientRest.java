package com.distribuida.rest;

import com.distribuida.db.Client;
import com.distribuida.repo.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class ClientRest {

    @Inject
    ClientRepository rep;

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response findAll() {
        List<Client> clients = rep.findAll().list();
        return Response.ok(clients).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        var client = rep.findByIdOptional(id);
        if (client.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(client.get()).build();
    }

    @POST
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response create(Client client) {
        rep.persist(client);
        return Response.status(Response.Status.CREATED.getStatusCode(), "client created").build();
    }

    @PUT
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Client client) {
        Client aux = rep.findById(id);
        aux.setNombre(client.getNombre());
        aux.setApellido(client.getApellido());
        aux.setDireccion(client.getDireccion());
        return Response.ok().build();
    }

    @DELETE
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        rep.deleteById(id);
        return Response.ok( ).build();
    }



}
