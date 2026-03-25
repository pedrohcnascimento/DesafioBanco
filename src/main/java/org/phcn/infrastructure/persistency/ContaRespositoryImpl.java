package org.phcn.infrastructure.persistency;

import org.phcn.domain.entitys.Conta;
import org.phcn.domain.repository.ContaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContaRespositoryImpl implements ContaRepository {
    private static final String PATH = "src/main/resources/contas.json";
    private List<Conta> contas;
    private long proximoIdConta;

    public ContaRespositoryImpl(){
        this.contas =new ArrayList<>();
        this.proximoIdConta = 1L;
        carregarContas();
    }

    private void carregarContas(){
        Path path = Paths.get(PATH);
        if (Files.exists(path)){
            try {
                String conteudoJson = Files.readString(path);

                if (conteudoJson.trim().isEmpty() || conteudoJson.equals("[]")){
                    this.contas = new ArrayList<>();
                    this.proximoIdConta = 1L;
                    return;
                }

                String jsonInterno = conteudoJson.substring(1, conteudoJson.length() -1);
                String[] listaClassesJson = jsonInterno.split("}(,)\\s*\\{?");

                for (String classeJson: listaClassesJson){
                    if (!classeJson.trim().isEmpty()){
                        String classeCompletaJson = classeJson.trim();

                        if (!classeCompletaJson.startsWith("{")){
                            classeCompletaJson = "{" + classeCompletaJson;
                        }
                        if (!classeCompletaJson.endsWith("}")){
                            classeCompletaJson = classeCompletaJson + "}";
                        }

                        try {
                            Conta conta = Conta.fromJson(classeCompletaJson);
                            if (conta != null){
                                this.contas.add(conta);
                                if (conta.getIdConta() >= proximoIdConta){
                                    proximoIdConta = conta.getIdConta()+1;
                                }
                            }
                        }catch (Exception e){
                            System.err.println("Erro ao desserializar conta: "+classeCompletaJson+"-"+e.getMessage());
                        }
                    }
                }
            }catch (IOException e){
                System.err.println("Erro ao carregar contas do arquivo: "+ e.getMessage());
            }
        }
    }

    private void salvarContas(){
        Path path = Paths.get(PATH);

        try {
            String conteudoJson = "["+
                contas.stream()
                        .map(Conta::toJson)
                        .collect(Collectors.joining(",\n"))+
                    "]";
            Files.writeString(path, conteudoJson);
        }catch (IOException e){
            System.err.println("Erro ao salvar contas no arquivo: " + e.getMessage());
        }
    }

    @Override
    public void salvar(Conta conta){
        Optional<Conta> existingConta = contas.stream()
                .filter(c -> c.getCpf().equals(conta.getCpf()))
                .findFirst();

        if (existingConta.isPresent()) {
            Conta c = existingConta.get();
            c.setNome(conta.getNome());
            c.setSenha(conta.getSenha());
            c.setChavePix(conta.getChavePix());
            c.setSaldoAtual(conta.getSaldoAtual());
            c.setTipo(conta.getTipo());
        } else {
            conta.setIdConta(proximoIdConta++);
            this.contas.add(conta);
        }
        salvarContas();
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
        Optional<Conta> contaOpt = buscarPorCpf(cpfTitular);

        contaOpt.ifPresent(conta -> {
            conta.desativar();
            salvar(conta);
        });
        return contaOpt;
    }
}
