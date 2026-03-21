package org.phcn.domain.entitys;

public class Corrente extends Conta{
    @Override
    public void fazerSaque(double valor) {
        if (saldoAtual + limite >= valor) {
            saldoAtual -= valor;
        } else {
            throw new RuntimeException("Saldo insuficiente + limite.");
        }
    }
}
