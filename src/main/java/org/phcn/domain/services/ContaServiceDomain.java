package org.phcn.domain.services;

import org.phcn.domain.entitys.Conta;
import org.phcn.domain.entitys.Poupanca;
import org.phcn.domain.repository.ContaRepository;
import org.phcn.presentation.texts.Respostas;

import java.util.Optional;

public class ContaServiceDomain {
    private final ContaRepository contaRepository;

    public ContaServiceDomain(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public void fazerTrasnferencia(String cpfTitular, String chavePix, double valor){
        Optional<Conta> contaDestinoOpt = contaRepository.buscarPorChavePix(chavePix);
        Optional<Conta> contaOrigemOpt = contaRepository.buscarPorCpf(cpfTitular);


        if (contaDestinoOpt.isEmpty()){
            System.out.println(Respostas.contaNaoEncontradaOuFechada);
            return;
        }

        if (contaOrigemOpt.isEmpty()){
            System.out.println(Respostas.contaNaoEncontradaOuFechada);
            return;
        }

        Conta contaDestino = contaDestinoOpt.get();
        Conta contaOrigem = contaOrigemOpt.get();

        if (!(contaOrigem instanceof Poupanca)){
            if (!contaOrigem.getChavePix().equals(chavePix)){
                if (contaOrigem.fazerSaque(valor)){
                    contaDestino.fazerDeposito(valor);

                    contaDestino.setLimite(contaDestino.getSaldoAtual());
                    contaOrigem.setLimite(contaOrigem.getSaldoAtual());

                    contaRepository.salvar(contaOrigem);
                    contaRepository.salvar(contaDestino);
                    System.out.println(Respostas.respostaTransferencia);
                }else {
                    System.out.println("Transferencia fracassada!");
                }
            }else {
                System.out.println("Você não pode transferir pra você mesmo, seu safado!");
            }
        }else {
            System.out.println("Transferencias não são permitidas em contas poupança.");
        }
    }
}
