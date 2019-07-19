package com.gmail.chernii.utils;

import com.gmail.chernii.model.Base;
import com.gmail.chernii.model.Case;
import com.gmail.chernii.model.Product;
import org.apache.log4j.Logger;


public class CaseUtil {
    private static final Logger LOG = Logger.getLogger(CaseUtil.class);

    private CaseUtil(){}

    public static double calculateOccupiedSpaceRatio(Case aCase, Product product, int numberOfProducts) {
        double ratio = occupiedSpaceRatio(aCase, product, numberOfProducts);
        LOG.info("id" + aCase.getId() + "  = "+ ratio);
        aCase.setOccupiedSpaceRatio(ratio);
        return ratio;
    }

    public static int calculateMaxProductInCase(Case aCase, Product product){
        int widthCount = aCase.getSizeX() / product.getSizeX();
        int heightCount = aCase.getSizeY() / product.getSizeY();
        int depthCount = aCase.getSizeZ() / product.getSizeZ();

        return widthCount * heightCount * depthCount;
    }

    private static double occupiedSpaceRatio(Case aCase, Product product, int numberOfProducts) {
        return ((double) (numberOfProducts * calculateVolume(product))) / calculateVolume(aCase);
    }

    public static int calculateVolume(Base base) {
        return (base.getSizeX() * base.getSizeY() * base.getSizeZ());
    }
}
