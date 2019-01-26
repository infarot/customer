package com.dao;

import com.entities.Role;
import com.entities.User;
import com.enums.Roles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveUser(User user){
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public User findByUserName(String username) throws NullPointerException {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where username=:name", User.class);
        query.setParameter("name", username);
        return query.getSingleResult();
    }
}
