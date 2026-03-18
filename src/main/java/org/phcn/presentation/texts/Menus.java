package org.phcn.presentation.texts;

public class Menus {
    public static String menuPrincipal = """
            _________________________________________________________
            |   Escolha uma opção:                                  |
            |       1- Criar nova conta no banco                    |
            |       2- Fazer transferencia                          |
            |       3- Fazer saque                                  |
            |       4- Fazer depósito                               |
            |       5- Fechar conta                                 |
            |       6- Exibir informações de conta                  |
            |       7- Sair                                         |
            _________________________________________________________
            """;

    public static String menuTipoConta = """
                Selecione o tipo de conta:
                1- Conta Corrente
                2- Conta Poupança
                3- Conta Salário
                """;

    public static String menuDepositoInicial = """
            Deseja realizar depósito inicial?
            1- Sim
            2- Não
            """;

    public static String menuChavePixPersonalizada = """
            Deseja personalizar chave pix?
            1- Sim
            2- Não
            """;

    public static String respostaExibirInformacoesConta = """
            Informações da conta:
            Número da conta: %d
            Nome do titular: %s
            CPF do titular: %s
            Chave pix: %s
            Saldo atual: %.2f,
            Limite: %.2f
            %n""";
}
