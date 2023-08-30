package com.distribuida.clients;

import com.distribuida.dto.ProductDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "clientRestClient")
public interface ProductRestClient {

    @GET
    List<ProductDto> findAll();

    @GET
    @Path("/{id}")
    ProductDto getById(@PathParam("id") Integer id);

}
