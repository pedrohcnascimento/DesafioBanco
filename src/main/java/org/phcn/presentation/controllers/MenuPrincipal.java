package org.phcn.presentation.controllers;

import org.phcn.application.services.ContaService;
import org.phcn.presentation.texts.Menus;
import org.phcn.presentation.texts.Perguntas;
import org.phcn.presentation.texts.Respostas;

import java.util.Scanner;

public class MenuPrincipal {
    static Scanner scanner = new Scanner(System.in);
    static ContaService service = new ContaService();

     public void MenuPrincipal(){
        int opcaoSaida;
        long numeroConta = 1;
        String opcaoCpf;

        do {
            System.out.println(Menus.menuPrincipal);
            opcaoSaida = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoSaida){
                case 1:
                    service.cadastrarConta(numeroConta);
                    numeroConta++;
                    break;
                case 2:

                    break;

                case 3:
                    System.out.println(Perguntas.cpfNecessario);
                    opcaoCpf = scanner.nextLine();

                    while (opcaoCpf.length() != 11){
                        System.out.println(Respostas.cpfInvalido);
                        opcaoCpf = scanner.nextLine();
                    }
                    service.fazerSaque(opcaoCpf);
                    break;
                case 4:
                    System.out.println(Perguntas.cpfNecessario);
                    opcaoCpf = scanner.nextLine();

                    while(opcaoCpf.length() != 11){
                        System.out.println(Respostas.cpfInvalido);
                        opcaoCpf = scanner.nextLine();
                    }
                    service.fazerDeposito(opcaoCpf);
                    break;
                case 5:
                    System.out.println(Perguntas.cpfNecessario);
                    opcaoCpf = scanner.nextLine();

                    while (opcaoCpf.length() != 11){
                        System.out.println(Respostas.cpfInvalido);
                        opcaoCpf = scanner.nextLine();
                    }

                    service.fecharConta(opcaoCpf);
                    break;
                case 6:
                    System.out.println(Perguntas.cpfNecessario);
                    opcaoCpf = scanner.nextLine();

                    while (opcaoCpf.length() != 11){
                        System.out.println(Respostas.cpfInvalido);
                        opcaoCpf = scanner.nextLine();
                    }

                    service.exibirInformacoesConta(opcaoCpf);
                    break;
                case 7:
                    System.out.println("Saindo do sistema, obrigado por usar nossos serviços!");
                    break;
                default:
                    System.out.println(Respostas.opcaoInvalida);
            }
        }while (opcaoSaida !=7);
    }
}
