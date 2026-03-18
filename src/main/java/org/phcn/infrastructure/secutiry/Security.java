package org.phcn.infrastructure.secutiry;

import org.phcn.domain.entitys.Conta;
import org.phcn.presentation.texts.Respostas;

import static org.phcn.application.services.ContaService.contas;

public class Security {
    public boolean verificarInformacoes(String cpfTitular, String senha){
        for (Conta conta: contas){
            if (conta.isStatus() && conta.getCpf().equals(cpfTitular)){
                if (conta.getSenha().equals(senha)){
                    return true;
                }else {
                    System.out.println("Senha incorreta!");
                    return false;
                }
            }else {
                System.out.println(Respostas.contaNaoEncontradaOuFechada);
                return false;
            }
        }
        return false;
    }
}
