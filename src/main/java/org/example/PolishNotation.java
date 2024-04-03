package org.example;
import java.util.*;
public class PolishNotation {
    private String[] infixExpression;
    private ArrayList<String> postfixExpression;
    private static final Map<String, Integer> operationPriority = new HashMap<>();
    static {
        operationPriority.put("(",0);
        operationPriority.put("+",1);
        operationPriority.put("-",1);
        operationPriority.put("*",2);
        operationPriority.put("/",2);
        operationPriority.put("^",3);
    }
    public PolishNotation(String[] expression){
        infixExpression = expression;
        postfixExpression = new ArrayList<>();
    }
    public String[] getInfExpression() {
        return infixExpression;
    }
    public ArrayList<String> getPostfixExpression() {
        return postfixExpression;
    }
    public void toPostfix(){
        Stack<String> operatorsStack = new Stack<>(); //create stack, contains expression operators
        for (String element:infixExpression) { //look through all expression
           if (element.matches("-?\\d+")){ // if finds number, add it immediately to postfix expression
               postfixExpression.add(element);
           } else if (element.equals("(")) {
               operatorsStack.push(element);
           } else if (element.equals(")")) { // add to postfix expression all from stack till opening bracket
               while (operatorsStack.size()>0 && !operatorsStack.peek().equals("(")){
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
    public void setInfExpression(String[] infExpression) {
        this.infixExpression = infExpression;
    }
    public void setPostExpression(ArrayList<String> postExpression) {
        this.postfixExpression = postExpression;
    }
}
