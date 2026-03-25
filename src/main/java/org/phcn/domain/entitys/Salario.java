package org.phcn.domain.entitys;

import org.phcn.domain.TipoConta;
import org.phcn.presentation.texts.Respostas;

public class Salario extends Conta{
    public Salario(long idConta, String nome, String cpf, String chavePix, double saldoAtual, double limite, boolean status, String senha, TipoConta tipo) {
    }

    public Salario() {
    }

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
