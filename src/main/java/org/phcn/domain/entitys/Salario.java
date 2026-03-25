package org.phcn.domain.entitys;

import org.phcn.domain.TipoConta;
import org.phcn.presentation.texts.Respostas;

public class Salario extends Conta{
    public Salario(long idConta, String nome, String cpf, String chavePix, double saldoAtual, double limite, boolean status, String senha, TipoConta tipo) {
        super(idConta,nome,cpf,chavePix,saldoAtual,limite,status,senha, tipo);
    }

    public Salario() {
        super();
        this.tipo = TipoConta.SALARIO;
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
    @Override
    public String toJson(){
        return super.toJson();
    }
}
