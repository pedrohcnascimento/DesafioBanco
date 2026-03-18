package org.phcn.domain.repository;

import org.phcn.domain.entitys.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaRepository {
    void salvar(Conta conta);
    List<Conta> listar();
    Optional<Conta> buscarPorCpf(String cpfTitular);
    Optional<Conta> buscarPorChavePix(String chavePix);
    Optional<Conta> fecharConta(String cpfTitular);

}
