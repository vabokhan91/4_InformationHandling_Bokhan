package com.vbokhan.informationhandling.entity;

import java.util.List;


public class Lexeme implements Component {
    private TextType textType = TextType.LEXEME;
    private String content;

    public Lexeme(String content) {
        this.content = content;
    }

    @Override
    public TextType getTextType() {
        return textType;
    }

    @Override
    public int size() {
        return content.length();
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public void addComponent(Component component) {
        throw new UnsupportedOperationException("Can not add component to the lexeme");
    }

    @Override
    public List<Component> getChild() {
        throw new UnsupportedOperationException("Can not get child component from the lexeme. Lexeme does not have children.");
    }

    @Override
    public void setChildComponents(List<Component> components) {
        throw new UnsupportedOperationException("Can not set child components to the lexeme.");
    }
}
