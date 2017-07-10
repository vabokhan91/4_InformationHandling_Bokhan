package com.vbokhan.informationhandling.interpreter;

import com.vbokhan.informationhandling.exception.WrongDataException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;


/**
 * Created by vbokh on 06.07.2017.
 */
@RunWith(Parameterized.class)
public class ClientTest {

    private final Client client;
    private final double expected;

    public ClientTest(Client testClient, double expectedValue) {
        client = testClient;
        expected = expectedValue;
    }
    @Parameterized.Parameters
    public static List<Object[]> calculatingData() throws WrongDataException{
        Client.setI(5);
        Client.setJ(10);
        return Arrays.asList(new Object[][]{
                {new Client("13+(i--)"), 17},
                {new Client("3+5"), 8},
                {new Client("6+9*(3-4)"), -3.0},
                {new Client("5*(1*2*(3*(4*(5-(--j)+4)-3)-2)-1)"), -115},
                {new Client("(71-(2*(i--)*(3*(2-1/2*2)-2)-10/2))*(++i)"), 408},
                {new Client("(-5+1/2*(2+5*2-(--j)))*1200"), -4200}
        });
    }

    @Test
    public void calculateTest() throws Exception {
        Client.setI(5.0);
        Client.setJ(10.0);
        double result = client.calculate().intValue();
        assertEquals(expected, result);
    }

}