package com.dzmitry.web_customer_tracker.dao;

import com.dzmitry.web_customer_tracker.entity.Customer;
import com.dzmitry.web_customer_tracker.util.SortUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers(int sortColumn) {

        Session session = sessionFactory.getCurrentSession();

        String sortField;
        switch (sortColumn) {
            case SortUtils.FIRST_NAME:
                sortField = "firstName";
                break;
            case SortUtils.LAST_NAME:
                sortField = "lastName";
                break;
            case SortUtils.EMAIL:
                sortField = "email";
                break;
            default:
                sortField = "lastName";
        }
        Query<Customer> query= session.createQuery("from Customer order by " + sortField, Customer.class);
        List<Customer> customers = query.getResultList();

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("delete from Customer where id = :customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String name) {
        Session session = sessionFactory.getCurrentSession();
        if(name.length() >0 && name.trim().length() > 0) {
            Query<Customer> query = session.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name", Customer.class);
            query.setParameter("name", "%" + name.toLowerCase() + "%");
            return query.getResultList();
        }else {
            return getCustomers(SortUtils.LAST_NAME);
        }

    }
}
