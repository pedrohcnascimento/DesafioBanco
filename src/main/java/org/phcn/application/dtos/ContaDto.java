package org.phcn.application.dtos;

import org.phcn.domain.TipoConta;
import org.phcn.domain.entitys.Conta;
import org.phcn.domain.entitys.Corrente;
import org.phcn.domain.entitys.Poupanca;
import org.phcn.domain.entitys.Salario;

public record ContaDto(
        long idConta,
        String nome,
        String cpf,
        String senha,
        String chavePix,
        double saldoAtual,
        String tipoConta
) {
    public static ContaDto toDto(Conta c){
        TipoConta tipo = switch (c){
            case Corrente corrente -> TipoConta.CORRENTE;
            case Poupanca poupanca -> TipoConta.POUPANCA;
            case Salario salario -> TipoConta.SALARIO;
            default -> throw new IllegalArgumentException("Tipo de conta não existente!");
        };
        return new ContaDto(c.getIdConta(), c.getNome(), c.getCpf(), c.getSenha(), c.getChavePix(), c.getSaldoAtual(), tipo.toString());
    }
    public Conta fromDto(){
        Conta conta = switch (tipoConta){
            case "SALARIO" -> new Salario();
            case "CORRENTE" -> new Corrente();
            case "POUPANCA" -> new Poupanca();
            default -> throw new IllegalStateException("Tipo de conta não exitente!" + tipoConta);
        };
        conta.setIdConta(idConta);
        conta.setNome(nome);
        conta.setCpf(cpf);
        conta.setSenha(senha);
        conta.setChavePix(chavePix);
        conta.setSaldoAtual(saldoAtual);
        conta.setStatus(true);
        return conta;
    }
}