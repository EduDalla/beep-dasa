package br.com.beep.cliente;

import br.com.beep.cliente.DAO.UsuarioDAO;
import br.com.beep.model.*;
import br.com.beep.service.EntradaSaidaProdutosBepados;
import br.com.beep.cliente.DAO.ProdutoDAO;
import br.com.beep.model.UsuarioModel.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Criação de objetos necessários
        Scanner scanner = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();  // Instância do DAO de Usuário
        EntradaSaidaProdutosBepados service = new EntradaSaidaProdutosBepados();

        Usuario usuarioLogado = null;  // Variável para armazenar o usuário logado

        // Menu de login ou criação de usuário
        while (usuarioLogado == null) {
            System.out.println("*****************************");
            System.out.println("Bem-vindo ao sistema Beep!");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar um Novo Usuário");
            System.out.println("2. Fazer Login");
            System.out.print("Digite a opção desejada: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha restante do buffer

            switch (opcao) {
                case 1:
                    // Criar novo usuário
                    System.out.print("Digite o nome do novo usuário: ");
                    String nomeNovoUsuario = scanner.nextLine();

                    // Pedir a data de nascimento do usuário
                    String dataNascimento = null;
                    boolean dataValida = false;
                    while (!dataValida) {
                        System.out.print("Digite a data de nascimento do usuário (formato: dd/MM/yyyy): ");
                        dataNascimento = scanner.nextLine();

                        // Validação da data
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate data = LocalDate.parse(dataNascimento, formatter);

                            // Verifica se a data de nascimento é no futuro
                            if (data.isAfter(LocalDate.now())) {
                                System.out.println("Erro: A data de nascimento não pode ser no futuro. Tente novamente.");
                            } else {
                                dataValida = true;  // A data é válida
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Erro: Data inválida. Por favor, use o formato correto (dd/MM/yyyy).");
                        }
                    }

                    // Cria o novo usuário com a data de nascimento válida
                    Usuario novoUsuario = new Usuario();
                    novoUsuario.setNome(nomeNovoUsuario);
                    novoUsuario.setDataNascimento(dataNascimento);

                    usuarioDAO.criar(novoUsuario);  // Salvar o novo usuário no banco
                    usuarioLogado = novoUsuario;  // Logar automaticamente após criar
                    System.out.println("Usuário criado com sucesso e logado!\n");
                    break;


                case 2:
                    // Login de usuário
                    System.out.print("Digite o nome do usuário para login: ");
                    String nomeUsuarioLogin = scanner.nextLine();

                    // Buscar o usuário no banco
                    Usuario usuario = usuarioDAO.obterPorNome(nomeUsuarioLogin);

                    if (usuario != null) {
                        usuarioLogado = usuario;
                        System.out.println("Login bem-sucedido! Bem-vindo " + usuarioLogado.getNome() + "\n");
                    } else {
                        System.out.println("Usuário não encontrado. Tente novamente ou crie uma conta.\n");
                    }
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }
        }

        // Menu principal após login
        while (true) {
            System.out.println("*****************************");
            System.out.println("Bem-vindo ao sistema Beep, " + usuarioLogado.getNome() + "!");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Registrar Entrada de Produto (Bepar)");
            System.out.println("3. Consultar Estoque de Produto");
            System.out.println("4. Sair");
            System.out.print("Digite a opção desejada: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha restante do buffer

            switch (opcao) {
                case 1:
                    // Cadastrar Produto
                    System.out.print("Digite o nome do produto: ");
                    String nomeProduto = scanner.nextLine();

                    System.out.print("Digite o preço do produto: ");
                    double precoProduto = scanner.nextDouble();

                    System.out.print("Digite a quantidade em estoque do produto: ");
                    int estoqueProduto = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha restante

                    Produto produto = new Produto(nomeProduto, precoProduto, estoqueProduto);
                    produtoDAO.criar(produto);  // Persistir produto
                    System.out.println("Produto cadastrado com sucesso!\n");
                    break;

                case 2:
                    // Registrar Entrada de Produto (Bepar)
                    System.out.print("Digite o nome do produto para registrar: ");
                    String nomeProdutoParaBepar = scanner.nextLine();

                    Produto produtoParaBepar = produtoDAO.obterPorNome(nomeProdutoParaBepar);
                    if (produtoParaBepar != null) {
                        try {
                            // Registrar Beep
                            service.registrarEntradaProduto(usuarioLogado, produtoParaBepar);
                            System.out.println("Produto " + produtoParaBepar.getNome() + " beparado com sucesso!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Produto não encontrado!\n");
                    }
                    break;

                case 3:
                    // Consultar Estoque
                    System.out.print("Digite o nome do produto para consultar o estoque: ");
                    String nomeProdutoConsulta = scanner.nextLine();

                    Produto produtoConsulta = produtoDAO.obterPorNome(nomeProdutoConsulta);
                    if (produtoConsulta != null) {
                        System.out.println("Produto: " + produtoConsulta.getNome());
                        System.out.println("Estoque disponível: " + produtoConsulta.getQuantidadeEstoque() + "\n");
                    } else {
                        System.out.println("Produto não encontrado!\n");
                    }
                    break;

                case 4:
                    // Sair
                    System.out.println("Saindo... Até logo!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }
        }
    }
}
