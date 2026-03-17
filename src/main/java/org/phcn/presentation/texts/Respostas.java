package org.phcn.presentation.texts;

public class Respostas {
    public static String respostaCadastroConta = """
            Conta criada com sucesso!
            """;

    public static String respostaTransferencia = """
            Transferência realizada com sucesso!
            """;

    public static String respostaSaque = """
            Saque realizado com sucesso!
            """;

    public static String respostaDeposito = """
            Depósito realizado com sucesso!
            """;

    public static String respostaFecharConta = """
            Conta fechada com sucesso!
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

    public static String cpfInvalido = """
            CPF inválido, tente novamente!
            """;

    public static String opcaoInvalida = """
            Opção inválida, tente novamente!
            """;

    public static String chavePixPersonalizadaNaoDefinida = """
            Chave pix personalizada não definida, chave pix padrão será o CPF do titular.
            """;

    public static String depositoNaoRealizado = """
            Depósito não realizado, saldo atual será 0.
            """;

    public static String contaNaoEncontradaOuFechada = """
            Conta não encontrada ou fechada!
            """;

    public static String limiteUltrapassado = """
            Você não possui dinheiro o suficiente em sua conta para realizar esta ação!
            """;
}
