package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.exception.NoFileException;
import com.vbokhan.informationhandling.exception.WrongDataException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vbokh on 05.07.2017.
 */
public class LexemeParserTest {
    private static final String EMPTY_STRING = "";
    private LexemeParser lexemeParser;
    private int expectedNumberOfLexemes = 23;
    private String textForParsing;

    @Before
    public void init() throws NoFileException {
        lexemeParser = new LexemeParser();
        textForParsing = "It has survived not only five centuries, but also the leap into 13+(i--)" +
                " electronic typesetting, remaining 3+5 essentially 6+9*(3-4) unchanged.";
    }

    @Test
    public void parseLexemesTest() throws WrongDataException {
        Component textComponents = lexemeParser.parse(textForParsing);
        int actual = textComponents.size();
        assertEquals(expectedNumberOfLexemes, actual);
    }

    @Test(expected = WrongDataException.class)
    public void parseNullTest() throws WrongDataException {
        lexemeParser.parse(null);
    }

    @Test(expected = WrongDataException.class)
    public void parseEmptyStringTest() throws WrongDataException {
        lexemeParser.parse(EMPTY_STRING);
    }
}