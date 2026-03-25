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
        String tipoStr = extractValue(jsonString, "tipo");
        if (tipoStr == null) {
            System.err.println("Erro: Tipo de conta não encontrado no JSON: " + jsonString);
            return null;
        }

        long idConta = Long.parseLong(Objects.requireNonNull(extractValue(jsonString, "idConta")));
        String nome = extractValue(jsonString, "nome");
        String cpf = extractValue(jsonString, "cpf");
        String senha = extractValue(jsonString, "senha");
        String chavePix = extractValue(jsonString, "chavePix");
        boolean status =  Boolean.parseBoolean(Objects.requireNonNull(extractValue(jsonString, "status")));
        double limite = Double.parseDouble(Objects.requireNonNull(extractValue(jsonString, "saldoAtual")));
        double saldoAtual = Double.parseDouble(Objects.requireNonNull(extractValue(jsonString, "saldoAtual")));

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


    protected static String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int startIndex = json.indexOf(searchKey);
        if (startIndex == -1) return null;

        startIndex += searchKey.length();
        char firstChar = json.charAt(startIndex);

        if (firstChar == '"') { // Valor é uma string
            startIndex++; // Pula a aspa de abertura
            int endIndex = json.indexOf('"', startIndex);
            if (endIndex == -1) return null;
            return json.substring(startIndex, endIndex);
        } else { // Valor é numérico ou booleano
            int endIndex = startIndex;
            while (endIndex < json.length() && (Character.isDigit(json.charAt(endIndex)) || json.charAt(endIndex) == '.' || json.charAt(endIndex) == '-')) {
                endIndex++;
            }
            // Ajuste para encontrar o final do número antes de uma vírgula ou chave de fechamento
            int commaIndex = json.indexOf(',', startIndex);
            int braceIndex = json.indexOf('}', startIndex);

            if (commaIndex != -1 && commaIndex < endIndex) endIndex = commaIndex;
            if (braceIndex != -1 && braceIndex < endIndex) endIndex = braceIndex;

            return json.substring(startIndex, endIndex).trim();
        }
    }
}
