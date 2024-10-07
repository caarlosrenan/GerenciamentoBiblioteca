# Sistema de Gerenciamento de Biblioteca

## Descrição
Este projeto é um sistema de gerenciamento de biblioteca que permite o cadastro de livros, autores, membros, e gerencia empréstimos e devoluções.

## Funcionalidades
- Cadastro de livros, autores e membros.
- Empréstimos e devoluções.
- Cálculo de multas por atraso.

## Descrição das Classes

### `Autor`
- **Atributos:**
  - `id`: Identificador único do autor.
  - `nome`: Nome do autor.
  - `nacionalidade`: Nacionalidade do autor.
  - `dataNascimento`: Data de nascimento do autor.
  - `biografia`: Biografia do autor.

- **Métodos:**
  - `getId()`: Retorna o ID do autor.
  - `getNome()`: Retorna o nome do autor.
  - `setNome(String nome)`: Define o nome do autor.
  - Outros métodos getters e setters.

### `Livro`
- **Atributos:**
  - `isbn`: ISBN do livro.
  - `titulo`: Título do livro.
  - `dataPublicacao`: Data de publicação do livro.
  - `genero`: Gênero do livro.
  - `quantidadeDisponivel`: Quantidade de cópias disponíveis do livro.
  - `autor`: Referência ao autor do livro.

- **Métodos:**
  - `getIsbn()`: Retorna o ISBN do livro.
  - `getTitulo()`: Retorna o título do livro.
  - `setTitulo(String titulo)`: Define o título do livro.
  - Outros métodos getters e setters.

### `Membro`
- **Atributos:**
  - `id`: Identificador único do membro.
  - `nome`: Nome do membro.
  - `endereco`: Endereço do membro.
  - `telefone`: Telefone do membro.
  - `email`: E-mail do membro.
  - `dataAssociacao`: Data de associação do membro.

- **Métodos:**
  - `getId()`: Retorna o ID do membro.
  - `getNome()`: Retorna o nome do membro.
  - `setNome(String nome)`: Define o nome do membro.
  - Outros métodos getters e setters.

### `Emprestimo`
- **Atributos:**
  - `id`: Identificador único do empréstimo.
  - `livro`: Referência ao livro emprestado.
  - `membro`: Referência ao membro que realizou o empréstimo.
  - `dataEmprestimo`: Data em que o livro foi emprestado.
  - `dataDevolucao`: Data prevista para devolução.

- **Métodos:**
  - `getId()`: Retorna o ID do empréstimo.
  - `getLivro()`: Retorna o livro emprestado.
  - `getMembro()`: Retorna o membro que realizou o empréstimo.
  - `public BigDecimal calcularMulta()`: Calcula a multa com base nos dias atradados

## Classes de Serviço
    As classes de serviço são responsáveis pela lógica de negócios do Sistema de Gerenciamento de Biblioteca. Cada classe realiza operações específicas relacionadas ao cadastro e à manipulação de dados das entidades do sistema.

### `CadastroMembro`
A classe `CadastroMembro` gerencia as operações de cadastro de membros da biblioteca.

#### Métodos Principais
- **`cadastrarMembro(Membro membro)`**: 
  - Cadastra um novo membro na biblioteca.
  - **Parâmetros**: 
    - `membro`: Objeto da classe `Membro` que contém as informações do novo membro.
  - **Retorno**: 
    - Retorna uma mensagem de confirmação se o cadastro for realizado com sucesso.

- **`buscarMembro(int id)`**: 
  - Busca um membro pelo ID.
  - **Parâmetros**: 
    - `id`: Identificador único do membro.
  - **Retorno**: 
    - Retorna o objeto `Membro` correspondente ao ID informado, ou `null` se não encontrado.

### `CadastroEmprestimo`
A classe `CadastroEmprestimo` gerencia as operações de empréstimo de livros.

#### Métodos Principais
- **`realizarEmprestimo(Emprestimo emprestimo)`**: 
  - Realiza o empréstimo de um livro para um membro.
  - **Parâmetros**: 
    - `emprestimo`: Objeto da classe `Emprestimo` que contém as informações do empréstimo.
  - **Retorno**: 
    - Retorna uma mensagem de confirmação se o empréstimo for realizado com sucesso.

- **`devolverLivro(int id)`**: 
  - Realiza a devolução de um livro emprestado.
  - **Parâmetros**: 
    - `id`: Identificador único do empréstimo a ser devolvido.
  - **Retorno**: 
    - Retorna uma mensagem de confirmação se a devolução for realizada com sucesso.

### `CadastroAutor`
A classe `CadastroAutor` gerencia as operações de cadastro de autores.

#### Métodos Principais
- **`cadastrarAutor(Autor autor)`**: 
  - Cadastra um novo autor no sistema.
  - **Parâmetros**: 
    - `autor`: Objeto da classe `Autor` que contém as informações do novo autor.
  - **Retorno**: 
    - Retorna uma mensagem de confirmação se o cadastro for realizado com sucesso.

- **`buscarAutor(int id)`**: 
  - Busca um autor pelo ID.
  - **Parâmetros**: 
    - `id`: Identificador único do autor.
  - **Retorno**: 
    - Retorna o objeto `Autor` correspondente ao ID informado, ou `null` se não encontrado.

### `CadastroLivro`
A classe `CadastroLivro` gerencia as operações de cadastro de livros.

#### Métodos Principais
- **`cadastrarLivro(Livro livro)`**: 
  - Cadastra um novo livro na biblioteca.
  - **Parâmetros**: 
    - `livro`: Objeto da classe `Livro` que contém as informações do novo livro.
  - **Retorno**: 
    - Retorna uma mensagem de confirmação se o cadastro for realizado com sucesso.

- **`buscarLivro(String isbn)`**: 
  - Busca um livro pelo ISBN.
  - **Parâmetros**: 
    - `isbn`: ISBN do livro.
  - **Retorno**: 
    - Retorna o objeto `Livro` correspondente ao ISBN informado, ou `null` se não encontrado.

## Regras de Negócio
1. Um autor pode ter vários livros.
2. Um membro pode realizar vários empréstimos.
3. As multas são aplicadas em caso de devolução fora do prazo estabelecido.

## Como Executar o Projeto
1. Clone o repositório.
2. Configure o banco de dados MySQL e importe o arquivo de exportação do banco de dados.
3. Compile e execute a aplicação no IntelliJ.