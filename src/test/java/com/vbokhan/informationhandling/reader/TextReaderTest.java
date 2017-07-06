package com.vbokhan.informationhandling.reader;

import com.vbokhan.informationhandling.exception.NoFileException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vbokh on 30.06.2017.
 */
public class TextReaderTest {
    private static String expectedData;
    private static TextReader textReader = new TextReader();
    private static final String FILE_NAME = "data/data.txt";
    private static final String EMPTY_STRING = "";

    @BeforeClass
    public static void init() {
        textReader = new TextReader();
        expectedData = "    It has survived not only five centuries, but also the leap into 13+(i--) electronic typesetting, remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-(--j)+4)-3)-2)-1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\r\n" +
                "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (71-(2*(i--)*(3*(2-1/2*2)-2)-10/2))*(++i) Ipsum is that is has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\r\n" +
                "    It is a (-5+1/2*(2+5*2-(--j)))*1200 established fact that a reader will be of a page when looking at it's layout.\r\n" +
                "    Bye.";
    }


    @Test
    public void readDataFromFile() throws Exception {
        String actual = textReader.readDataFromFile(FILE_NAME);
        assertEquals(expectedData, actual);
    }

    @Test(expected = NoFileException.class)
    public void nullFileNameTest() throws Exception {
        String actual = textReader.readDataFromFile(null);
    }

    @Test(expected = NoFileException.class)
    public void emptyFileNameTest() throws Exception {
        String actual = textReader.readDataFromFile(EMPTY_STRING);
    }


}