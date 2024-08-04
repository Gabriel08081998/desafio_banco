# Desafio Dio Banco

Este projeto é uma aplicação de gerenciamento bancário desenvolvida com Spring Boot e Hibernate. O sistema inclui funcionalidades para gerenciamento de clientes, contas e transações.

## Estrutura do Projeto

- **Controller**: Camada responsável por expor as APIs REST.
    - `ClienteController`: Gerencia operações relacionadas a clientes.
    - `ContaController`: Gerencia operações relacionadas a contas.
    - `TransacoesController`: Gerencia operações relacionadas a transações.

- **Model**: Representa as entidades do banco de dados.
    - `Cliente`: Entidade cliente.
    - `Conta`: Entidade conta bancária.
    - `Transacoes`: Entidade transações.

- **Repository**: Interfaces para acesso ao banco de dados.
    - `ClienteRepository`: Repositório para a entidade Cliente.
    - `ContaRepository`: Repositório para a entidade Conta.
    - `TransacoesRepository`: Repositório para a entidade Transações.

- **Service**: Camada de lógica de negócio.
    - `ClienteService`: Interface para operações de cliente.
    - `ContaService`: Interface para operações de conta.
    - `TransacoesService`: Interface para operações de transações.

- **Util**: Utilitários diversos.
    - `UtilCriptografia`: Classe para criptografia de senhas.

- **View**: Objetos de transferência de dados (DTOs).
    - `ClienteDTO`: DTO para informações de clientes.
    - `ContaDTO`: DTO para informações de contas.
    - `TransacoesDTO`: DTO para informações de transações.
    - `ClienteException`: Exceção personalizada para clientes.

## Configuração do Projeto

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/usuario/desafio-dio-banco.git