/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.polonesa;

/**
 *
 * @author gabri
 */
import java.util.Scanner;
public class Polonesa {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Infixa opInfixa = new Infixa();  
        Posfixa opPosfixa = new Posfixa();
        Double resultado = null;
        String infixa = null;
        String posfixa = null;
        String prefixa = null; 
        boolean continuar = true;
        
    while(continuar){

        
        System.out.println("informe o tipo");
        System.out.println("1 - INFIXA");
        System.out.println("2 - POSFIXA");
        System.out.println("3 - PREFIXA");
        System.out.println("4 - SAIR");
        int  operacao = scanner.nextInt();
        scanner.nextLine();
        
        if(operacao==4){
            continuar = false;
            break;
        }
        
        System.out.println("Informe a expressao");
        String expressao = scanner.nextLine();
       

        switch(operacao){
            case 1:
                
               infixa = expressao;
               posfixa = opInfixa.converterParaPosfixa(expressao);
               prefixa = opPosfixa.posFixaParaPreFixa(posfixa);
               resultado = opInfixa.calcular(expressao);
               
                break;
                
            case 2:
                
                posfixa = expressao;
                infixa = opPosfixa.PosFixaParaInfixa(expressao);
                prefixa = opPosfixa.posFixaParaPreFixa(expressao);
                resultado = opInfixa.calcular(infixa);
                
                break;
           
            case 3:
                Prefixa opPreFixa = new Prefixa();
                
                prefixa = expressao;
                posfixa = opPreFixa.converterPrefixaPosfixa(expressao);
                infixa = opPosfixa.PosFixaParaInfixa(posfixa);
                resultado = opInfixa.calcular(infixa);
                
                break;   
            default:
                System.out.println("Opcao errada digite novamente");
                break;
        }
        
          System.out.println("Resultado: "+resultado);
          System.out.println("Expressao PosFixa: "+posfixa);
          System.out.println("Expressao Infixa: "+infixa);
          System.out.println("Expressao Prefixa: "+prefixa);
        
    }
    }     
}
