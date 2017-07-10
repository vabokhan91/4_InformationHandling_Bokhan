package com.vbokhan.informationhandling.entity;

import java.util.ArrayList;
import java.util.List;


public class TextComponent implements Component {
    private TextType textType;
    private List<Component> childComponents;

    public TextComponent(TextType textType) {
        this.textType = textType;
        childComponents = new ArrayList<>();
    }

    @Override
    public void addComponent(Component component) {
        childComponents.add(component);
    }

    @Override
    public void removeComponent(Component component) {
        childComponents.remove(component);
    }

    @Override
    public List<Component> getChild() {
        return childComponents;
    }

    @Override
    public TextType getTextType() {
        return textType;
    }

    @Override
    public int size() {
        return childComponents.size();
    }

    public void setChildComponents(List<Component> components) {
        childComponents = components;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Component component : childComponents) {
            builder.append(component.toString());
            TextType type = component.getTextType();
            if (type == TextType.LEXEME) {
                builder.append(" ");
            }
            if (type == TextType.PUNCTUATION) {
                int index = builder.lastIndexOf(" ");
                builder.replace(index, index + 1, "");
                builder.append(" ");
            }
            if (type == TextType.PARAGRAPH) {
                int index = builder.lastIndexOf(" ");
                builder.replace(index, index + 1, "\n");

            }
        }
        return builder.toString();
    }
}
