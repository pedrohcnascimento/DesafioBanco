package org.phcn.domain.entitys;

import org.phcn.domain.TipoConta;

import java.util.Objects;

public abstract class Conta {
    protected long idConta;
    protected String nome;
    protected String cpf;
    protected String chavePix;
    protected double saldoAtual;
    protected double limite;
    protected boolean status;
    protected String senha;
    protected TipoConta tipo;

    public Conta(long idConta, String nome, String cpf, String chavePix, double saldoAtual, double limite, boolean status, String senha, TipoConta tipo) {
        this.idConta = idConta;
        this.nome = nome;
        this.cpf = cpf;
        this.chavePix = chavePix;
        this.saldoAtual = saldoAtual;
        this.limite = limite;
        this.status = status;
        this.senha = senha;
        this.tipo = tipo;
    }
    public Conta() {
    }
    public long getIdConta() {
        return idConta;
    }
    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }
    public String getNome() {
        return nome;
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
    public String getChavePix() {
        return chavePix;
    }
    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
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
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public TipoConta getTipo() {
        return tipo;
    }
    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }
    public void desativar() {
        this.status = false;
    }
    public  void fazerDeposito(double valor){
        saldoAtual += valor;
        limite = saldoAtual;
    }
    public abstract boolean fazerSaque(double valor);
    public String toJson() {
        String nomeTipo = (tipo != null) ? tipo.name() : "UNKNOWN";
        return String.format(
                "{\"idConta\":%d, \"nome\":\"%s\", \"cpf\":\"%s\", \"senha\":\"%s\", \"chavePix\":\"%s\", \"saldoAtual\":%.2f, \"tipo\":\"%s\", \"status\":\"%b\"}",
                idConta, nome, cpf, senha, chavePix, saldoAtual, nomeTipo, status
        );
    }
    public static Conta fromJson(String jsonString) {
        String tipoStr = extrairValores(jsonString, "tipo");
        if (tipoStr == null) {
            System.err.println("Erro: Tipo de conta não encontrado no JSON: " + jsonString);
            return null;
        }

        long idConta = Long.parseLong(Objects.requireNonNull(extrairValores(jsonString, "idConta")));
        String nome = extrairValores(jsonString, "nome");
        String cpf = extrairValores(jsonString, "cpf");
        String senha = extrairValores(jsonString, "senha");
        String chavePix = extrairValores(jsonString, "chavePix");
        boolean status =  Boolean.parseBoolean(Objects.requireNonNull(extrairValores(jsonString, "status")));
        double limite = Double.parseDouble(Objects.requireNonNull(extrairValores(jsonString, "saldoAtual")));
        double saldoAtual = Double.parseDouble(Objects.requireNonNull(extrairValores(jsonString, "saldoAtual")));

        TipoConta tipo = TipoConta.valueOf(tipoStr);
        return switch (tipo) {
            case CORRENTE -> new Corrente(idConta, nome, cpf, chavePix,saldoAtual, limite, status, senha, tipo);
            case POUPANCA -> new Poupanca(idConta, nome, cpf, chavePix,saldoAtual, limite, status, senha, tipo);
            case SALARIO -> new Salario(idConta, nome, cpf, chavePix,saldoAtual, limite, status, senha, tipo);
            default -> {
                System.err.println("Tipo de conta desconhecido: " + tipoStr);
                yield null;
            }
        };
    }
    protected static String extrairValores(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int indexInicio = json.indexOf(searchKey);
        if (indexInicio == -1) return null;
        indexInicio += searchKey.length();
        char primeiroChar = json.charAt(indexInicio);

        if (primeiroChar == '"') { // Valor é uma string
            indexInicio++; // Pula a aspa de abertura
            int indexFinal = json.indexOf('"', indexInicio);
            if (indexFinal == -1) return null;
            return json.substring(indexInicio, indexFinal);
        } else { // Valor é numérico ou booleano
            int finalIndex = indexInicio;
            while (finalIndex < json.length() && (Character.isDigit(json.charAt(finalIndex)) || json.charAt(finalIndex) == '.' || json.charAt(finalIndex) == '-')) {
                finalIndex++;
            }
            // Ajuste para encontrar o final do número antes de uma vírgula ou chave de fechamento
            int indexIntervalo = json.indexOf(',', indexInicio);
            int indexFinalClasse = json.indexOf('}', indexInicio);

            if (indexIntervalo != -1 && indexIntervalo < finalIndex) finalIndex = indexIntervalo;
            if (indexFinalClasse != -1 && indexFinalClasse < finalIndex) finalIndex = indexFinalClasse;

            return json.substring(indexInicio, finalIndex).trim();
        }
    }
}
