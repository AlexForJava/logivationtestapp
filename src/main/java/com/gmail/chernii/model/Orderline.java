package com.gmail.chernii.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Orderline implements Cloneable{
    private int id;
    private int numberOfProducts;
    private int productId;

    @Override
    public Orderline clone() {
        return new Orderline(id, numberOfProducts, productId);
    }
}
