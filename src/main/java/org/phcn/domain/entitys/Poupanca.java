package org.phcn.domain.entitys;

import org.phcn.domain.TipoConta;
import org.phcn.presentation.texts.Respostas;

public class Poupanca extends Conta{
    public Poupanca(long idConta, String nome, String cpf, String chavePix, double saldoAtual, double limite, boolean status, String senha, TipoConta tipo) {
    }

    public Poupanca() {
    }

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
