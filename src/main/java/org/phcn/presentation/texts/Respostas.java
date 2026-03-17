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
}
