package org.phcn.domain.entitys;

public class Salario extends Conta{
    @Override
    public void fazerSaque(double valor) {
        if (saldoAtual >= valor) {
            saldoAtual -= valor;
        } else {
            throw new RuntimeException("Saldo insuficiente.");
        }
    }
}
