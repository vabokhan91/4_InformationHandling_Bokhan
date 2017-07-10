package com.vbokhan.informationhandling.interpreter;

import com.vbokhan.informationhandling.exception.WrongDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Client {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DELIMITER_FOR_EXPRESSION = "\\p{Blank}";
    private static final String REGEX_FOR_INTEGER = "^-?\\d+$";
    private ArrayList<AbstractMathExpression> listExpression;
    private static double i;
    private static double j;

    public Client(String expression) throws WrongDataException {
        listExpression = new ArrayList<>();
        parse(expression);
    }

    private void parse(String expression) throws WrongDataException {
        if (expression == null || expression.isEmpty()) {
            throw new WrongDataException("No expression to parse");
        }
        String reversed = PolishNotationConverter.reverseExpression(expression);
        evaluateExpression(reversed);
    }

    private void evaluateExpression(String expression) throws WrongDataException {
        String[] s = expression.split(DELIMITER_FOR_EXPRESSION);
        for (String lexeme : s) {
            if (lexeme.isEmpty()) {
                LOGGER.info("The lexeme is empty");
            } else {
                switch (lexeme) {
                    case "+":
                        listExpression.add(new TerminalExpressionPlus());
                        break;
                    case "-":
                        listExpression.add(new TerminalExpressionMinus());
                        break;
                    case "*":
                        listExpression.add(new TerminalExpressionMultiply());
                        break;
                    case "/":
                        listExpression.add(new TerminalExpressionDivide());
                        break;
                    case "#":
                        listExpression.add(new TerminalExpressionIncrement());
                        break;
                    case "@":
                        listExpression.add(new TerminalExpressionDecrement());
                        break;
                    case "i":
                        listExpression.add(new NonTerminalExpression(i));
                        break;
                    case "j":
                        listExpression.add(new NonTerminalExpression(j));
                        break;
                    default:
                        Pattern pattern = Pattern.compile(REGEX_FOR_INTEGER);
                        Matcher numberInteger = pattern.matcher(lexeme);
                        if (numberInteger.matches()) {
                            int number = Integer.parseInt(lexeme);
                            listExpression.add(new NonTerminalExpression(number));
                        } else {
                            LOGGER.error("Wrong value " + lexeme);
                        }
                }
            }
        }
    }

    public Number calculate() {
        Context context = new Context();
        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);

        }
        return context.popValue();
    }

    public static double getI() {
        return i;
    }

    public static void setI(double i) {
        Client.i = i;
    }

    public static double getJ() {
        return j;
    }

    public static void setJ(double j) {
        Client.j = j;
    }


}
