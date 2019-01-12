package com;

import com.config.MvcConfiguration;
import com.entities.Customer;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import java.sql.SQLException;

public class testJDBC {

    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(MvcConfiguration.class);
        ComboPooledDataSource comboPooledDataSource = context.getBean("myDataSource",ComboPooledDataSource.class);
        try {
            comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
