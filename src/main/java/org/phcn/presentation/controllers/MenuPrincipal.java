package org.phcn.presentation.controllers;

import org.phcn.application.services.ContaService;
import org.phcn.presentation.texts.Menus;

import java.util.Scanner;

public class MenuPrincipal {
    static Scanner scanner = new Scanner(System.in);
    static ContaService service = new ContaService();

     public void MenuPrincipal(){
        int opcaoSaida;
        long numeroConta = 1;
        do {
            System.out.println(Menus.menuPrincipal);
            opcaoSaida = scanner.nextInt();

            switch (opcaoSaida){
                case 1:
                    service.cadastrarConta(numeroConta);
                    numeroConta++;
                    break;
                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    System.out.println(Menus.menuInformacoesContaPrincipal);
                    var opcaoInformacoes = scanner.next();
                    if(opcaoInformacoes.length() ==11){
                        service.exibirInformacoesConta(opcaoInformacoes);
                    }

                    break;
                case 7:
                    System.out.println("Saindo do sistema, obrigado por usar nossos serviços!");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
            }
        }while (opcaoSaida !=7);
    }
}
