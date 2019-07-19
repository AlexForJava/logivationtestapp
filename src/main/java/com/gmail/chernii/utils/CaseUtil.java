package com.gmail.chernii.utils;

import com.gmail.chernii.model.Base;
import com.gmail.chernii.model.Case;
import com.gmail.chernii.model.Product;


public class CaseUtil {

    private CaseUtil(){}

    public static double calculateOccupiedSpaceRatio(Case aCase, Product product, int numberOfProducts) {
        double ratio = occupiedSpaceRatio(aCase, product, numberOfProducts);
        aCase.setOccupiedSpaceRatio(ratio);
        return ratio;
    }

    public static int calculateMaxProductInCase(Case aCase, Product product){
        int widthCount = aCase.getSizeX() / product.getSizeX();
        int heightCount = aCase.getSizeY() / product.getSizeY();
        int depthCount = aCase.getSizeZ() / product.getSizeZ();

        return widthCount * heightCount * depthCount;
    }

    public static int calculateVolume(Base base) {
        return (base.getSizeX() * base.getSizeY() * base.getSizeZ());
    }

    private static double occupiedSpaceRatio(Case aCase, Product product, int numberOfProducts) {
        return ((double) (numberOfProducts * calculateVolume(product))) / calculateVolume(aCase);
    }
}
