package br.com.beep.model;

import br.com.beep.model.UsuarioModel.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import br.com.beep.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
public class Beep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    private Produto produto;

    @Column(name = "dataHora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Beep() {
        this.dataHora = LocalDateTime.now();
        this.status = Status.PENDENTE;
    }

    public Beep(Usuario usuario, Produto produto) {
        this.usuario = usuario;
        this.produto = produto;
        this.dataHora = LocalDateTime.now();
        this.status = Status.PENDENTE;

        // Regra de negócio: Atualiza o estoque do produto
        if (produto.getQuantidadeEstoque() > 0) {
            produto.atualizarQuantidade(1); // Diminuir 1 unidade do estoque

            // Atualiza o produto no banco de dados
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                session.merge(produto); // Atualiza o produto no banco de dados
                transaction.commit(); // Confirma as alterações no banco de dados
            } catch (Exception e) {
                if (transaction != null) transaction.rollback(); // Rollback em caso de erro
                e.printStackTrace();
            } finally {
                session.close();
            }
        } else {
            throw new IllegalArgumentException("Estoque insuficiente para bepar o produto.");
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Métodos adicionais
    public void concluirCompra() {
        this.status = Status.CONCLUIDO;
    }
}
