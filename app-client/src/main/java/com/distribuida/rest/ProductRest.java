package com.distribuida.rest;

import com.distribuida.db.Product;
import com.distribuida.repo.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class ProductRest {

    @Inject
    ProductRepository rep;

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response findAll() {
        List<Product> products = rep.findAll().list();
        return Response.ok(products).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        var product = rep.findByIdOptional(id);
        if (product.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(product.get()).build();
    }

    @POST
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response create(Product product) {
        rep.persist(product);
        return Response.status(Response.Status.CREATED.getStatusCode(), "product created").build();
    }

    @PUT
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Product product) {
        Product aux = rep.findById(id);
        aux.setNombre(product.getNombre());
        aux.setPrecio(product.getPrecio());
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
