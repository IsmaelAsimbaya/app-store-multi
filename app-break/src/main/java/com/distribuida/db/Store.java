package com.distribuida.db;

import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orden_id")
    private Integer orden_id;

    @Column(name = "product_id")
    private Integer product_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(Integer orden_id) {
        this.orden_id = orden_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", orden_id=" + orden_id +
                ", product_id=" + product_id +
                '}';
    }
}
