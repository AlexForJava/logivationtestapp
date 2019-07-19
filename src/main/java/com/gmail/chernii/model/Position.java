package com.gmail.chernii.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Position implements Cloneable {
    private int x;
    private int y;
    private int z;

    @Override
    public Position clone() {
        return new Position(this.x, this.y, this.z);
    }
}
