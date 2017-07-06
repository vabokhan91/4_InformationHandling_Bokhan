package com.vbokhan.informationhandling.interpreter;

import com.vbokhan.informationhandling.exception.WrongDataException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 06.07.2017.
 */
public class ClientTest {

    private static Client client;
    private static String testExpression = "(5-(--j)+4)";
    private int expected = 17;

    @BeforeClass
    public static void init() throws WrongDataException {
        client.setI(5);
        client.setJ(10);
        client = new Client(testExpression);

    }

    @Test
    public void calculateTest() throws Exception {
        int result = client.calculate().intValue();
        assertEquals(expected, result);
    }

}