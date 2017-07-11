package com.vbokhan.informationhandling.suite;

import com.vbokhan.informationhandling.interpreter.ClientTest;
import com.vbokhan.informationhandling.interpreter.PolishNotationConverterTest;
import com.vbokhan.informationhandling.parser.LexemeParserTest;
import com.vbokhan.informationhandling.parser.ParagraphParserTest;
import com.vbokhan.informationhandling.parser.SentenceParserTest;
import com.vbokhan.informationhandling.reader.TextReaderTest;
import com.vbokhan.informationhandling.service.TextServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({ClientTest.class,
        PolishNotationConverterTest.class,
        LexemeParserTest.class,
        ParagraphParserTest.class,
        SentenceParserTest.class,
        TextReaderTest.class,
        TextServiceTest.class})
@RunWith(Suite.class)
public class SuiteTest {
}
