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
    private final ContaService service= new ContaService();
    private final ContaServiceDomain serviceDomain = new ContaServiceDomain();
    private final Security security = new Security();

     public void MenuPrincipal(){
        int opcaoSaida;
        long numeroConta = 1;
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

                    service.cadastrarConta(new ContaDto(numeroConta,nome, cpf, senha,chavePix,depositoInicial,tipo));
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
//                        service.fazerTrasferencia(opcaoCpf);
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
