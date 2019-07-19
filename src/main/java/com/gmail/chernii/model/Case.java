package com.gmail.chernii.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class Case extends Base implements Cloneable {
    private Orderline orderline;
    private double occupiedSpaceRatio;
    private List<Position> positions = new ArrayList<>();

    public Case(int id, int sizeX, int sizeY, int sizeZ) {
        super(id, sizeX, sizeY, sizeZ);
    }

    @Override
    public Case clone() {
        Case aCase = new Case(this.getId(), this.getSizeX(), this.getSizeY(), this.getSizeZ());
        if (Objects.nonNull(orderline)) {
            aCase.setOrderline(orderline.clone());
        }
        aCase.setOccupiedSpaceRatio(occupiedSpaceRatio);
        aCase.setPositions(positions.stream().map(Position::clone).collect(Collectors.toList()));
        return aCase;

    }
}
