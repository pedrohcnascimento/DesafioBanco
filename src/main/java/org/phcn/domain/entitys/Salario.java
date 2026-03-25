package org.phcn.domain.entitys;

import org.phcn.presentation.texts.Respostas;

public class Salario extends Conta{
    @Override
    public boolean fazerSaque(double valor) {
        if (saldoAtual >= valor) {
            saldoAtual -= valor;
            System.out.println(Respostas.respostaSaque);
            return true;
        } else {
            throw new RuntimeException("Saldo insuficiente.");
        }
    }
}
