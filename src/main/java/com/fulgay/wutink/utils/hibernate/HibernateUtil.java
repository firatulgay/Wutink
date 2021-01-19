package com.fulgay.wutink.utils.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * HibernateUtil
 *
 * @author Fırat ÜLGAY
 * @since 5.230.0
 */
public class HibernateUtil {

    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {

            Configuration cfg = new Configuration();
            SessionFactory sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();

            return sessionFactory;

        }catch (Exception e){
            System.out.println("Session factory oluşturulurken hata oluştu");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
