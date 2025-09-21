package br.com.beep.service;

import br.com.beep.model.Beep;
import br.com.beep.model.Produto;
import br.com.beep.model.UsuarioModel.Usuario;
import br.com.beep.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EntradaSaidaProdutosBepados {

    public void registrarEntradaProduto(Usuario usuario, Produto produto) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Se o usuário for transiente, salva o usuário antes de persistir o Beep
            if (usuario.getId() == null) {
                session.persist(usuario);  // Salva o usuário no banco de dados
            }

            // Criação do Beep
            Beep beep = new Beep(usuario, produto);
            session.persist(beep);  // Persistir o Beep

            transaction.commit();
            System.out.println("Produto " + produto.getNome() + " beparado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
