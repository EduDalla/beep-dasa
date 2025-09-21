package br.com.beep.cliente.DAO;

import br.com.beep.model.UsuarioModel.Regra;
import br.com.beep.model.UsuarioModel.Usuario;
import br.com.beep.model.UsuarioModel.UsuarioRegra;
import br.com.beep.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void criar(Usuario usuario) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Buscar usuário por nome
    public Usuario obterPorNome(String nome) {
        Session session = HibernateUtil.getSession();
        Usuario usuario = null;
        try {
            usuario = session.createQuery("FROM Usuario WHERE nome = :nome", Usuario.class)
                    .setParameter("nome", nome)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuario;
    }

    public Usuario obter(int id) {
        Session session = HibernateUtil.getSession();
        Usuario usuario = null;
        try {
            usuario = session.find(Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuario;
    }

    public void atualizar(Usuario usuario) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void remover(Usuario usuario) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        try {
            usuarios = session.createQuery("FROM Usuario", Usuario.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuarios;
    }

    public void adicionarRegra(Long usuarioId, Long regraId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Usuario usuario = session.find(Usuario.class, usuarioId);
            Regra regra = session.find(Regra.class, regraId);
            UsuarioRegra usuarioRegra = new UsuarioRegra();
            usuarioRegra.setUsuario(usuario);
            usuarioRegra.setRegra(regra);
            usuarioRegra.setDateCreated(LocalDateTime.now());
            usuario.getUsuarioRegras().add(usuarioRegra); // This triggers the cascade
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}