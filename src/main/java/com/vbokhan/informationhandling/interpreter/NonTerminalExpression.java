package com.vbokhan.informationhandling.interpreter;


public class NonTerminalExpression extends AbstractMathExpression{
    private double number;

    public NonTerminalExpression(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
