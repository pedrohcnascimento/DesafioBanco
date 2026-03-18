package org.phcn.domain.entitys;

public abstract class Conta {
    long idConta;
    double saldoAtual;
    double limite;
    String chavePix;
    String nome;
    String cpf;
    boolean status;
    String senha;


    public Conta(long idConta, double saldoAtual, double limite, String chavePix, String nome, String cpf, boolean status, String senha) {
        this.idConta = idConta;
        this.saldoAtual = saldoAtual;
        this.limite = limite;
        this.chavePix = chavePix;
        this.nome = nome;
        this.cpf = cpf;
        this.status = status;
        this.senha = senha;
    }

    public Conta() {
    }

    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void desativar() {
        this.status = false;
    }
}
