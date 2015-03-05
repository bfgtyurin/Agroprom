package com.vtyurin.dao;

import com.vtyurin.domain.Seller;
import com.vtyurin.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SellerDaoHibernate implements SellerDao {

    private SessionFactory sessionFactory = HibernateUtil.buildHibernateAnnotationConfigSessionFactory();
    private Session session;

    public SellerDaoHibernate() {
    }

    public SellerDaoHibernate(Session session) {
        this.session = session;
    }

    @Override
    public void addSeller(Seller seller) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(seller);
        transaction.commit();
        session.close();
    }

    @Override
    public Seller getSellerById(Long id) {
//        Session session = sessionFactory.getCurrentSession();

        Seller seller;
        try {
//            session.beginTransaction();
            seller = (Seller) session.get(Seller.class, id);
//            session.getTransaction().commit();
            return seller;

        } catch (HibernateException e) {
            Transaction tx = session.getTransaction();
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        return null;
    }
}
