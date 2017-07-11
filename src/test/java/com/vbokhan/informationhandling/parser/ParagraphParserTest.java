package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.exception.NoFileException;
import com.vbokhan.informationhandling.exception.WrongDataException;
import com.vbokhan.informationhandling.reader.TextReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParagraphParserTest {
    private static final String EMPTY_STRING = "";
    private static final String FILE_NAME = "data/data.txt";
    private TextReader textReader;
    private ParagraphParser paragraphParser;
    private int expectedNumberOfParagraphs = 4;
    private String dataFromFile;

    @Before
    public void init() throws NoFileException {
        textReader = new TextReader();
        paragraphParser = new ParagraphParser();
        dataFromFile = textReader.readDataFromFile(FILE_NAME);
    }

    @Test
    public void parseTest() throws WrongDataException {
        Component textComponents = paragraphParser.parse(dataFromFile);
        assertEquals(expectedNumberOfParagraphs, textComponents.size());
    }

    @Test(expected = WrongDataException.class)
    public void parseNullTest() throws WrongDataException {
        paragraphParser.parse(null);
    }

    @Test(expected = WrongDataException.class)
    public void parseEmptyStringTest() throws WrongDataException {
        paragraphParser.parse(EMPTY_STRING);
    }
}