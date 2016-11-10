/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

import com.masscomm.persistence.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ManageCumpleanios {

    public static int save(Cumpleanios cumpleanios) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Integer ok = -1;
        try {
            tx = sess.beginTransaction();
            ok = (Integer) sess.save(cumpleanios);
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

    public static Object update(Cumpleanios cumpleanios) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Object ok = null;
        try {
            tx = sess.beginTransaction();
            ok = sess.merge(cumpleanios);
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

    public static void delete(Cumpleanios cumpleanios) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.delete(cumpleanios);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
    }

    public static List<Cumpleanios> list() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Cumpleanios> cumpleanios = new ArrayList();
        try {
            tx = sess.beginTransaction();
            cumpleanios = sess.createQuery("from Cumpleanios").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return cumpleanios;
    }

    public static Cumpleanios read(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Cumpleanios cumpleanios = new Cumpleanios();
        try {
            tx = sess.beginTransaction();
            cumpleanios = (Cumpleanios) sess.get(Cumpleanios.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return cumpleanios;
    }

    public static List<Cumpleanios> listDate(String dia, String mes) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Cumpleanios> cumpleanios = new ArrayList();
        try {
            tx = sess.beginTransaction();
            cumpleanios = (List<Cumpleanios>) sess.createSQLQuery("select * FROM Cumpleanios where DAY( fecha ) = :dia and MONTH(fecha) = :mes")
                    .addEntity(Cumpleanios.class)
                    .setParameter("mes", mes)
                    .setParameter("dia", dia)
                    .list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return cumpleanios;
    }
}
