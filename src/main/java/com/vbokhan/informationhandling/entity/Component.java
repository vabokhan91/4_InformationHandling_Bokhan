package com.vbokhan.informationhandling.entity;

import java.util.List;


public interface Component {
    void addComponent(Component component);

    List<Component> getChild();

    TextType getTextType();

    int size();

    void setChildComponents(List<Component> components);
}
