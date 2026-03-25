package org.phcn.domain.entitys;

public class Poupanca extends Conta{
    @Override
    public void fazerSaque(double valor) {
        if (saldoAtual == valor) {
            saldoAtual -= valor;
        } else if (saldoAtual > 0){
            throw new RuntimeException("Contas poupanças devem retirar tudo quando sacadas.");
        }else {
            throw new RuntimeException("Saldo insuficiente.");
        }
    }
}
