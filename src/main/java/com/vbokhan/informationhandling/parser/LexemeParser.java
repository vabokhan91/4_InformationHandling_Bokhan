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
            throw new WrongDataException("Wrong data for parsing. Nothing to parse.");
        }
        TextComponent sentence = new TextComponent(TextType.SENTENCE);
        Pattern patternForLexeme = Pattern.compile(REGEX_FOR_LEXEME);
        Pattern arithmeticPattern = Pattern.compile(REGEX_FOR_ARITHMETIC_EXPRESSION);
        Pattern patternForPunctuation = Pattern.compile(REGEX_FOR_PUNCTUATION);
        Matcher matcherForLexeme = patternForLexeme.matcher(text);
        while (matcherForLexeme.find()) {
            String parsingLexeme = matcherForLexeme.group();
            Matcher matcherForPunctuation = patternForPunctuation.matcher(parsingLexeme);
            Matcher arithmeticMatcher = arithmeticPattern.matcher(parsingLexeme);
            if (arithmeticMatcher.find()) {
                parsingLexeme = evaluateExpression(arithmeticMatcher);
            }
            if (matcherForPunctuation.find()) {
                parseForLexemeAndPunctuation(sentence, parsingLexeme, matcherForPunctuation);
            } else {
                Component lexeme = new Lexeme(parsingLexeme);
                sentence.addComponent(lexeme);
            }
        }
        return sentence;
    }

    private String evaluateExpression(Matcher arithmeticMatcher) throws WrongDataException {
        String parsingLexeme;
        String expression = arithmeticMatcher.group();
        Client client = new Client(expression);
        double result = (Double) client.calculate();
        String s = String.valueOf(result);
        parsingLexeme = expression.replaceAll(REGEX_FOR_ARITHMETIC_EXPRESSION, s);
        return parsingLexeme;
    }

    private void parseForLexemeAndPunctuation(TextComponent sentence, String parsingLexeme, Matcher matcherForPunctuation) {
        String punctuation = matcherForPunctuation.group();
        Component punctuationComponent = new Punctuation(punctuation);
        String replacedLex = parsingLexeme.replace(punctuation, "");
        Component lexeme = new Lexeme(replacedLex);
        sentence.addComponent(lexeme);
        sentence.addComponent(punctuationComponent);
    }
}
