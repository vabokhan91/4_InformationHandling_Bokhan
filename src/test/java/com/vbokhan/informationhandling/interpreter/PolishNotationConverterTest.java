package com.vbokhan.informationhandling.interpreter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class PolishNotationConverterTest {
    private String expressionForConverting;
    private String expected;

    public PolishNotationConverterTest(String expressionForConverting, String expected) {
        this.expressionForConverting = expressionForConverting;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> convertingData() {

        return Arrays.asList(new Object[][]{
                {"13+(i--)", "13 i @ +"},
                {"6+9*(3-4)", "6 9 3 4 - * +"},
                {"5*(1*2*(3*(4*(5-(--j)+4)-3)-2)-1)", "5 1 2 * 3 4 5 j @ - 4 + * 3 - * 2 - * 1 - *"},
                {"(71-(2*(i--)*(3*(2-1/2*2)-2)-10/2))*(++i)", "71 2 i @ * 3 2 1 2 / 2 * - * 2 - * 10 2 / - - i # *"},
                {"(-5+1/2*(2+5*2-(--j)))*1200", "5 - 1 2 / 2 5 2 * + j @ - * + 1200 *"}
        });
    }

    @Test
    public void reverseExpressionTest() {
        String actual = PolishNotationConverter.reverseExpression(expressionForConverting);
        assertEquals(actual, expected);
    }

}