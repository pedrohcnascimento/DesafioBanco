Desafio Banco

Descrição do Projeto

O projeto "Desafio Banco" é uma aplicação de console desenvolvida em Java que simula operações bancárias básicas. Ele permite aos usuários criar diferentes tipos de contas, realizar saques, depósitos, transferências via PIX e gerenciar suas informações bancárias. A aplicação foi projetada com uma arquitetura modular, separando as responsabilidades em camadas de domínio, aplicação, infraestrutura e apresentação.

Funcionalidades

O sistema oferece as seguintes funcionalidades principais:

•
Criação de Contas: Os usuários podem criar contas dos tipos Corrente, Poupança e Salário.

•
Saques: Realizar saques de suas contas, com regras específicas para cada tipo de conta.

•
Depósitos: Efetuar depósitos em suas contas.

•
Transferências PIX: Transferir valores entre contas utilizando chaves PIX, com validações e restrições específicas.

•
Fechamento de Conta: Desativar uma conta existente.

•
Exibição de Informações: Consultar os detalhes de uma conta específica.

•
Listagem de Contas Ativas: Visualizar todas as contas ativas no sistema.

Tipos de Conta e Regras Específicas

O sistema suporta três tipos de conta, cada um com suas particularidades:

Conta Corrente

•
Permite saques que consideram o saldo atual mais um limite de crédito.

Conta Poupança

•
Para realizar um saque, o valor deve ser exatamente igual ao saldo total da conta. Não são permitidos saques parciais.

•
Não permite transferências PIX de saída.

Conta Salário

•
Permite saques apenas se houver saldo suficiente. Lança uma exceção (RuntimeException) se o saldo for insuficiente.

Tecnologias Utilizadas

•
Java 21: Linguagem de programação.

•
Maven: Ferramenta de automação de build e gerenciamento de dependências.

Estrutura do Projeto

O projeto segue uma estrutura de pacotes organizada para separar as responsabilidades:

•
org.phcn.application: Contém os serviços da camada de aplicação (ContaService) e DTOs (ContaDto) para orquestrar as operações e transferir dados entre as camadas.

•
org.phcn.domain: Define as entidades de negócio (Conta, Corrente, Poupanca, Salario), os tipos de conta (TipoConta), interfaces de repositório (ContaRepository) e serviços de domínio (ContaServiceDomain) que encapsulam as regras de negócio.

•
org.phcn.infrastructure: Implementa a persistência de dados (ContaRespositoryImpl) e a lógica de segurança (Security).

•
org.phcn.presentation: Contém a interface de linha de comando (CLI) para interação com o usuário (MenuPrincipal) e classes auxiliares para textos e formatação.

Persistência de Dados

Os dados das contas são persistidos localmente em um arquivo JSON (src/main/resources/contas.json). A implementação do repositório (ContaRespositoryImpl) é responsável por carregar e salvar as informações das contas neste arquivo, mantendo uma lista em memória para as operações durante a execução da aplicação.

Como Executar o Projeto

Para compilar e executar o projeto, siga os passos abaixo:

1.
Pré-requisitos:

•
Java Development Kit (JDK) 21 ou superior instalado.

•
Apache Maven instalado.



2.
Clonar o Repositório:

Bash


git clone https://github.com/pedrohcnascimento/DesafioBanco.git
cd DesafioBanco





3.
Compilar o Projeto:

Bash


mvn clean install





4.
Executar a Aplicação:

Bash


java -jar target/DesafioBanco-1.0-SNAPSHOT.jar



Ou, se estiver executando diretamente do IDE (como IntelliJ IDEA ou Eclipse ), execute a classe org.phcn.Main.



Exemplos de Uso

Ao iniciar a aplicação, um menu principal será exibido no console, permitindo que você escolha as operações desejadas:

Plain Text


========================================
|           MENU PRINCIPAL             |
========================================
| 1. Criar Conta                       |
| 2. Fazer Transferencia               |
| 3. Fazer Saque                       |
| 4. Fazer Deposito                    |
| 5. Fechar Conta                      |
| 6. Exibir Informacoes da Conta       |
| 7. Listar Contas Ativas              |
| 8. Sair                              |
========================================
Digite a opcao desejada:



Siga as instruções no console para interagir com o sistema.

