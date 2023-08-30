package com.distribuida.clients;

import com.distribuida.dto.OrdenDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "clientRestClient")
public interface OrdenRestClient {

    @GET
    List<OrdenDto> findAll();

    @GET
    @Path("/{id}")
    OrdenDto getById(@PathParam("id") Integer id);

    @PUT
    @Path("/{id}/{precio}")
    OrdenDto agregar(@PathParam("id") Integer id, @PathParam("precio") Double precio);

}
