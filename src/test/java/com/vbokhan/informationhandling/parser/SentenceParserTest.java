package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.NoFileException;
import com.vbokhan.informationhandling.exception.WrongDataException;
import com.vbokhan.informationhandling.reader.TextReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class SentenceParserTest {
    private static final String EMPTY_STRING = "";
    private String textForParsing;
    private SentenceParser sentenceParser;
    private int expectedNumberOfSentences = 2;

    @Before
    public void init() throws NoFileException {
        sentenceParser = new SentenceParser();
        textForParsing = "It has survived not only five centuries, but also the leap into 13+(i--)" +
                " electronic typesetting, remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised " +
                "in the 5*(1*2*(3*(4*(5-(--j)+4)-3)-2)-1) with the release of Letraset sheets containing Lorem " +
                "Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                "versions of Lorem Ipsum.";
    }

    @Test
    public void parseSentencesTest() throws WrongDataException {
        Component textComponents = sentenceParser.parse(textForParsing);
        assertEquals(expectedNumberOfSentences, textComponents.size());
    }

    @Test(expected = WrongDataException.class)
    public void parseNullTest() throws WrongDataException{
        sentenceParser.parse(null);
    }

    @Test(expected = WrongDataException.class)
    public void parseEmptyStringTest() throws WrongDataException{
        sentenceParser.parse(EMPTY_STRING);
    }

}