package com.gmail.chernii.service;

import com.gmail.chernii.exceptions.ProductNotFoundException;
import com.gmail.chernii.exceptions.SmallCaseException;
import com.gmail.chernii.model.Case;
import com.gmail.chernii.model.Orderline;
import com.gmail.chernii.model.Position;
import com.gmail.chernii.model.Product;
import com.gmail.chernii.utils.CaseUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CaseServiceImpl implements CaseService{
    private static final Logger LOG = Logger.getLogger(CaseServiceImpl.class);
    private List<Product> products = new ArrayList<>();

    public CaseServiceImpl(List<Product> products){
        this.products = products;
    }

    public Case solution(List<Case> cases, List<Orderline> orderlines) {
        return compare(orderlines.stream()
                .map(orderline -> paking(cases, orderline))
                .collect(Collectors.toList()));
    }

    private Case paking(List<Case> cases, Orderline orderline) {
        return compare(cases.stream()
                .map(aCase -> pakingOrderline(aCase.clone(), orderline))
                .collect(Collectors.toList()));
    }

    private Case compare(List<Case> cases) {
        return Collections.max(cases, Comparator.comparing(Case::getOccupiedSpaceRatio));
    }

    private Case pakingOrderline(Case aCase, Orderline orderline) {
        aCase.setOrderline(orderline);
        Product product = products.stream()
                .filter(e -> e.getId() == orderline.getProductId())
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);

        return pakingProducts(aCase, product, orderline.getNumberOfProducts());
    }

    private Case pakingProducts(Case aCase, Product product, int numberOfProducts) {
        if (CaseUtil.calculateOccupiedSpaceRatio(aCase, product, numberOfProducts) > 1) {
            throw new SmallCaseException();
        }

        int maxProductsInCase = CaseUtil.calculateMaxProductInCase(aCase, product);
        if (maxProductsInCase < numberOfProducts) {
            throw new SmallCaseException("Case is too small for this order, maxProducts = " + maxProductsInCase + ", numberOfProducts = " + numberOfProducts);
        }
        for (int i = 0; i < numberOfProducts; i++) {
            if (hasXSpace(aCase, product) && hasYSpace(aCase, product) && hasZSpace(aCase, product) && aCase.getPositions().isEmpty()) {
                aCase.getPositions().add(new Position(product.getSizeX(), 0, 0));
            }
            if (hasXSpace(aCase, product) && hasYSpace(aCase, product) && hasZSpace(aCase, product)) {
                Position lastPosition = getLastPostion(aCase);
                Position position = new Position(lastPosition.getX() + product.getSizeX(), lastPosition.getY(), lastPosition.getZ());
                aCase.getPositions().add(position);
                continue;
            }
            if (hasYSpace(aCase, product) && hasZSpace(aCase, product)) {
                Position lastPosition = getLastPostion(aCase);
                Position position = new Position(0, lastPosition.getY() + product.getSizeY(), lastPosition.getZ());
                aCase.getPositions().add(position);
                continue;
            }
            if (hasZSpace(aCase, product)) {
                Position lastPosition = getLastPostion(aCase);
                Position position = new Position(0, 0, lastPosition.getZ() + product.getSizeZ());
                aCase.getPositions().add(position);
            }
        }
        return aCase;
    }

    private boolean hasXSpace(Case aCase, Product product) {
        List<Position> positions = aCase.getPositions();
        int xValue = 0;
        if (!positions.isEmpty()) {
            Position lastPosition = positions.get(positions.size() - 1);
            xValue = lastPosition.getX();
        }
        return (xValue + product.getSizeX()) <= aCase.getSizeX();
    }

    private boolean hasYSpace(Case aCase, Product product) {
        List<Position> positions = aCase.getPositions();
        int yValue = 0;
        if (!positions.isEmpty()) {
            Position lastPosition = positions.get(positions.size() - 1);
            yValue = lastPosition.getY();
        }
        return (yValue + product.getSizeY()) <= aCase.getSizeY();
    }

    private boolean hasZSpace(Case aCase, Product product) {
        List<Position> positions = aCase.getPositions();
        int zValue = 0;
        if (!positions.isEmpty()) {
            Position lastPosition = positions.get(positions.size() - 1);
            zValue = lastPosition.getZ();
        }
        return (zValue + product.getSizeZ()) <= aCase.getSizeZ();
    }

    private Position getLastPostion(Case aCase) {
        List<Position> positions = aCase.getPositions();
        return positions.get(positions.size() - 1);
    }
}
