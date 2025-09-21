package br.com.beep.cliente.DAO;

import br.com.beep.model.Produto;
import br.com.beep.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProdutoDAO {

    // Criar Produto
    public void criar(Produto produto) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(produto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Obter Produto por ID
    public Produto obter(Long id) {
        Session session = HibernateUtil.getSession();
        Produto produto = null;
        try {
            produto = session.find(Produto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return produto;
    }

    // Listar Produtos
    public List<Produto> listar() {
        Session session = HibernateUtil.getSession();
        List<Produto> produtos = null;
        try {
            produtos = session.createQuery("FROM Produto", Produto.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return produtos;
    }

    // Atualizar Produto
    public void atualizar(Produto produto) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(produto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Remover Produto
    public void remover(Produto produto) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(produto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Produto obterPorNome(String nome) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Produto p WHERE p.nome = :nome", Produto.class)
                    .setParameter("nome", nome)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

}
