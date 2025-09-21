import br.com.beep.model.Produto;
import br.com.beep.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoDuplicadoTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCadastrarProdutoDuplicado() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            // Persistir o primeiro produto
            Produto produto1 = new Produto("Máscara", 19.99, 100);
            session.persist(produto1);

            // Tentar persistir o segundo produto com o mesmo nome
            Produto produto2 = new Produto("Máscara", 19.99, 100);

            // Verificar se a exceção de violação de chave única é lançada
            assertThrows(ConstraintViolationException.class, () -> session.persist(produto2));

            tx.rollback();
        }
    }

    @Test
    void testCadastrarProdutoComNomesDiferentes() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            Produto produto1 = new Produto("Máscara", 19.99, 100);
            Produto produto2 = new Produto("Luvas", 5.0, 50);

            assertDoesNotThrow(() -> session.persist(produto1));
            assertDoesNotThrow(() -> session.persist(produto2));

            tx.rollback();
        }
    }

}
