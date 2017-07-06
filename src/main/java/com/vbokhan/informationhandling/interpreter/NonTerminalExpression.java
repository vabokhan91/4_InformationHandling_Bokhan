package com.vbokhan.informationhandling.interpreter;


public class NonTerminalExpression extends AbstractMathExpression{
    private int number;

    public NonTerminalExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
