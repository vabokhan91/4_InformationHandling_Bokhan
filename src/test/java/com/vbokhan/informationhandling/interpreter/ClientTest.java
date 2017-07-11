package com.vbokhan.informationhandling.interpreter;

import com.vbokhan.informationhandling.exception.WrongDataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;


@RunWith(Parameterized.class)
public class ClientTest {
    private final String expression;
    private final double expected;

    public ClientTest(String expression, double expectedValue) {
        this.expression = expression;
        expected = expectedValue;
    }

    @Parameterized.Parameters
    public static List<Object[]> calculatingData() throws WrongDataException {

        return Arrays.asList(new Object[][]{
                {"13+(i--)", 17},
                {"3+5", 8},
                {"6+9*(3-4)", -3.0},
                {"5*(1*2*(3*(4*(5-(--j)+4)-3)-2)-1)", -115},
                {"(71-(2*(i--)*(3*(2-1/2*2)-2)-10/2))*(++i)", 408},
                {"(-5+1/2*(2+5*2-(--j)))*1200", -4200}
        });
    }

    @Test
    public void calculateTest() throws Exception {
        Client.setI(5);
        Client.setJ(10);
        Client client = new Client(expression);
        Double result = (Double) client.calculate();
        assertEquals(expected, result);
    }

}