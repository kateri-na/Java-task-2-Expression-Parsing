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
    public ArrayList<Character> getPostfixExpression() {
        return postfixExpression;
    }
    public void toPostfix(){
        Stack<Character> operatorsStack = new Stack<>(); //create stack, contains expression operators
        for (Character element:infixExpression) { //look through all expression
           if (Character.isDigit(element)){ // if finds number, add it immediately to postfix expression
               postfixExpression.add(element);
           } else if (element == '(') {
               operatorsStack.push(element);
           } else if (element == ')') { // add to postfix expression all from stack till opening bracket
               while (operatorsStack.size()>0 && operatorsStack.peek()!='('){
                   postfixExpression.add(operatorsStack.pop());
               }
               operatorsStack.pop(); //delete opening bracket from stack
           } else if (operationPriority.containsKey(element)) { //check if element contains in the list of operators
               //add to postfix expression all higher priority operators from stack
               while (operatorsStack.size()>0 &&
                       (operationPriority.get(operatorsStack.peek())>=operationPriority.get(element))){
                   postfixExpression.add(operatorsStack.pop());
               }
               //add operator to stack
               operatorsStack.push(element);
           }
        }
        //add all remaining from stack operators to postfix expression
        while (!operatorsStack.isEmpty()){
            postfixExpression.add(operatorsStack.pop());
        }
    }
    public void setInfExpression(char[] infExpression) {
        this.infixExpression = infExpression;
    }
    public void setPostExpression(ArrayList<Character> postExpression) {
        this.postfixExpression = postExpression;
    }
}
