package com.vbokhan.informationhandling.interpreter;


public class TerminalExpressionMinus extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        int first = c.popValue();
        int second = c.popValue();
        c.pushValue(second - first);
    }
}

