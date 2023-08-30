package com.distribuida.repo;

import com.distribuida.db.Store;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class StoreRepository implements PanacheRepositoryBase<Store, Integer> {

    @PersistenceContext
    private EntityManager em;

    public List<Store> listaProductosByOrden(Integer orden_id) {
        return em.createQuery("SELECT s FROM Store s WHERE s.orden_id = :orden_id", Store.class)
                .setParameter("orden_id", orden_id)
                .getResultList();
    }

    public List<Store> listaOrdensByProduct(Integer product_id) {
        return em.createQuery("SELECT s FROM Store s WHERE s.product_id = :product_id", Store.class)
                .setParameter("orden_id", product_id)
                .getResultList();
    }

}
