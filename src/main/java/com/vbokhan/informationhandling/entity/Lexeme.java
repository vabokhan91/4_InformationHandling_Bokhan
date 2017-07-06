package com.vbokhan.informationhandling.entity;

import java.util.List;

/**
 * Created by vbokh on 06.07.2017.
 */
public class Lexeme implements Component {
    private TextType textType = TextType.LEXEME;
    private String content;

    public Lexeme(String content) {
        this.content = content;
    }

    @Override
    public void addComponent(Component component) {
        throw new UnsupportedOperationException("Can not add component to the lexeme");
    }

    @Override
    public void removeComponent(Component component) {
        throw new UnsupportedOperationException("Can not remove component from the lexeme");
    }

    @Override
    public List<Component> getChild() {
        throw new UnsupportedOperationException("Can not get child component from the lexeme");
    }

    @Override
    public TextType getTextType() {
        return textType;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        return content;
    }
}
