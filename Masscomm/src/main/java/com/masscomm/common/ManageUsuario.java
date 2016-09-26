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

    public static int save(Usuario user, RolesUsuarios role) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Integer ok = -1;
        try {
            tx = sess.beginTransaction();
            ok = (Integer) sess.save(user);
            sess.save(role);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            ok = -1;
            throw e;
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
            throw e;
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
            throw e;
        } finally {
            sess.close();
        }
    }

    public static List<Usuario> list() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Usuario> users = new ArrayList();
        try {
            tx = sess.beginTransaction();
            users = sess.createQuery("from Usuario").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
        return users;
    }

    public static Usuario read(String username) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Usuario user = new Usuario();
        try {
            tx = sess.beginTransaction();
            user = (Usuario) sess.get(Usuario.class, username);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
        return user;
    }

    public static List getRol(String usr) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List rols = new ArrayList();
        try {
            tx = sess.beginTransaction();

            String consulta = "SELECT U.rol FROM RolesUsuarios U WHERE U.username = :user_name";

            rols = sess.createQuery(consulta)
                    .setParameter("user_name", usr)
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return rols;
    }
}
