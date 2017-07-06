package com.vbokhan.informationhandling.interpreter;

/**
 * Created by vbokh on 06.07.2017.
 */
public class TerminalExpressionDecrement extends AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() - 1);
    }
}
