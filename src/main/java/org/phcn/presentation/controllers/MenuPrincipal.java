package org.phcn.presentation.controllers;

import org.phcn.application.services.ContaService;
import org.phcn.domain.services.ContaServiceDomain;
import org.phcn.infrastructure.secutiry.Security;
import org.phcn.presentation.texts.Menus;
import org.phcn.presentation.texts.Perguntas;
import org.phcn.presentation.texts.Respostas;

import java.util.Scanner;

public class MenuPrincipal {
    Scanner scanner = new Scanner(System.in);
    private final ContaService service = new ContaService();
    private final ContaServiceDomain serviceDomain = new ContaServiceDomain();
    private final Security security = new Security();

     public void MenuPrincipal(){
        int opcaoSaida;
        long numeroConta = 1;
        String opcaoCpf;
        String senha;

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
                    System.out.println(Perguntas.cpfNecessario);
                    opcaoCpf = scanner.nextLine();

                    while (opcaoCpf.length() !=11){
                        System.out.println(Respostas.cpfInvalido);
                        opcaoCpf = scanner.nextLine();
                    }
                    System.out.println(Perguntas.digiteSuaSenha);
                    senha = scanner.nextLine();
                    if (security.verificarInformacoes(opcaoCpf,senha)){
                        serviceDomain.fazerTrasnferencia(opcaoCpf);
                    }
                    break;
                case 3:
                    System.out.println(Perguntas.cpfNecessario);
                    opcaoCpf = scanner.nextLine();

                    while (opcaoCpf.length() != 11){
                        System.out.println(Respostas.cpfInvalido);
                        opcaoCpf = scanner.nextLine();
                    }

                    System.out.println(Perguntas.digiteSuaSenha);
                    senha = scanner.nextLine();
                    if (security.verificarInformacoes(opcaoCpf,senha)) {
                        serviceDomain.fazerSaque(opcaoCpf);
                    }
                    break;
                case 4:
                    System.out.println(Perguntas.cpfNecessario);
                    opcaoCpf = scanner.nextLine();

                    while(opcaoCpf.length() != 11){
                        System.out.println(Respostas.cpfInvalido);
                        opcaoCpf = scanner.nextLine();
                    }

                    System.out.println(Perguntas.digiteSuaSenha);
                    senha = scanner.nextLine();
                    if (security.verificarInformacoes(opcaoCpf,senha)) {
                        serviceDomain.fazerDeposito(opcaoCpf);
                    }
                    break;
                case 5:
                    System.out.println(Perguntas.cpfNecessario);
                    opcaoCpf = scanner.nextLine();

                    while (opcaoCpf.length() != 11){
                        System.out.println(Respostas.cpfInvalido);
                        opcaoCpf = scanner.nextLine();
                    }
                    System.out.println(Perguntas.digiteSuaSenha);
                    senha = scanner.nextLine();
                    if (security.verificarInformacoes(opcaoCpf,senha)) {
                        service.fecharConta(opcaoCpf);
                    }
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
