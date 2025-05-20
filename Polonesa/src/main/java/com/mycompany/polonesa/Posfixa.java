package com.mycompany.polonesa;

import java.util.Stack;

public class Posfixa {

    public String PosFixaParaInfixa(String posfixa) {
        Stack<String> numeros = new Stack<>();
        String[] tokens = posfixa.trim().split("\\s+");
        for (String token : tokens) {
            if (isNumero(token)) {
                numeros.push(token);
            } 
            else if (isOperador(token.charAt(0)) && token.length() == 1) {
                if (numeros.size() < 2) {
                    throw new IllegalArgumentException("Expressão pósfixa inválida");
                }

                String operando2 = numeros.pop();
                String operando1 = numeros.pop();
                String infix = "(" + operando1 + " " + token + " " + operando2 + ")";
                numeros.push(infix);
            } 
            else {
                throw new IllegalArgumentException("Token inválido: " + token);
            }
        }

        if (numeros.size() != 1) {
            throw new IllegalArgumentException("Expressão pósfixa inválida");
        }

        return numeros.pop();
    }

    public String posFixaParaPreFixa(String posfixa) {
        Stack<String> stack = new Stack<>();
        String[] tokens = posfixa.trim().split("\\s+");

        for (String token : tokens) {
            if (isNumero(token)) {
                stack.push(token);
            } 
            else if (isOperador(token.charAt(0)) && token.length() == 1) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Expressão pósfixa inválida");
                }

                String operando2 = stack.pop();
                String operando1 = stack.pop();
                String prefixa = token + " " + operando1 + " " + operando2;
                stack.push(prefixa);
            } 
            else {
                throw new IllegalArgumentException("Token inválido: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Expressão pósfixa inválida");
        }

        return stack.pop();
    }

    private static boolean isOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean isNumero(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
