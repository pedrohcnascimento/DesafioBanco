package org.phcn.domain.entitys;

import org.phcn.presentation.texts.Respostas;

public class Corrente extends Conta{
    @Override
    public boolean fazerSaque(double valor) {
        if (saldoAtual + limite >= valor) {
            saldoAtual -= valor;
            System.out.println(Respostas.respostaSaque);
            return true;
        } else {
            System.out.println(Respostas.limiteUltrapassado);
            return false;
        }
    }
}
