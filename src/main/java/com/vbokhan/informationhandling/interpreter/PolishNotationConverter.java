package com.vbokhan.informationhandling.interpreter;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by vbokh on 07.07.2017.
 */
public class PolishNotationConverter {
    private static final char WHITESPACE = ' ';

    private static final String DECREMENT_SIGN = "--";
    private static final String DECREMENT_NEW_SIGN = "@";

    private static final String INCREMENT_SIGN = "\\+\\+";
    private static final String INCREMENT_NEW_SIGN = "#";

    private static final int NEXT_CHAR = 1;

    private static HashMap<Character, Integer> operatorPriority = new HashMap<>();

    static {
        operatorPriority.put('(', 1);
        operatorPriority.put(')', 1);
        operatorPriority.put('+', 2);
        operatorPriority.put('-', 2);
        operatorPriority.put('*', 3);
        operatorPriority.put('/', 3);
        operatorPriority.put('@', 4);
        operatorPriority.put('#', 4);
    }

    public static String reverseExpression(String expression) {
        expression = expression.replaceAll(DECREMENT_SIGN, DECREMENT_NEW_SIGN);
        expression = expression.replaceAll(INCREMENT_SIGN, INCREMENT_NEW_SIGN);

        ArrayDeque<Character> operationStack = new ArrayDeque<>();
        StringBuilder postfixExpression = new StringBuilder();
        int p = expression.length();

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            if (Character.isDigit(token)) {
                postfixExpression.append(token);
                if (i == expression.length()-1) {
                    postfixExpression.append(WHITESPACE);
                    break;
                }else if(!Character.isDigit(expression.charAt(i + NEXT_CHAR))){
                    postfixExpression.append(WHITESPACE);
                }
            } else {
                if (token == 'i' || token == 'j') {
                    postfixExpression.append(token).append(WHITESPACE);
                }
            }
            switch (token) {
                case '(':
                    operationStack.push(token);
                    break;
                case ')':
                    while (!operationStack.isEmpty() && operationStack.getFirst() != '(') {
                        postfixExpression.append(operationStack.pop()).append(WHITESPACE);
                    }
                    if (!operationStack.isEmpty() && operationStack.peek() == '(') {
                        operationStack.pop();
                    }
                    break;
                case '+':
                    while (!operationStack.isEmpty() && operatorPriority.get('+') <= operatorPriority.get(operationStack.peek())) {
                        postfixExpression.append(operationStack.pop()).append(WHITESPACE);
                    }
                    operationStack.push(token);
                    break;
                case '-':
                    while (!operationStack.isEmpty() && operatorPriority.get('-') <= operatorPriority.get(operationStack.peek())) {
                        postfixExpression.append(operationStack.pop()).append(WHITESPACE);
                    }
                    operationStack.push(token);
                    break;
                case '*':
                    while (!operationStack.isEmpty() && operatorPriority.get('*') <= operatorPriority.get(operationStack.peek())) {
                        postfixExpression.append(operationStack.pop()).append(WHITESPACE);
                    }
                    operationStack.push(token);
                    break;
                case '/':
                    while (!operationStack.isEmpty() && operatorPriority.get('/') <= operatorPriority.get(operationStack.peek())) {
                        postfixExpression.append(operationStack.pop()).append(WHITESPACE);
                    }
                    operationStack.push(token);
                    break;
                case '@':
                    while (!operationStack.isEmpty() && operatorPriority.get('@') <= operatorPriority.get(operationStack.peek())) {
                        postfixExpression.append(operationStack.pop()).append(WHITESPACE);
                    }
                    operationStack.push(token);
                    break;
                case '#':
                    while (!operationStack.isEmpty() && operatorPriority.get('#') <= operatorPriority.get(operationStack.peek())) {
                        postfixExpression.append(operationStack.pop()).append(WHITESPACE);
                    }
                    operationStack.push(token);
                    break;
            }
        }
        while (!operationStack.isEmpty() && operationStack.peek() != '(') {
            postfixExpression.append(operationStack.pop()).append(WHITESPACE);
        }
        return postfixExpression.toString().trim();
    }
}
