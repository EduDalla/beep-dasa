# Beep - Sistema de Monitoramento de Estoque para Hospitais

![WhatsApp Image 2025-08-21 at 20 56 55_bb559d96](https://github.com/user-attachments/assets/5af38c5a-500c-4380-aecf-ee2efe922ec0)

---

* **Solução:**

  * **Beep**: Sistema de Monitoramento de Estoque para Hospitais

## Sumário

1. [Objetivo e Escopo do Projeto](#objetivo-e-escopo-do-projeto)
2. [Funcionalidades](#funcionalidades)
3. [Protótipo e Interação](#protótipo-e-interação)
4. [Modelo de Banco de Dados](#modelo-de-banco-de-dados)
5. [Diagrama de Classes](#diagrama-de-classes)
6. [Como Executar](#como-executar)
7. [Tecnologias Utilizadas](#tecnologias-utilizadas)

---

## Objetivo e Escopo do Projeto

O **Beep** é um sistema desenvolvido para **monitoramento de estoque de produtos em hospitais**, permitindo a **gestão eficiente** e a **automação** de registros de entradas e saídas de produtos, como medicamentos, materiais médicos e outros itens essenciais.

### Escopo da Solução:

* **Cadastro de Produtos**: Inserção de produtos com nome, preço e quantidade em estoque.
* **Registro de Entrada (Bepar)**: O usuário registra a entrada de produtos no sistema, diminuindo automaticamente o estoque.
* **Consulta de Estoque**: O sistema permite consultar o estoque de qualquer produto.
* **Controle de Usuários**: Implementação de autenticação e controle de acesso para garantir que apenas usuários autorizados possam registrar entradas de produtos.
* **Testes Automatizados**: Testes unitários e de integração foram implementados para garantir a integridade do sistema e o correto funcionamento das funcionalidades.

---

## Funcionalidades

* **Cadastro de Produtos**: O sistema permite que os usuários cadastrem produtos no sistema com informações como nome, preço e quantidade em estoque.

* **Registro de Entrada de Produto (Bepar)**: Ao "bepar" um produto, o sistema diminui automaticamente 1 unidade do estoque do produto registrado, garantindo controle de estoque em tempo real.

* **Consulta de Estoque**: O usuário pode consultar a quantidade disponível de cada produto no estoque para saber a quantidade atualizada.

* **Cadastro e Login de Usuários**: Usuários podem ser registrados e fazer login para realizar o registro de entradas de produtos.

* **Testes Automatizados**: A solução conta com testes de **BDD (Behavior Driven Development)** e testes unitários para garantir que as funcionalidades estão funcionando corretamente e sem afetar o banco de dados real durante os testes.

---

## Protótipo e Interação

O protótipo do sistema foi desenvolvido em um formato de **console interativo**. A interação com o usuário acontece através de um menu simples, com as seguintes opções:

### 1. Tela Inicial

O menu principal do sistema:

```
*****************************
Bem-vindo ao sistema Beep!
Escolha uma opção:
1. Criar um Novo Usuário
2. Fazer Login
Digite a opção desejada:
```

### 2. Cadastro de Usuário

O usuário informa seu nome e a data de nascimento:

```
Digite o nome do novo usuário: Amanda
Digite a data de nascimento do usuário (formato: dd/MM/yyyy): 11/10/2005
```

### 3. Cadastro de Produto

Menu para o cadastro de produtos:

```
1. Cadastrar Produto
2. Registrar Entrada de Produto (Bepar)
3. Consultar Estoque de Produto
4. Sair
```

Para cadastrar um produto, o usuário informa o nome, preço e a quantidade:

```
Digite o nome do produto: Máscara
Digite o preço do produto: 19.99
Digite a quantidade em estoque: 100
```

### 4. Registrar Bepar

O usuário seleciona o produto a ser registrado. O sistema diminui automaticamente 1 unidade do estoque.

### 5. Consultar Estoque

O sistema exibe a quantidade disponível de um produto específico:

```
Produto: Máscara
Estoque disponível: 99
```

**Observação:** O protótipo foi desenvolvido inicialmente para ser executado via **console**, mas a interface pode ser adaptada para uma interface gráfica posteriormente.

---

## Modelo de Banco de Dados

O modelo do banco de dados segue o diagrama de classes, e as entidades principais incluem:

* **Produto**: Tabela para armazenar informações sobre os produtos (nome, preço, quantidade).
* **Usuário**: Tabela para armazenar informações de login e detalhes do usuário.
* **Beep**: Tabela para registrar a entrada de produtos no sistema, associando um produto a um usuário.

**Entidades Principais:**

* Produto: `id`, `nome`, `preco`, `quantidade_estoque`
* Usuario: `id`, `nome`, `dataNascimento`
* Beep: `id`, `dataHora`, `idProduto`, `status`, `idUsuario`

---

## Diagrama de Classes

O diagrama de classes atualizado do sistema é apresentado abaixo:

![Diagrama de Classes](A_UML_class_diagram_in_the_image_illustrates_a_sys.png)

---

## Como Executar

### Pré-requisitos:

1. **MySQL**: O banco de dados **MySQL** deve estar instalado e configurado.
2. **Banco de Dados**: O banco de dados **beep** deve ser criado no MySQL. Você pode criar o banco utilizando o comando SQL:

   ```sql
   CREATE DATABASE beep;
   ```
3. **Configuração do Hibernate**: Certifique-se de que o arquivo `hibernate.cfg.xml` esteja corretamente configurado com as credenciais de conexão do banco de dados.

### Passos para rodar o sistema:

1. Clone o repositório do projeto.
2. Importe o projeto em sua IDE favorita (IntelliJ, Eclipse, etc.).
3. Certifique-se de que o banco de dados **`beep`** esteja criado no MySQL e as tabelas sejam criadas automaticamente ao executar a aplicação.
4. Execute a classe `Main` para interagir com o sistema via console.

---

## Tecnologias Utilizadas

* **Hibernate ORM**: Para mapeamento objeto-relacional e persistência de dados.
* **MySQL**: Banco de dados utilizado para armazenar as informações.
* **JUnit 5**: Framework para testes automatizados, incluindo testes unitários e BDD.
* **Cucumber**: Utilizado para testes de integração e comportamento (BDD).
