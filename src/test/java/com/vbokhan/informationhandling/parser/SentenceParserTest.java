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
public class SentenceParserTest {
    private static TextReader textReader;
    private static ParagraphParser paragraphParser;
    private static SentenceParser sentenceParser;
    private final static String FILE_NAME = "data/data.txt";
    private static TextComponent text;
    private int expectedNumberOfSentences = 2;
    static String dataFromFile;

    @BeforeClass
    public static void init() throws NoFileException {
        textReader = new TextReader();
        paragraphParser = new ParagraphParser();
        dataFromFile = textReader.readDataFromFile(FILE_NAME);
    }

    @Test
    public void parseSentencesTest() throws WrongDataException {
        Component textComponents = paragraphParser.parse(dataFromFile);
        assertEquals(expectedNumberOfSentences, textComponents.getChild().get(0).getChild().size());

    }

    @Test(expected = WrongDataException.class)
    public void parseNullTest() throws WrongDataException{
        paragraphParser.parse(null);
    }

}