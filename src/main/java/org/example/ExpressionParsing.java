package org.example;

import java.text.ParseException;
import java.util.Stack;
public class ExpressionParsing {
    private String[] expression;
    public ExpressionParsing(String expression){
        expression = expression.replaceAll(" ","");
        this.expression = expression.split("(?=[^\\d\\s])|(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
    }
    public String[] getExpression() {
        return expression;
    }
    public boolean BracketsCheck(){
       Stack<String> stack =new Stack<>();
        for (String element: expression) {
            if (element == "("){
                stack.push(element);
            } else if (element == ")") {
                if(stack.isEmpty()) {
                    return false;
                }
                else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        else{
            return false;
        }
    }
    public double calculation(){
        Stack<Double> valuesStack = new Stack<>();
        if(BracketsCheck()){
            PolishNotation polishNotation = new PolishNotation(expression);
            polishNotation.toPostfix();
            for (Character element: polishNotation.getPostfixExpression()) {
                if (Character.isDigit(element)){
                    valuesStack.push((double)Character.getNumericValue(element));
                }
                else {
                    valuesStack.push(calculateOperation(element,valuesStack.pop(),valuesStack.pop()));
                }
            }
            if(valuesStack.size() == 1) {
                return valuesStack.pop();
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalStateException();
        }
    }
    private double calculateOperation(char operation, double second, double first){
        switch (operation){
            case '+':
                return first+second;
            case '-':
                return first-second;
            case '*':
                return first*second;
            case '/':
                return first/second;
            case '^':
                return Math.pow(first, second);
            default:
                throw new IllegalArgumentException();
        }
    }
}
