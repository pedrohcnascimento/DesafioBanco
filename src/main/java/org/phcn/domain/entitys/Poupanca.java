package org.phcn.domain.entitys;

import org.phcn.presentation.texts.Respostas;

public class Poupanca extends Conta{
    @Override
    public boolean fazerSaque(double valor) {
        if (saldoAtual == valor) {
            saldoAtual -= valor;
            System.out.println(Respostas.respostaSaque);
            return true;
        } else if (saldoAtual > 0){
            System.out.println("Contas poupanças devem retirar tudo quando sacadas.");
            return false;
        }else {
            System.out.println(Respostas.limiteUltrapassado);
            return false;
        }
    }
}
