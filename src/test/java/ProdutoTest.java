import br.com.beep.model.Produto;
import br.com.beep.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testCadastrarProduto() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            Produto produto = new Produto("Máscara", 19.99, 100);
            session.persist(produto);

            Produto produtoSalvo = session.createQuery(
                            "FROM Produto p WHERE p.nome = :nome", Produto.class)
                    .setParameter("nome", "Máscara")
                    .setMaxResults(1)
                    .uniqueResult();

            assertNotNull(produtoSalvo);
            assertEquals("Máscara", produtoSalvo.getNome());
            assertEquals(100, produtoSalvo.getQuantidadeEstoque());

            tx.rollback();
        }
    }
}
