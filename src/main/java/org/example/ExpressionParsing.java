package org.example;

import java.util.Stack;
public class ExpressionParsing {
    private char[] expression;
    public ExpressionParsing(String expression){
        this.expression = expression.toCharArray();
    }
    public boolean BracketsCheck(){
       Stack<Character> stack =new Stack<>();
        for (char element: expression) {
            if (element == '('){
                stack.push(element);
            } else if (element == ')') {
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
}
