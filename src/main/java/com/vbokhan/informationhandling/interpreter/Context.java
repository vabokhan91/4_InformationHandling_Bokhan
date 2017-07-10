package com.vbokhan.informationhandling.interpreter;

import java.util.ArrayDeque;


public class Context {
    private ArrayDeque<Double> contextValues = new ArrayDeque<>();


    public Double popValue() {
        double number = 0;
        if (contextValues.peek() != null) {
            number = contextValues.pop();
        }
        return number;
    }

    public void pushValue(Double value) {
        contextValues.push(value);
    }
}
