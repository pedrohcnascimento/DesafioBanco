package org.phcn.application.services;

import org.phcn.domain.entitys.Conta;
import org.phcn.domain.entitys.Corrente;
import org.phcn.domain.entitys.Poupanca;
import org.phcn.domain.entitys.Salario;
import org.phcn.infrastructure.secutiry.Security;
import org.phcn.presentation.texts.Menus;
import org.phcn.presentation.texts.Perguntas;
import org.phcn.presentation.texts.Respostas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContaService {
    static Scanner scanner = new Scanner(System.in);
    public static List<Conta> contas = new ArrayList<>();
    static Conta conta;
    private Security security;


    public void cadastrarConta(long numeroConta){
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

        System.out.println(Perguntas.definaSuaSenha);
        conta.setSenha(scanner.nextLine());

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
        System.out.printf(Menus.respostaExibirInformacoesConta,
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
            if (conta.getCpf().equals(cpfTitular)) {
                System.out.printf(Menus.respostaExibirInformacoesConta,
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
            if (conta.getCpf().equals(cpfTitular)) {
                conta.setStatus(false);
                System.out.println(Respostas.respostaFecharConta);
                return;
            }
        }
        System.out.println(Respostas.contaNaoEncontradaOuFechada);
    }
}
