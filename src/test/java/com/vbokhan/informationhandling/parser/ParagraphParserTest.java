package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.NoFileException;
import com.vbokhan.informationhandling.exception.WrongDataException;
import com.vbokhan.informationhandling.interpreter.Client;
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
        Client.setI(5);
        Client.setJ(10);
    }

    @Test
    public void parseTest() throws WrongDataException{
        Component textComponents = paragraphParser.parse(dataFromFile);
        assertEquals(expectedNumberOfParagraphs, textComponents.getChild().size());
    }
    @Test
    public void parseT() throws WrongDataException {
        Component textComponent = paragraphParser.parse(dataFromFile);
        String expected = "It has survived not only five centuries, but also the leap into 17.0 electronic typesetting, remaining 8.0 essentially -3.0 unchanged. It was popularised in the -115.0 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using 408.0 Ipsum is that is has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n" +
                "It is a -4200.0 established fact that a reader will be of a page when looking at it's layout.\n" +
                "Bye.\n";
        String actual = textComponent.toString();
        assertEquals(expected, actual);
    }

    @Test(expected = WrongDataException.class)
    public void parseNullTest() throws WrongDataException{
        paragraphParser.parse(null);
    }
}