package org.phcn.domain.entitys;

import org.phcn.domain.TipoConta;
import org.phcn.presentation.texts.Respostas;

public class Corrente extends Conta{
    public Corrente(long idConta, String nome, String cpf, String chavePix, double saldoAtual, double limite, boolean status, String senha, TipoConta tipo) {
        super(idConta,nome,cpf,chavePix,saldoAtual,limite,status,senha, tipo);
    }

    public Corrente() {
        super();
        this.tipo = TipoConta.CORRENTE;
    }

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

    @Override
    public String toJson(){
        return super.toJson();
    }
}
