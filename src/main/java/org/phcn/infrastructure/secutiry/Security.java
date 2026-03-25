package org.phcn.infrastructure.secutiry;

import org.phcn.domain.repository.ContaRepository;

public class Security {
    private final ContaRepository contaRepository;

    public Security(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }
    public boolean verificarInformacoes(String cpfTitular, String senha) {
        return contaRepository.buscarPorCpf(cpfTitular)
                .map(conta -> conta.getSenha().equals(senha))
                .orElse(false);
    }
}
