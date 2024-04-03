package org.example;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.printf("Введите выражение: ");
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        ExpressionParsing parse = new ExpressionParsing(expression);
        System.out.println(parse.calculation());
    }
}