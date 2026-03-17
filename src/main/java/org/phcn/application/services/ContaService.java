package org.phcn.application.services;

import org.phcn.domain.entitys.Conta;
import org.phcn.domain.entitys.Corrente;
import org.phcn.domain.entitys.Poupanca;
import org.phcn.domain.entitys.Salario;
import org.phcn.presentation.texts.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContaService {
    static Scanner scanner = new Scanner(System.in);
    static List<Conta> contas = new ArrayList<>();
    static Conta conta;


    public void cadastrarConta(long numeroConta){
        Menus menus = new Menus();
        System.out.println(Menus.menuTipoConta);
        int opcao = scanner.nextInt();
        switch (opcao){
            case 1:
                System.out.println("Criando conta corrente...");
                conta = new Corrente();
                break;
            case 2:
                System.out.println("Criando conta poupança...");
                conta = new Poupanca();
                break;
            case 3:
                System.out.println("Criando conta salário...");
                conta = new Salario();
                break;
            default:
                System.out.println("Opção inválida, tente novamente!");
        }
        conta.setIdConta(numeroConta);
        System.out.println("""
                Preencha os seguintes campos:
                1- Nome do titular
                """);
        scanner.nextLine();
        conta.setNome(scanner.nextLine());
        System.out.println("""
                2- CPF do titular
                """);
        conta.setCpf(scanner.nextLine());
        System.out.println("""
                Deseja personalizar chave pix?
                1- Sim
                2- Não
                """);
        int opcaoPix = scanner.nextInt();
        scanner.nextLine();
        if (opcaoPix == 1){
            System.out.println("""
                    Digite a chave pix personalizada:
                    """);
            conta.setChavePix(scanner.nextLine());
            if (conta.getChavePix().isBlank()){
                System.out.println("""
                        Chave pix personalizada não definida, chave pix padrão será o CPF do titular.
                        """);
                conta.setChavePix(conta.getCpf());
            }
        } else if(opcaoPix == 2){
            System.out.println("""
                    Chave pix personalizada não definida, chave pix padrão será o CPF do titular.
                    """);
            conta.setChavePix(conta.getCpf());
        } else {
            System.out.println("Opção inválida, tente novamente!");
        }
        System.out.println("""
                Deseja realizar depósito inicial?
                1- Sim
                2- Não
                """);
        int opcaoDeposito = scanner.nextInt();
        if (opcaoDeposito == 1){
            System.out.println("""
                    Digite o valor do depósito inicial:
                    """);
            conta.setSaldoAtual(scanner.nextDouble());
        } else if(opcaoDeposito == 2){
            System.out.println("""
                    Depósito inicial não realizado, saldo atual será 0.
                    """);
            conta.setSaldoAtual(0);
        } else {
            System.out.println("Opção inválida, tente novamente!");
        }

        System.out.printf("""
                Conta criada com sucesso!);
                Número da conta: %d
                Nome do titular: %s
                CPF do titular: %s
                Chave pix: %s
                Saldo atual: %.2f,
                Limite: %.2f
                %n""",
                conta.getIdConta(),
                conta.getNome(),
                conta.getCpf(),
                conta.getChavePix(),
                conta.getSaldoAtual(),
                conta.getLimite()
        );
        contas.add(conta);
    }

    public void exibirInformacoesConta(long numeroConta){
        for (Conta conta : contas) {
            if (conta.getIdConta() == numeroConta) {
                System.out.printf("""
                        Número da conta: %d
                        Nome do titular: %s
                        CPF do titular: %s
                        Chave pix: %s
                        Saldo atual: %.2f,
                        Limite: %.2f
                        %n""",
                        conta.getIdConta(),
                        conta.getNome(),
                        conta.getCpf(),
                        conta.getChavePix(),
                        conta.getSaldoAtual(),
                        conta.getLimite()
                );
                return;
            }
        }
        System.out.println("Conta não encontrada!");
    }

    public void exibirInformacoesConta(String cpfTitular){
        for (Conta conta : contas) {
            if (conta.getCpf().equals(cpfTitular)) {
                System.out.printf("""
                        Número da conta: %d
                        Nome do titular: %s
                        CPF do titular: %s
                        Chave pix: %s
                        Saldo atual: %.2f,
                        Limite: %.2f
                        %n""",
                        conta.getIdConta(),
                        conta.getNome(),
                        conta.getCpf(),
                        conta.getChavePix(),
                        conta.getSaldoAtual(),
                        conta.getLimite()
                );
                return;
            }
        }
        System.out.println("Conta não encontrada!");
    }
}
