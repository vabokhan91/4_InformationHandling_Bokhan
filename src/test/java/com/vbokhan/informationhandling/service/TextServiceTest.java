package com.vbokhan.informationhandling.service;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.NoFileException;
import com.vbokhan.informationhandling.exception.WrongDataException;
import com.vbokhan.informationhandling.interpreter.Client;
import com.vbokhan.informationhandling.parser.ParagraphParser;
import com.vbokhan.informationhandling.reader.TextReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 09.07.2017.
 */
public class TextServiceTest {


    private static TextReader textReader;
    private static ParagraphParser paragraphParser;
    private final static String FILE_NAME = "data/data.txt";
    private static String dataFromFile;
    private static Component text;

    @Before
    public void init() throws NoFileException, WrongDataException {
        paragraphParser = new ParagraphParser();
        textReader = new TextReader();
        dataFromFile = textReader.readDataFromFile(FILE_NAME);

        text = paragraphParser.parse(dataFromFile);
        Client.setI(5);
        Client.setJ(10);
    }

    @Test
    public void sortSentencesByNumberOfLexemesASC() throws Exception {
        String expected = "Bye. ";
       List<Component> components = TextService.sortSentencesByNumberOfLexemesASC(text);
       assertEquals(expected, components.get(0).toString());
    }

    @Test
    public void changeFirstAndLastLexemeInSentence() throws Exception {
        String expected = "unchanged";
        List<Component> components = TextService.changeFirstAndLastLexemeInSentence(text);
        assertEquals(expected, components.get(0).getChild().get(0).toString());
    }

    @Test
    public void removeLexemes() throws Exception {
        int expected = 22;
        List<Component> components = TextService.removeLexemes(text, 2, 'I');
        assertEquals(expected, components.get(0).size());
    }


}