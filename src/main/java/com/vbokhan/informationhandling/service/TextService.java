package com.vbokhan.informationhandling.service;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vbokh on 09.07.2017.
 */
public class TextService {
    public static List<Component> sortSentencesByNumberOfLexemesASC(Component component) {
        List<Component> foundSentences = new ArrayList<>();
        findSentence(component, foundSentences);
        foundSentences.sort(Comparator.comparing(sentence->sentence.getChild().size()));
        return foundSentences;
    }

    public static List<Component> changeFirstAndLastLexemeInSentence(Component component) {
        List<Component> foundSentence = new ArrayList<>();
        findSentence(component, foundSentence);
        for (Component c : foundSentence) {
            List<Component> listOfLexemes = c.getChild();
            if (listOfLexemes.get(0).getTextType() == TextType.LEXEME) {
                if (listOfLexemes.get(listOfLexemes.size()-1).getTextType() == TextType.LEXEME) {
                    Collections.swap(listOfLexemes, 0, listOfLexemes.size()-1);
                }else {
                    Collections.swap(listOfLexemes, 0, listOfLexemes.size()-2);
                }
            }
        }
        return foundSentence;
    }

    public static List<Component> removeLexemes(Component component, int length, char firstLetter) {
        List<Component> foundComponents = new ArrayList<>();
        findSentence(component, foundComponents);
        for (Component sentence : foundComponents) {
            List<Component> listOfLexemes = sentence.getChild();
            List<Component> changedList = new ArrayList<>();
            for (Component lexeme : listOfLexemes) {
                if (lexeme.getTextType() == TextType.LEXEME) {
                    if(lexeme.size() != length){
                        changedList.add(lexeme);
                    }else if(lexeme.toString().charAt(0) != firstLetter){
                        changedList.add(lexeme);
                    }
                }else {
                    changedList.add(lexeme);
                }
            }
            sentence.setChildComponents(changedList);

        }
        return foundComponents;
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
}
