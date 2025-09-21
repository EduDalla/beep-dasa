package br.com.beep.model;

import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "preco")
    private double preco;

    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    // Construtores
    public Produto() {}

    public Produto(String nome, double preco, int quantidadeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Métodos adicionais
    public void atualizarQuantidade(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEstoque -= quantidade;
        } else {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
    }

    public void aumentarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEstoque += quantidade;
        } else {
            throw new IllegalArgumentException("A quantidade para aumento não pode ser negativa.");
        }
    }
}
