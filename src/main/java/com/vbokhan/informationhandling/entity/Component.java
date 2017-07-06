package com.vbokhan.informationhandling.entity;

import java.util.List;

/**
 * Created by vbokh on 06.07.2017.
 */
public interface Component {
    void addComponent(Component component);

    void removeComponent(Component component);

    List<Component> getChild();

    TextType getTextType();

    int size();
}
