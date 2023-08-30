package com.distribuida.dto;

import java.util.List;

public class OrdenDto {

    private Integer id;
    private Integer cliente_id;
    private Double total;
    private List<ProductDto> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrdenDto{" +
                "id=" + id +
                ", cliente_id=" + cliente_id +
                ", total=" + total +
                ", products=" + products +
                '}';
    }
}
