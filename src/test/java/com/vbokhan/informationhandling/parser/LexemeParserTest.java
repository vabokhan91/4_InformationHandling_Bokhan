package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.NoFileException;
import com.vbokhan.informationhandling.exception.WrongDataException;
import com.vbokhan.informationhandling.reader.TextReader;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 05.07.2017.
 */
public class LexemeParserTest {
    private static TextReader textReader;
    private static ParagraphParser paragraphParser;
    private final static String FILE_NAME = "data/data.txt";
    private int expectedNumberOfLexemes = 23;
    private static String dataFromFile;

    @BeforeClass
    public static void init() throws NoFileException {
        textReader = new TextReader();
        paragraphParser = new ParagraphParser();
        dataFromFile = textReader.readDataFromFile(FILE_NAME);
    }

    @Test
    public void parseLexemesTest() throws WrongDataException {
        Component textComponents = paragraphParser.parse(dataFromFile);
        int actual = textComponents.getChild().get(0).getChild().get(0).getChild().size();
        assertEquals(expectedNumberOfLexemes, actual);
    }

    @Test(expected = WrongDataException.class)
    public void parseNullTest() throws WrongDataException{
        paragraphParser.parse(null);
    }
}