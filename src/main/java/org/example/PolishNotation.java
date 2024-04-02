package org.example;
import java.util.*;
public class PolishNotation {
    private char[] infixExpression;
    private ArrayList<Character> postfixExpression;
    private static final Map<Character, Integer> operationPriority = new HashMap<>();
    static {
        operationPriority.put('(',0);
        operationPriority.put('+',1);
        operationPriority.put('-',1);
        operationPriority.put('*',2);
        operationPriority.put('/',2);
        operationPriority.put('^',3);
    }
    public PolishNotation(char[] expression){
        infixExpression = expression;
        postfixExpression = new ArrayList<>();
    }
    public char[] getInfExpression() {
        return infixExpression;
    }
    public ArrayList<Character> getPostExpression() {
        return postfixExpression;
    }
    public void toPostfix(){
        Stack<Character> operatorsStack = new Stack<>();
        for (Character element:infixExpression) {
           if (Character.isDigit(element)){
               postfixExpression.add(element);
           } else if (element == '(') {
               operatorsStack.push(element);
           } else if (element == ')') {
               while (operatorsStack.size()>0 && operatorsStack.peek()!='('){
                   postfixExpression.add(operatorsStack.pop());
               }
               operatorsStack.pop(); 
           } else if (operationPriority.containsKey(element)) {
               while (operatorsStack.size()>0 &&
                       operationPriority.get(operatorsStack.peek())>=operationPriority.get(element)){
                   postfixExpression.add(operatorsStack.pop());
               }
               operatorsStack.push(element);
           }
        }
        for (Character operator: operatorsStack) {
            postfixExpression.add(operator);
        }
    }
    public void setInfExpression(char[] infExpression) {
        this.infixExpression = infExpression;
    }
    public void setPostExpression(ArrayList<Character> postExpression) {
        this.postfixExpression = postExpression;
    }
}
