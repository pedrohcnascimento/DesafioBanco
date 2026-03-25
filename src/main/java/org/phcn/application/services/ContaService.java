package org.phcn.application.services;

import org.phcn.application.dtos.ContaDto;
import org.phcn.domain.TipoConta;
import org.phcn.domain.entitys.Conta;
import org.phcn.domain.entitys.Corrente;
import org.phcn.domain.entitys.Poupanca;
import org.phcn.domain.entitys.Salario;
import org.phcn.domain.repository.ContaRepository;

import java.util.List;
import java.util.Optional;

public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public void cadastrarConta(ContaDto dto ){

        if (contaRepository.buscarPorCpf(dto.cpf()).isPresent()){
            throw new RuntimeException("Já existe uma conta com este CPF");
        }
        Conta contaNova;
        TipoConta tipo = TipoConta.valueOf(dto.tipoConta());

        switch (tipo){
            case CORRENTE :
                contaNova = new Corrente(dto.idConta(),dto.nome(), dto.cpf(), dto.chavePix(), dto.saldoAtual(), dto.saldoAtual(), true, dto.senha(), tipo);
                break;
            case POUPANCA:
                contaNova = new Poupanca(dto.idConta(),dto.nome(), dto.cpf(), dto.chavePix(), dto.saldoAtual(), dto.saldoAtual(), true, dto.senha(), tipo);
                break;
            case SALARIO:
                contaNova = new Salario(dto.idConta(),dto.nome(), dto.cpf(), dto.chavePix(), dto.saldoAtual(), dto.saldoAtual(), true, dto.senha(), tipo);
                break;
            default:
                System.err.println("Tipo de conta inválido");
                return;
        }

        contaRepository.salvar(contaNova);
        buscarPorCpf(contaNova.getCpf());
    }

    public List<Conta> listarAtivos(){
        return contaRepository.listar()
                .stream().filter(Conta::isStatus)
                .toList();
    }

    public Optional<ContaDto> buscarPorCpf(String cpfTitular){
        return contaRepository.buscarPorCpf(cpfTitular)
                .filter(Conta::isStatus)
                .map(ContaDto::toDto);
    }


    public boolean fecharConta(String cpfTitular){
        return contaRepository.buscarPorCpf(cpfTitular).map(conta1 -> {
            conta1.setStatus(false);
            contaRepository.salvar(conta1);
            return true;
        }).orElse(false);
    }

    public void fazerSaque(String cpfTitular, double valor){
        Conta conta = contaRepository.buscarPorCpf(cpfTitular)
                .orElseThrow();
        conta.fazerSaque(valor);
    }

    public void fazerDeposito(String cpfTitular, double valor){
        Conta conta =  contaRepository.buscarPorCpf(cpfTitular)
                .orElseThrow();
        conta.fazerDeposito(valor);
    }
}
