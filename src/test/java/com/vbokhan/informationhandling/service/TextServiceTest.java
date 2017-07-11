package com.vbokhan.informationhandling.service;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.exception.NoFileException;
import com.vbokhan.informationhandling.exception.WrongDataException;
import com.vbokhan.informationhandling.interpreter.Client;
import com.vbokhan.informationhandling.parser.ParagraphParser;
import com.vbokhan.informationhandling.reader.TextReader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class TextServiceTest {
    private final static String FILE_NAME = "data/data.txt";
    private TextReader textReader;
    private ParagraphParser paragraphParser;
    private String dataFromFile;
    private Component textComponent;

    @Before
    public void init() throws NoFileException, WrongDataException {
        paragraphParser = new ParagraphParser();
        textReader = new TextReader();
        dataFromFile = textReader.readDataFromFile(FILE_NAME);
        Client.setI(5);
        Client.setJ(10);
        textComponent = paragraphParser.parse(dataFromFile);
    }

    @Test
    public void sortSentencesByNumberOfLexemesASCTest() throws Exception {
        String expected = "Bye. ";
        List<Component> components = TextService.sortSentencesByNumberOfLexemesASC(textComponent);
        String actual = components.get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void changeFirstAndLastLexemeInSentenceTest() throws Exception {
        String expected = "unchanged";
        List<Component> components = TextService.changeFirstAndLastLexemeInSentence(textComponent);
        String actual = components.get(0).getChild().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void removeLexemesTest() throws Exception {
        int expected = 22;
        List<Component> components = TextService.removeLexemesFromText(textComponent, 2, 'I');
        int actual = components.get(0).size();
        assertEquals(expected, actual);
    }


}