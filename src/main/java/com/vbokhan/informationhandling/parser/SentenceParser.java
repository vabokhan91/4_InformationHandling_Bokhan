package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.WrongDataException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentenceParser extends AbstractParser{
    private final static String REGEX_FOR_SENTENCE = "(\\S.+?[.!?])(?=\\s+|$)";

    public SentenceParser() {
        nextParser = new LexemeParser();
    }

    @Override
    public Component parse(String text) throws WrongDataException {
        if (text == null || text.isEmpty()) {
            throw new WrongDataException("Wrong data");
        }
        TextComponent paragraphs = new TextComponent(TextType.PARAGRAPH);

        Pattern sentencePattern = Pattern.compile(REGEX_FOR_SENTENCE);
        Matcher sentenceMatcher = sentencePattern.matcher(text);
        while (sentenceMatcher.find()) {
            String sentence = sentenceMatcher.group();
            paragraphs.addComponent(nextParser.parse(sentence));
        }
        return paragraphs;
    }
}
