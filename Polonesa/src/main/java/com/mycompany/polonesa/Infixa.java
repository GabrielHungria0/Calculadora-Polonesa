package com.mycompany.polonesa;

import java.util.Stack;

public class Infixa {

    public double calcular(String expressao) {
        Stack<Double> pilhaNumeros = new Stack<>();
        Stack<Character> pilhaOperadores = new Stack<>();
        StringBuilder numeroAtual = new StringBuilder();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                numeroAtual.append(c);
            } else {
                if (numeroAtual.length() > 0) {
                    pilhaNumeros.push(Double.parseDouble(numeroAtual.toString()));
                    numeroAtual.setLength(0);
                }

                if (c == '(') {
                    pilhaOperadores.push(c);
                } else if (c == ')') {
                    while (!pilhaOperadores.isEmpty() && pilhaOperadores.peek() != '(') {
                        processarTopo(pilhaNumeros, pilhaOperadores);
                    }
                    if (!pilhaOperadores.isEmpty() && pilhaOperadores.peek() == '(') {
                        pilhaOperadores.pop();
                    }
                } else if (isOperador(c)) {
                    while (!pilhaOperadores.isEmpty() &&
                            precedencia(pilhaOperadores.peek()) >= precedencia(c)) {
                          processarTopo(pilhaNumeros, pilhaOperadores);
                    }
                    pilhaOperadores.push(c);
                }
            }
        }

        if (numeroAtual.length() > 0) {
            pilhaNumeros.push(Double.parseDouble(numeroAtual.toString()));
        }

        while (!pilhaOperadores.isEmpty()) {
            processarTopo(pilhaNumeros, pilhaOperadores);
        }

        return pilhaNumeros.pop();
    }

    public String converterParaPosfixa(String expressaoInfixa) {
        Stack<String> pilhaPosfixa = new Stack<>();
        Stack<Character> pilhaOperadores = new Stack<>();
        StringBuilder numeroAtual = new StringBuilder();
        StringBuilder saida = new StringBuilder();

        for (char token : expressaoInfixa.toCharArray()) {
            if (Character.isDigit(token) || token == '.') {
                numeroAtual.append(token);
            } else {
                if (numeroAtual.length() > 0) {
                    pilhaPosfixa.push(numeroAtual.toString());
                    numeroAtual.setLength(0);
                }
                if (token == '(') {
                    pilhaOperadores.push(token);
                } else if (token == ')') {
                    while (!pilhaOperadores.isEmpty() && pilhaOperadores.peek() != '(') {
                        pilhaPosfixa.push(String.valueOf(pilhaOperadores.pop()));
                    }
                    if (!pilhaOperadores.isEmpty() && pilhaOperadores.peek() == '(') {
                        pilhaOperadores.pop();
                    } else {
                        throw new IllegalArgumentException("Parênteses não balanceados");
                    }
                } else if (isOperador(token)) {
                    while (!pilhaOperadores.isEmpty() && precedencia(pilhaOperadores.peek()) >= precedencia(token)) {
                        pilhaPosfixa.push(String.valueOf(pilhaOperadores.pop()));
                    }
                    pilhaOperadores.push(token);
                } else if (token == ' ') {
                    
                } else {
                    throw new IllegalArgumentException("Caractere inválido na expressão: " + token);
                }
            }
        }
        if (numeroAtual.length() > 0) {
            pilhaPosfixa.push(numeroAtual.toString());
        }
        while (!pilhaOperadores.isEmpty()) {
            if (pilhaOperadores.peek() == '(' || pilhaOperadores.peek() == ')') {
                throw new IllegalArgumentException("Parênteses não balanceados");
            }
            pilhaPosfixa.push(String.valueOf(pilhaOperadores.pop()));
        }
 
        Stack<String> pilhaInvertida = new Stack<>();
        while (!pilhaPosfixa.isEmpty()) {
            pilhaInvertida.push(pilhaPosfixa.pop());
        }
        while (!pilhaInvertida.isEmpty()) {
            saida.append(pilhaInvertida.pop()).append(" ");
        }

        return saida.toString().trim();
    }

    private static boolean isOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static void processarTopo(Stack<Double> numeros, Stack<Character> operadores) {
        double b = numeros.pop();
        double a = numeros.pop();
        char operador = operadores.pop();
        double resultado;

        switch (operador) {
            case '+': resultado = a + b; break;
            case '-': resultado = a - b; break;
            case '*': resultado = a * b; break;
            case '/': resultado = a / b; break;
            default: throw new RuntimeException("Operador desconhecido: " + operador);
        }

        numeros.push(resultado);
    }
}