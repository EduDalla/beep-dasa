package br.com.beep.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Cria a SessionFactory a partir do arquivo de configuração hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();  // Carrega o hibernate.cfg.xml
        } catch (Exception e) {
            System.err.println("Falha ao criar SessionFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    // Retorna a SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Fecha a SessionFactory
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    // Retorna uma sessão do Hibernate
    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
