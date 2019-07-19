package com.gmail.chernii.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Base implements Cloneable {
    private int id;
    private int sizeX;
    private int sizeY;
    private int sizeZ;

    @Override
    public Base clone() {
        return new Base(id, sizeX, sizeY, sizeZ);
    }
}
