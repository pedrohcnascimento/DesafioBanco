package org.phcn.infrastructure.persistency;

import org.phcn.domain.entitys.Conta;
import org.phcn.domain.repository.ContaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContaRespositoryImpl implements ContaRepository {
    private List<Conta> contas = new ArrayList<>();

    @Override
    public void salvar(Conta conta){
        contas.add(conta);
    }

    @Override
    public List<Conta> listar(){
        return new ArrayList<>(contas);
    }

    @Override
    public Optional<Conta> buscarPorCpf(String cpfTitular){
        return contas.stream()
                .filter(conta -> conta.getCpf().equals(cpfTitular))
                .filter(Conta::isStatus)
                .findFirst();
    }

    @Override
    public Optional<Conta> buscarPorChavePix(String chavePix){
        return contas.stream()
                .filter(conta -> conta.getChavePix().equals(chavePix))
                .filter(Conta::isStatus)
                .findFirst();
    }

    @Override
    public Optional<Conta> fecharConta(String cpfTitular){
        Optional<Conta> contaOpt = contas.stream()
                .filter(conta -> conta.getCpf().equals(cpfTitular))
                .filter(Conta::isStatus)
                .findFirst();

        contaOpt.ifPresent(Conta::desativar);

        return contaOpt;
    }
}
