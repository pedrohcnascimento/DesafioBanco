package org.phcn.application.services;

import org.phcn.domain.entitys.Conta;
import org.phcn.domain.entitys.Corrente;
import org.phcn.domain.entitys.Poupanca;
import org.phcn.domain.entitys.Salario;
import org.phcn.presentation.texts.Menus;
import org.phcn.presentation.texts.Perguntas;
import org.phcn.presentation.texts.Respostas;

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
                System.out.println(Respostas.opcaoInvalida);
        }

        conta.setIdConta(numeroConta);

        System.out.println(Perguntas.preenchimentoNome);
        scanner.nextLine();
        conta.setNome(scanner.nextLine());

        System.out.println(Perguntas.preenchimentoCpf);
        String cpf = scanner.nextLine();
        while (cpf.length() != 11){
            System.out.println(Respostas.cpfInvalido);
            cpf = scanner.nextLine();
        }
        conta.setCpf(cpf);

        System.out.println(Menus.menuChavePixPersonalizada);
        int opcaoPix = scanner.nextInt();
        scanner.nextLine();
        if (opcaoPix == 1){
            System.out.println(Perguntas.preenchimentoPixPersonalizado);
            conta.setChavePix(scanner.nextLine());
            if (conta.getChavePix().isBlank()){
                System.out.println(Respostas.chavePixPersonalizadaNaoDefinida);
                conta.setChavePix(conta.getCpf());
            }
        } else if(opcaoPix == 2){
            System.out.println(Respostas.chavePixPersonalizadaNaoDefinida);
            conta.setChavePix(conta.getCpf());
        } else {
            System.out.println(Respostas.opcaoInvalida);
        }

        System.out.println(Menus.menuDepositoInicial);
        int opcaoDeposito = scanner.nextInt();
        if (opcaoDeposito == 1){
            System.out.println(Perguntas.valoraSerDepositado);
            conta.setSaldoAtual(scanner.nextDouble());
            conta.setLimite(conta.getSaldoAtual());
            if (conta.getSaldoAtual() == 0){
                System.out.println(Respostas.depositoNaoRealizado);
            }
        } else if(opcaoDeposito == 2){
            System.out.println(Respostas.depositoNaoRealizado);
            conta.setSaldoAtual(0);
        } else {
            System.out.println(Respostas.opcaoInvalida);
        }

        conta.setStatus(true);

        System.out.println(Respostas.respostaCadastroConta);
        System.out.printf(Respostas.respostaExibirInformacoesConta,
                conta.getIdConta(),
                conta.getNome(),
                conta.getCpf(),
                conta.getChavePix(),
                conta.getSaldoAtual(),
                conta.getLimite()
        );
        contas.add(conta);
    }

    public void exibirInformacoesConta(String cpfTitular){
        for (Conta conta : contas) {
            if (conta.getCpf().equals(cpfTitular) && conta.isStatus()) {
                System.out.printf(Respostas.respostaExibirInformacoesConta,
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
        System.out.println(Respostas.contaNaoEncontradaOuFechada);
    }

    public void fecharConta(String cpfTitular){
        for (Conta conta : contas) {
            if (conta.getCpf().equals(cpfTitular) && conta.isStatus()) {
                conta.setStatus(false);
                System.out.println("Conta fechada com sucesso!");
                return;
            }
        }
        System.out.println(Respostas.contaNaoEncontradaOuFechada);
    }

    public void fazerDeposito(String cpfTitular){
        for(Conta conta : contas){
            if (conta.getCpf().equals(cpfTitular) && conta.isStatus()){
                System.out.println(Perguntas.valoraSerDepositado);
                double soma = scanner.nextDouble();
                conta.setSaldoAtual(conta.getSaldoAtual()+soma);
                conta.setLimite(conta.getSaldoAtual());
                System.out.println(Respostas.respostaDeposito);
            }
        }
    }

    public void fazerSaque(String cpfTitular){
        for (Conta conta : contas){
            if (conta.getCpf().equals(cpfTitular) && conta.isStatus()){
                System.out.println(Perguntas.valoraSerSacado);
                double subtracao = scanner.nextDouble();
                if(conta.getLimite() < subtracao){
                    System.out.println(Respostas.limiteUltrapassado);
                } else {
                    conta.setSaldoAtual(conta.getSaldoAtual()-subtracao);
                    conta.setLimite(conta.getSaldoAtual());
                }
            }
        }
    }
}
