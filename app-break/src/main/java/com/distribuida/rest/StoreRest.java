package com.distribuida.rest;

import com.distribuida.clients.ClientRestClient;
import com.distribuida.clients.OrdenRestClient;
import com.distribuida.clients.ProductRestClient;
import com.distribuida.db.Store;
import com.distribuida.dto.ClientDto;
import com.distribuida.dto.OrdenDto;
import com.distribuida.dto.ProductDto;
import com.distribuida.repo.StoreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@Path("/stores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class StoreRest {

    @Inject
    StoreRepository rep;

    @Inject
    @RestClient
    ClientRestClient clientC;

    @Inject
    @RestClient
    OrdenRestClient ordenC;

    @Inject
    @RestClient
    ProductRestClient productC;

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response findAll() {
        List<Store> stores = rep.findAll().list();
        return Response.ok(stores).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        var store = rep.findByIdOptional(id);
        if (store.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(store.get()).build();
    }

    @POST
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response create(Store store) {
        rep.persist(store);
        ProductDto product = productC.getById(store.getProduct_id());
        ordenC.agregar(store.getOrden_id(),product.getPrecio());
        return Response.status(Response.Status.CREATED.getStatusCode(), "order created").build();
    }

    @PUT
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Store store) {
        Store aux = rep.findById(id);
        aux.setOrden_id(store.getOrden_id());
        aux.setProduct_id(store.getProduct_id());
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

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/history")
    public Response listaStore() {
        List<OrdenDto> ordens = ordenC.findAll();
        List<ClientDto> clients = clientC.findAll();
        for (ClientDto client : clients) {
            for (OrdenDto orden : ordens) {
                if (orden.getCliente_id() == client.getId()) {
                    List<ProductDto> products = new ArrayList<>();
                    List<Store> stores = rep.listaProductosByOrden(orden.getId());
                    for (Store store : stores){
                        ProductDto paux = productC.getById(store.getProduct_id());
                        if (paux != null) {
                            products.add(paux);
                        }
                    }
                    orden.setProducts(products);
                    List<OrdenDto> aux = client.getOrdens();
                    if (aux == null){
                        aux = new ArrayList<>();
                    }
                    aux.add(orden);
                    client.setOrdens(aux);
                }
            }

        }
        return Response.ok(clients).build();
    }










}
