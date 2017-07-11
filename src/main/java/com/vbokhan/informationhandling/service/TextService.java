package com.vbokhan.informationhandling.service;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextType;
import com.vbokhan.informationhandling.exception.WrongDataException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TextService {
    public static List<Component> sortSentencesByNumberOfLexemesASC(Component component) throws WrongDataException {
        isAppropriateTypeOfComponent(component);
        List<Component> foundSentences = new ArrayList<>();
        findSentence(component, foundSentences);
        foundSentences.sort(Comparator.comparing(sentence -> sentence.getChild().size()));
        return foundSentences;
    }

    public static List<Component> changeFirstAndLastLexemeInSentence(Component component) throws WrongDataException {
        isAppropriateTypeOfComponent(component);
        List<Component> foundSentence = new ArrayList<>();
        findSentence(component, foundSentence);
        for (Component c : foundSentence) {
            List<Component> listOfLexemes = c.getChild();
            if (listOfLexemes.get(0).getTextType() == TextType.LEXEME) {
                if (listOfLexemes.get(listOfLexemes.size() - 1).getTextType() == TextType.LEXEME) {
                    Collections.swap(listOfLexemes, 0, listOfLexemes.size() - 1);
                } else {
                    Collections.swap(listOfLexemes, 0, listOfLexemes.size() - 2);
                }
            }
        }
        return foundSentence;
    }

    public static List<Component> removeLexemesFromText(Component component, int length, char firstLetter) throws WrongDataException {
        isAppropriateTypeOfComponent(component);
        List<Component> foundComponents = new ArrayList<>();
        findSentence(component, foundComponents);
        for (Component sentence : foundComponents) {
            List<Component> listOfLexemes = sentence.getChild();
            List<Component> changedList = new ArrayList<>();
            findLexemeAndDeleteFromComponent(length, firstLetter, listOfLexemes, changedList);
            sentence.setChildComponents(changedList);
        }
        return foundComponents;
    }

    private static void findLexemeAndDeleteFromComponent(int length, char firstLetter, List<Component> listOfLexemes, List<Component> changedList) {
        for (Component lexeme : listOfLexemes) {
            if (lexeme.getTextType() == TextType.LEXEME) {
                if (lexeme.size() != length) {
                    changedList.add(lexeme);
                } else if (lexeme.toString().charAt(0) != firstLetter) {
                    changedList.add(lexeme);
                }
            } else {
                changedList.add(lexeme);
            }
        }
    }

    private static void findSentence(Component component, List<Component> foundSentences) {
        for (Component components : component.getChild()) {
            if (components.getTextType() == TextType.SENTENCE) {
                foundSentences.add(components);
            } else {
                findSentence(components, foundSentences);
            }
        }
    }

    private static void isAppropriateTypeOfComponent(Component component) throws WrongDataException {
        if (component.getTextType() == TextType.LEXEME && component.getTextType() == TextType.PUNCTUATION) {
            throw new WrongDataException("Can not find sentence. Wrong type of components");
        }
    }
}
