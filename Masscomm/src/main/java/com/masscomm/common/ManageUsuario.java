/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

import com.masscomm.persistence.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author pmayor
 */
public class ManageUsuario {

    public static int save(Usuario user) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Integer ok = -1;
        try {
            tx = sess.beginTransaction();
            ok = (Integer) sess.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return ok;
    }

    public static Object update(Usuario user) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Object ok = null;
        try {
            tx = sess.beginTransaction();
            ok = sess.merge(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return ok;
    }

    public static void delete(Usuario user) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.delete(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
    }

    public static List list() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List users = new ArrayList();
        try {
            tx = sess.beginTransaction();
            users = sess.createQuery("from Usuario").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return users;
    }

    public static Usuario read(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Usuario user = new Usuario();
        try {
            tx = sess.beginTransaction();
            user = (Usuario) sess.get(Usuario.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return user;
    }

    public static List comprueba(String usr, String pwd) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List users = new ArrayList();
        try {
            tx = sess.beginTransaction();
            
            String consulta = "SELECT U.id FROM Usuario U WHERE U.user = :user_name AND U.password = :user_pass";
            
            users = sess.createQuery(consulta)
                    .setParameter("user_name", usr)
                    .setParameter("user_pass", pwd)
                    .list();
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return users;
    }
}
