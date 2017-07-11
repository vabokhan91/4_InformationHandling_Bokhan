package com.vbokhan.informationhandling.entity;

import java.util.List;


public class Punctuation implements Component {
    private TextType textType = TextType.PUNCTUATION;
    private String content;

    public Punctuation(String content) {
        this.content = content;
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

    @Override
    public void addComponent(Component component) {
        throw new UnsupportedOperationException("Can not add component to the lexeme");
    }

    @Override
    public List<Component> getChild() {
        throw new UnsupportedOperationException("Can not get child component from the lexeme");
    }

    @Override
    public void setChildComponents(List<Component> components) {
        throw new UnsupportedOperationException("Can not set child component for the punctuation");
    }
}
