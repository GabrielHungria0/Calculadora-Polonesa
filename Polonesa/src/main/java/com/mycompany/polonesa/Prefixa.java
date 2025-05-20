/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.polonesa;

import java.util.Stack;

/**
 *
 * @author gabri
 */
public class Prefixa {


    public String converterPrefixaPosfixa(String expressaoPrefixa) {
        Stack<String> pilha = new Stack<>();
   
        for (int i = expressaoPrefixa.length() - 1; i >= 0; i--) {
            char c = expressaoPrefixa.charAt(i);
            
            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c) || Character.isLetter(c)) {
                pilha.push(Character.toString(c));
            } 

            else if (ehOperador(c)) {
                if (pilha.size() < 2) {
                    throw new IllegalArgumentException("Expressão prefixa inválida");
                }
                
                String operando1 = pilha.pop();
                String operando2 = pilha.pop();
                
                String expressaoPosfixa = operando1 + " " + operando2 + " " + c;
                pilha.push(expressaoPosfixa);
            }
            else {
                throw new IllegalArgumentException("Caractere inválido: " + c);
            }
        }
        
        if (pilha.size() != 1) {
            throw new IllegalArgumentException("Expressão prefixa inválida");
        }
        
        return pilha.pop();
    }
    
    private static boolean ehOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}