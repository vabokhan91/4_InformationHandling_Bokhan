package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.NoFileException;
import com.vbokhan.informationhandling.exception.WrongDataException;
import com.vbokhan.informationhandling.reader.TextReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 01.07.2017.
 */
public class ParagraphParserTest {
    private static TextReader textReader;
    private static ParagraphParser paragraphParser;
    private final static String FILE_NAME = "data/data.txt";
    private static TextComponent text;
    private int expectedNumberOfParagraphs = 4;
    private static String dataFromFile;

    @BeforeClass
    public static void init() throws NoFileException{
        textReader = new TextReader();
        paragraphParser = new ParagraphParser();
        dataFromFile = textReader.readDataFromFile(FILE_NAME);
        text = new TextComponent(TextType.TEXT);
    }

    @Test
    public void parseTest() throws WrongDataException{
        Component textComponents = paragraphParser.parse(dataFromFile);
        assertEquals(expectedNumberOfParagraphs, textComponents.getChild().size());

    }

    @Test(expected = WrongDataException.class)
    public void parseNullTest() throws WrongDataException{
        paragraphParser.parse(null);
    }
}