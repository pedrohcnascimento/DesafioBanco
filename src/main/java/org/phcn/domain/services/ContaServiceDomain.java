package org.phcn.domain.services;

import org.phcn.domain.entitys.Conta;
import org.phcn.presentation.texts.Perguntas;
import org.phcn.presentation.texts.Respostas;

import java.util.Scanner;



public class ContaServiceDomain {
    Scanner scanner = new Scanner(System.in);

//    public void fazerTrasnferencia(String cpfTitular){
//        for (Conta conta : contas){
//            if (conta.getCpf().equals(cpfTitular)){
//                System.out.println(Perguntas.qualChavePix);
//                String chavePix =scanner.nextLine();
//                for (Conta contaTransferencia : contas){
//                    if (contaTransferencia.getChavePix().equals(chavePix) && contaTransferencia.isStatus()){
//                        if (conta.getChavePix().equals(contaTransferencia.getChavePix())){
//                            System.out.println("Você não pode transferir dinheiro para a própria conta!\nSeu pilantra ;)");
//                            return;
//                        }
//                        System.out.println(Perguntas.valoraSerTransferido);
//                        double valorTransferido = scanner.nextDouble();
//                        if (conta.getLimite() > valorTransferido){
//                            conta.setSaldoAtual(conta.getSaldoAtual()-valorTransferido);
//                            conta.setLimite(conta.getSaldoAtual());
//                            contaTransferencia.setSaldoAtual(contaTransferencia.getSaldoAtual()+valorTransferido);
//                            contaTransferencia.setLimite(contaTransferencia.getSaldoAtual());
//                            System.out.println(Respostas.respostaTransferencia);
//                        } else {
//                            System.out.println(Respostas.limiteUltrapassado);
//                        }
//                    }else {
//                        if (contas.size() == contaTransferencia.getIdConta()){
//                            System.out.println(Respostas.contaNaoEncontradaOuFechada);
//                        }else {
//                            System.out.println("Procurando...");
//                        }
//                    }
//                }
//            }
//        }

//    }
}
