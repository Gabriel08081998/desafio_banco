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

# Requirements

Para buildar e rodar você precisa:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6.3](https://maven.apache.org)

- # Rodando a aplicação localmente

Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método `main` na classe `com.desafio.DesafioDioBanco` de seu IDE.

Alternativamente, você pode usar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

# Direito autoral

Lançado sob o Apache License 2.0. Ver o [LICENSE](https://github.com/Gabriel08081998/desafio_banco) arquivo.

