package org.phcn.application.services;

import org.phcn.application.dtos.ContaDto;
import org.phcn.domain.entitys.Conta;
import org.phcn.domain.entitys.Corrente;
import org.phcn.domain.entitys.Poupanca;
import org.phcn.domain.entitys.Salario;
import org.phcn.domain.repository.ContaRepository;
import org.phcn.infrastructure.persistency.ContaRespositoryImpl;
import org.phcn.presentation.texts.Respostas;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ContaService {
    static Scanner scanner = new Scanner(System.in);
    private ContaRepository contaRepository = new ContaRespositoryImpl();

    public void cadastrarConta(ContaDto dto ){

        if (contaRepository.buscarPorCpf(dto.cpf()).isPresent()){
            throw new RuntimeException("Já existe uma conta com este CPF");
        }

        Conta conta = switch (dto.tipoConta()){
            case "CORRENTE" -> new Corrente();
            case "POUPANCA" -> new Poupanca();
            case "SALARIO" -> new Salario();
            default -> throw new RuntimeException(Respostas.opcaoInvalida);
        };

        conta.setIdConta(dto.idConta());
        conta.setNome(dto.nome());
        conta.setCpf(dto.cpf());
        conta.setSenha(dto.senha());
        conta.setChavePix(dto.chavePix());
        conta.setSaldoAtual(dto.saldoAtual());
        conta.setLimite(dto.saldoAtual());
        conta.setStatus(true);

        contaRepository.salvar(conta);
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

    public Optional<ContaDto> buscarPorChavePix(String chavePix){
        return contaRepository.buscarPorChavePix(chavePix)
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
        Conta conta1 = contaRepository.buscarPorCpf(cpfTitular)
                .orElseThrow();
        conta1.fazerSaque(valor);
    }

    public void fazerDeposito(String cpfTitular, double valor){
        Conta conta1 =  contaRepository.buscarPorCpf(cpfTitular)
                .orElseThrow();
        conta1.fazerDeposito(valor);
    }
}
