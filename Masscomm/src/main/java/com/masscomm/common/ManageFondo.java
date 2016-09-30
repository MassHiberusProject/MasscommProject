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
 * @author claencina
 */
public class ManageFondo {
    
    public static int save(Fondo fondo) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Integer ok = -1;
        try {
            tx = sess.beginTransaction();
            ok = (Integer) sess.save(fondo);
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
    
    public static Object update(Fondo fondo) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Object ok = null;
        try {
            tx = sess.beginTransaction();
            ok = sess.merge(fondo);
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
    
    public static void delete(Fondo fondo) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.delete(fondo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
    }
    
    public static List<Fondo> list() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Fondo> fondos = new ArrayList();
        try {
            tx = sess.beginTransaction();
            fondos = sess.createQuery("from Fondo").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return fondos;
    }
    
    public static Fondo read(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Fondo fondo = new Fondo();
        try {
            tx = sess.beginTransaction();
            fondo = (Fondo) sess.get(Fondo.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return fondo;
    }
}
