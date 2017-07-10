package com.vbokhan.informationhandling.interpreter;


public class TerminalExpressionMinus extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        double first = c.popValue();
        double second = c.popValue();
        c.pushValue(second - first);
    }
}

