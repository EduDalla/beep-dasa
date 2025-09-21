import br.com.beep.cliente.DAO.ProdutoDAO;
import br.com.beep.model.Produto;
import br.com.beep.model.UsuarioModel.Usuario;
import br.com.beep.service.EntradaSaidaProdutosBepados;
import br.com.beep.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeepTest {

    private ProdutoDAO produtoDAO;
    private EntradaSaidaProdutosBepados service;

    @BeforeEach
    void setUp() {
        produtoDAO = new ProdutoDAO();
        service = new EntradaSaidaProdutosBepados();
    }

    @Test
    void testRegistrarEntradaProdutoDiminuirEstoque() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            Usuario usuario = new Usuario();
            usuario.setNome("João Silva");

            Produto produto = new Produto("Máscara", 19.99, 100);
            session.persist(produto);

            service.registrarEntradaProduto(usuario, produto);

            Produto produtoAtualizado = session.createQuery(
                            "FROM Produto p WHERE p.nome = :nome", Produto.class)
                    .setParameter("nome", "Máscara")
                    .setMaxResults(1)
                    .uniqueResult();

            assertEquals(99, produtoAtualizado.getQuantidadeEstoque());

            tx.rollback();
        }
    }

    @Test
    void testRegistrarEntradaProdutoQuantidadeMúltipla() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            Usuario usuario = new Usuario();
            usuario.setNome("Pedro");

            Produto produto = new Produto("Luvas", 5.0, 10);
            session.persist(produto);

            service.registrarEntradaProduto(usuario, produto);
            service.registrarEntradaProduto(usuario, produto);
            service.registrarEntradaProduto(usuario, produto);

            Produto atualizado = session.createQuery(
                            "FROM Produto p WHERE p.nome = :nome", Produto.class)
                    .setParameter("nome", "Luvas")
                    .setMaxResults(1)
                    .uniqueResult();

            assertEquals(7, atualizado.getQuantidadeEstoque());

            tx.rollback();
        }
    }

    @Test
    void testProdutoNaoNuloAoBepar() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            Usuario usuario = new Usuario();
            usuario.setNome("Ana");

            Produto produto = new Produto("Máscara", 19.99, 5);
            session.persist(produto);

            assertDoesNotThrow(() -> service.registrarEntradaProduto(usuario, produto));

            tx.rollback();
        }
    }
}
