package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.*;
import com.vbokhan.informationhandling.exception.WrongDataException;
import com.vbokhan.informationhandling.interpreter.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LexemeParser extends AbstractParser {
    private static final String REGEX_FOR_LEXEME = "(\\w|\\p{Punct})+";
    private static final String REGEX_FOR_ARITHMETIC_EXPRESSION = "(\\d|\\+|-|\\*|/|\\(|\\)|i|j)+(?!\\w)";
    private static final String REGEX_FOR_PUNCTUATION = "[,.!?]";

    @Override
    public TextComponent parse(String text) throws WrongDataException {
        if (text == null || text.isEmpty()) {
            throw new WrongDataException("Wrong data");
        }
        TextComponent sentence = new TextComponent(TextType.SENTENCE);
        Pattern pattern = Pattern.compile(REGEX_FOR_LEXEME);
        Pattern arithmeticPattern = Pattern.compile(REGEX_FOR_ARITHMETIC_EXPRESSION);
        Pattern patternForPunctuation = Pattern.compile(REGEX_FOR_PUNCTUATION);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String lex = matcher.group();
            Matcher matcherForPunctuation = patternForPunctuation.matcher(lex);

            Matcher arithmeticMatcher = arithmeticPattern.matcher(lex);
            while (arithmeticMatcher.find()) {
                String expression = arithmeticMatcher.group();
                Client client = new Client(expression);
                double result = (Double) client.calculate();
                String s = String.valueOf(result);
                lex = expression.replaceAll(REGEX_FOR_ARITHMETIC_EXPRESSION, s);
            }
            if (matcherForPunctuation.find()) {
                String exp = matcherForPunctuation.group();
                Component punctuation = new Punctuation(exp);
                String replacedLex = lex.replace(exp, "");
                Component lexeme = new Lexeme(replacedLex);
                sentence.addComponent(lexeme);
                sentence.addComponent(punctuation);

            } else {
                Component lexeme = new Lexeme(lex);
                sentence.addComponent(lexeme);
            }
        }
        return sentence;
    }
}
