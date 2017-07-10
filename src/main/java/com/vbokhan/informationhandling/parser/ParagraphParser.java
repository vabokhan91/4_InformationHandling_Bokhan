package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.WrongDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParagraphParser extends AbstractParser {
    private final static String REGEX_FOR_PARAGRAPH = "(\r\n|^).*?(?=\r\n|$)";


    public ParagraphParser() {
        nextParser = new SentenceParser();
    }

    @Override
    public Component parse(String text) throws WrongDataException{
        if (text == null || text.isEmpty()) {
            throw new WrongDataException("Wrong data");
        }
        TextComponent textComponent = new TextComponent(TextType.TEXT);

        Pattern paragraphPattern = Pattern.compile(REGEX_FOR_PARAGRAPH);
        Matcher paragraphMatcher = paragraphPattern.matcher(text);


        while (paragraphMatcher.find()) {
            String paragraph = paragraphMatcher.group();
            textComponent.addComponent(nextParser.parse(paragraph));
        }
        return textComponent;
    }
}
