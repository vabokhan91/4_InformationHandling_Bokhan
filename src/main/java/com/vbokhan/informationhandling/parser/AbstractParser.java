package com.vbokhan.informationhandling.parser;

import com.vbokhan.informationhandling.entity.Component;
import com.vbokhan.informationhandling.entity.TextComponent;
import com.vbokhan.informationhandling.exception.WrongDataException;


public abstract class AbstractParser {
    AbstractParser nextParser;

    public void setNextParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract Component parse(String text) throws WrongDataException;
}
