package com.DAOImplementations;

import com.DAO.CustomerDAO;
import com.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    // need to inject the session factory
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName", Customer.class);

        // execute query and get result list

        // return the results
        return theQuery.getResultList();
    }

    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Customer.class, id));
    }

    @Override
    public List<Customer> searchCustomers(String name) {
        if (name.isEmpty()) {
            return getCustomers();
        }
        Session session = sessionFactory.getCurrentSession();
        name = name.trim().toLowerCase();
        Query<Customer> query = session.createQuery("from Customer c where lower(lastName) like:name or lower(firstName) like:name", Customer.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
