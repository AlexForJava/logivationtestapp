package service;

import com.gmail.chernii.exceptions.SmallCaseException;
import com.gmail.chernii.model.Case;
import com.gmail.chernii.model.Orderline;
import com.gmail.chernii.model.Product;
import com.gmail.chernii.service.CaseService;
import com.gmail.chernii.service.CaseServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CaseServiceTest {
    private static List<Case> caseList = new ArrayList<>();
    private static List<Orderline> orderlineList = new ArrayList<>();
    private static List<Product> productList = new ArrayList<>();

    private static CaseService caseService;

    @BeforeClass
    public static void setUp() {
        Case firstCase = new Case(1, 30, 30, 30);
        Case secondCase = new Case(1, 30, 35, 30);
        Case thirdCase = new Case(1, 30, 30, 40);

        caseList = Arrays.asList(firstCase, secondCase, thirdCase);

        Orderline firstOrderline = new Orderline(1, 27, 1);
        Orderline secondOrderline = new Orderline(2, 5, 2);
        Orderline thirdOrderline = new Orderline(3, 10, 3);

        orderlineList = Arrays.asList(firstOrderline, secondOrderline, thirdOrderline);

        Product firstProduct = new Product(1, 10, 10, 10);
        Product secondProduct = new Product(2, 15, 15, 10);
        Product thirdProduct = new Product(3, 8, 8, 8);

        productList = Arrays.asList(firstProduct, secondProduct, thirdProduct);

    }

    @Test
    public void testSolution() {
        caseService = new CaseServiceImpl(productList);
        Case solution = caseService.solution(caseList, orderlineList);
        assertEquals(solution.getId(), caseList.get(1).getId());
    }


    @Test(expected = SmallCaseException.class)
    public void testSolutionException() {
        caseService = new CaseServiceImpl(Arrays.asList(new Product(1, 20, 20, 20)));
        caseService.solution(caseList, Arrays.asList(new Orderline(1, 5, 1)));
    }
}
