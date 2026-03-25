package org.phcn.presentation.cli;

import org.phcn.application.dtos.ContaDto;
import org.phcn.application.services.ContaService;
import org.phcn.domain.TipoConta;
import org.phcn.domain.entitys.Conta;
import org.phcn.domain.entitys.Corrente;
import org.phcn.domain.repository.ContaRepository;
import org.phcn.domain.services.ContaServiceDomain;
import org.phcn.infrastructure.persistency.ContaRespositoryImpl;
import org.phcn.infrastructure.secutiry.Security;
import org.phcn.presentation.texts.Formatter;
import org.phcn.presentation.texts.Menus;
import org.phcn.presentation.texts.Perguntas;
import org.phcn.presentation.texts.Respostas;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuPrincipal {
    Scanner scanner = new Scanner(System.in);
    private final ContaService service;
    private final Security security;
    private final ContaServiceDomain serviceDomain;

    public MenuPrincipal(ContaService contaService, Security security, ContaServiceDomain serviceDomain) {
        this.service = contaService;
        this.security = security;
        this.serviceDomain = serviceDomain;
    }

     public void MenuPrincipal(){
        int opcaoSaida;
        String opcaoCpf;
        String senha;
        String chavePix;
        double depositoInicial;
        String tipo = "";

        do {
            System.out.println(Menus.menuPrincipal);
            opcaoSaida = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoSaida){
                case 1:
                    System.out.println(Menus.menuTipoConta);
                    int opcao= scanner.nextInt();
                    switch (opcao){
                        case 1:
                            System.out.println("Criando conta corrente...");
                            tipo = TipoConta.CORRENTE.toString();
                            break;
                        case 2:
                            System.out.println("Criando conta poupança...");
                            tipo = TipoConta.POUPANCA.toString();
                            break;
                        case 3:
                            System.out.println("Criando conta salário...");
                            tipo = TipoConta.SALARIO.toString();
                            break;
                        default:
                            System.out.println(Respostas.opcaoInvalida);
                            continue;
                    }
                    scanner.nextLine();

                    System.out.println(Perguntas.preenchimentoNome);
                    String nome =  scanner.nextLine();

                    System.out.println(Perguntas.preenchimentoCpf);
                    String cpf = scanner.nextLine();
                    while (cpf.length() != 11){
                        System.out.println(Respostas.cpfInvalido);
                        cpf = scanner.nextLine();
                    }

                    System.out.println(Perguntas.definaSuaSenha);
                    senha = scanner.nextLine();

                    System.out.println(Menus.menuChavePixPersonalizada);
                    int opcaoPix = scanner.nextInt();

                    scanner.nextLine();
                    if (opcaoPix == 1){
                        System.out.println(Perguntas.preenchimentoPixPersonalizado);
                        chavePix = scanner.nextLine();
                    } else if(opcaoPix == 2){
                        System.out.println(Respostas.chavePixPersonalizadaNaoDefinida);
                        chavePix = cpf;
                    } else {
                        System.out.println(Respostas.opcaoInvalida);
                        chavePix = cpf;
                    }

                    System.out.println(Menus.menuDepositoInicial);
                    int opcaoDeposito = scanner.nextInt();
                    if (opcaoDeposito == 1){
                        System.out.println(Perguntas.valoraSerDepositado);
                        depositoInicial = scanner.nextDouble();
                    } else if(opcaoDeposito == 2){
                        System.out.println(Respostas.depositoNaoRealizado);
                        depositoInicial = 0;
                    } else {
                        System.out.println(Respostas.opcaoInvalida);
                        depositoInicial=0;
                    }
                    scanner.nextLine();

                    service.cadastrarConta(new ContaDto(0,nome, cpf, senha,chavePix,depositoInicial,tipo));
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
                        System.out.println("Digite a chave pix da conta que deseja transferir:");
                        String chavePixTransferencia = scanner.nextLine();

                        System.out.println(Perguntas.valoraSerTransferido);
                        double valor = scanner.nextDouble();
                        scanner.nextLine();
                        serviceDomain.fazerTrasnferencia(opcaoCpf, chavePixTransferencia, valor);
                    } else {
                        System.out.println(Respostas.senhaIncorreta);
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
                        System.out.println(Perguntas.valoraSerSacado);
                        double valor = scanner.nextDouble();
                        service.fazerSaque(opcaoCpf,valor);
                    } else {
                        System.out.println(Respostas.senhaIncorreta);
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
                        System.out.println(Perguntas.valoraSerDepositado);
                        double valor = scanner.nextDouble();
                        service.fazerDeposito(opcaoCpf, valor);
                    }else {
                        System.out.println(Respostas.senhaIncorreta);
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
                    } else {
                        System.out.println(Respostas.senhaIncorreta);
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

                    service.buscarPorCpf(opcaoCpf)
                            .ifPresentOrElse(
                                    conta -> System.out.println(Formatter.ContaFormatter.formatar(conta.fromDto())),
                                    () -> System.out.println("Conta não encontrada.")
                            );                    break;
                case 7:
                    List<Conta> contas = service.listarAtivos();

                    if (contas.isEmpty()) {
                        System.out.println("Nenhuma conta ativa encontrada.");
                    } else {
                        contas.forEach(conta ->
                                System.out.println(Formatter.ContaFormatter.formatar(conta))
                        );
                    }
                    break;
                case 8:
                    System.out.println("Saindo do sistema, obrigado por usar nossos serviços!");
                    break;
                default:
                    System.out.println(Respostas.opcaoInvalida);
            }
        }while (opcaoSaida != 8);
    }
}
