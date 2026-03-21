package org.phcn.presentation.texts;

import org.phcn.domain.entitys.Conta;

public class Formatter {
    public class ContaFormatter {

        public static String formatar(Conta conta) {
            return String.format(
                    Menus.respostaExibirInformacoesConta,
                    conta.getIdConta(),
                    conta.getNome(),
                    conta.getCpf(),
                    conta.getChavePix(),
                    conta.getSaldoAtual(),
                    conta.getLimite()
            );
        }
    }
}
