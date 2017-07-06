package com.vbokhan.informationhandling.interpreter;

import com.vbokhan.informationhandling.exception.WrongDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;


public class Client {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DELIMITER_FOR_EXPRESSION = "(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d|\\D)";
    private static final String REGEX_FOR_INTEGER = "^-?\\d+$";
    private static final String DECREMENT_SIGN = "--";
    private static final String DECREMENT_NEW_SIGN = "@";
    private static final String INCREMENT_SIGN = "\\+\\+";
    private static final String INCREMENT_NEW_SIGN = "#";
    private ArrayList<AbstractMathExpression> listExpression;
    private static int i;
    private static int j;

    public Client(String expression) throws WrongDataException{
        listExpression = new ArrayList<>();
        parse(expression);
    }

    private void parse(String expression) throws WrongDataException{
        if (expression == null || expression.isEmpty()) {
            throw new WrongDataException("No expression to parse");
        }
        expression = expression.replaceAll(DECREMENT_SIGN, DECREMENT_NEW_SIGN);
        expression = expression.replaceAll(INCREMENT_SIGN, INCREMENT_NEW_SIGN);
        expression.trim();
        evaluateExpression(expression);


    }

    private void evaluateExpression(String expression) throws WrongDataException {
        List<String> list = new ArrayList<>(Arrays.asList(expression.split(DELIMITER_FOR_EXPRESSION)));
        for (String lexeme : list) {
            if (lexeme.isEmpty()) {
                continue;
            }
            switch ( lexeme.charAt(0)) {
                case '+':
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case '-':
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case '*':
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case '/':
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                case '#':
                    listExpression.add(new TerminalExpressionIncrement());
                    break;
                case '@':
                    listExpression.add(new TerminalExpressionDecrement());
                    break;
                case 'i':
                    listExpression.add(new NonTerminalExpression(i));
                    break;
                case 'j':
                    listExpression.add(new NonTerminalExpression(j));
                    break;
                case '(':
                    int firstBracket = expression.indexOf('(');
                    int lastBracket = expression.lastIndexOf(')');
                    String subExpression = expression.substring(firstBracket + 1, lastBracket);
                    evaluateExpression(subExpression);
                    Client client = new Client(subExpression);
                    int result = client.calculate().intValue();

                    listExpression.add(new NonTerminalExpression(result));
                    return;
                case ')':
                    break;
                case ' ':
                    break;
                default:
                    Pattern pattern = Pattern.compile(REGEX_FOR_INTEGER);
                    if (pattern.matcher(lexeme).matches()) {
                        int number = Integer.parseInt(lexeme);
                        listExpression.add(new NonTerminalExpression(number));
                    } else {
                        LOGGER.log(Level.ERROR, "Wrong value " + lexeme);
                    }
            }
        }
    }

    public Number calculate() {
        Context context = new Context();
        for(int i = 0; i <= listExpression.size()-1; i++) {
            AbstractMathExpression exp = listExpression.get(i);
            if(!(exp instanceof NonTerminalExpression) && i !=listExpression.size()-1){
                listExpression.get(i+1).interpret(context);
                Collections.swap(listExpression, i, i+1);
                i++;
            }
            exp.interpret(context);
        }
        return context.popValue();
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        Client.i = i;
    }

    public static int getJ() {
        return j;
    }

    public static void setJ(int j) {
        Client.j = j;
    }

    
}
