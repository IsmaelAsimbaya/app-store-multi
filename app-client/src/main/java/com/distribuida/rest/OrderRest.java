package com.distribuida.rest;

import com.distribuida.db.Orden;
import com.distribuida.repo.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class OrderRest {

    @Inject
    OrderRepository rep;

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response findAll() {
        List<Orden> orders = rep.findAll().list();
        return Response.ok(orders).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        var order = rep.findByIdOptional(id);
        if (order.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order.get()).build();
    }

    @POST
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response create(Orden order) {
        rep.persist(order);
        return Response.status(Response.Status.CREATED.getStatusCode(), "order created").build();
    }

    @PUT
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}/{precio}")
    public Response agregar(@PathParam("id") Integer id, @PathParam("precio") Double precio) {
        Orden aux = rep.findById(id);
        aux.setTotal(aux.getTotal() + precio);
        return Response.ok(aux).build();
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
