package com.distribuida.repo;

import com.distribuida.db.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheRepositoryBase<Client, Integer> {
}
