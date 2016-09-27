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

    public static void update(Usuario user) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.update(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
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
            user = (Usuario) sess.load(Usuario.class, id);
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

    public static List<Rol> listRol() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Rol> rols = new ArrayList();
        try {
            tx = sess.beginTransaction();
            rols = sess.createQuery("from Rol").list();
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

    public static Rol readRol(int idrol) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Rol rol = new Rol();
        try {
            tx = sess.beginTransaction();
            rol = (Rol) sess.load(Rol.class, idrol);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return rol;
    }

    public static boolean existeName(String name) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        boolean existe = false;
        try {
            tx = sess.beginTransaction();
            List<Object> dev = sess.createQuery("select id from Usuario where username = :user_name")
                    .setParameter("user_name", name).list();
            if (!dev.isEmpty()) {
                existe = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return existe;
    }

    public static boolean existeEmail(String email) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        boolean existe = false;
        try {
            tx = sess.beginTransaction();
            List<Object> dev = sess.createQuery("select id from Usuario where email = :user_mail")
                    .setParameter("user_mail", email).list();
            if (!dev.isEmpty()) {
                existe = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return existe;
    }

    public static List<Usuario> listOneUser(String name) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Usuario> users = new ArrayList();
        try {
            tx = sess.beginTransaction();
            users = sess.createQuery("from Usuario where username = :user_name")
                    .setParameter("user_name", name).list();
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

    public static boolean existeNameNotme(String name, int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        boolean existe = false;
        try {
            tx = sess.beginTransaction();
            List<Object> dev = sess.createQuery("select id from Usuario where username = :user_name and id <> :id")
                    .setParameter("user_name", name)
                    .setParameter("id", id)
                    .list();
            if (!dev.isEmpty()) {
                existe = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return existe;
    }

    public static boolean existeEmailNotme(String email, int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        boolean existe = false;
        try {
            tx = sess.beginTransaction();
            List<Object> dev = sess.createQuery("select id from Usuario where email = :user_mail and id <> :id")
                    .setParameter("user_mail", email)
                    .setParameter("id", id)
                    .list();
            if (!dev.isEmpty()) {
                existe = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return existe;
    }
}
