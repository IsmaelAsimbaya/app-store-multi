package com.distribuida.clients;

import com.distribuida.dto.ClientDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "clientRestClient")
public interface ClientRestClient {

    @GET
    List<ClientDto> findAll();

    @GET
    @Path("/{id}")
    ClientDto getById(@PathParam("id") Integer id);

}
