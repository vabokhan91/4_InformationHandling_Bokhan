package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.Lexeme;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.WrongDataException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LexemeParser extends AbstractParser {
    private final static String REGEX_FOR_LEXEME = "(\\w|\\p{Punct})+[.\\p{Blank}]";

    @Override
    public TextComponent parse(String text) throws WrongDataException {
        if (text == null || text.isEmpty()) {
            throw new WrongDataException("Wrong data");
        }
        TextComponent sentence = new TextComponent(TextType.SENTENCE);
        Pattern pattern = Pattern.compile(REGEX_FOR_LEXEME);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            Component lexeme = new Lexeme(matcher.group().trim());
            sentence.addComponent(lexeme);
        }
        return sentence;
    }
}
