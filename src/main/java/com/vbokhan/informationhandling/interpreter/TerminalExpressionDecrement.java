package com.vbokhan.informationhandling.interpreter;


public class TerminalExpressionDecrement extends AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() - 1);
    }
}
