package com.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DatabaseConfiguration {
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource myDataSource() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass(environment.getProperty("database.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        comboPooledDataSource.setJdbcUrl(environment.getProperty("database.jdbcurl"));
        comboPooledDataSource.setUser(environment.getProperty("database.user"));
        comboPooledDataSource.setPassword(environment.getProperty("database.password"));
        comboPooledDataSource.setMinPoolSize(Integer.parseInt(environment.getProperty("database.maxpoolsize")));
        comboPooledDataSource.setMaxPoolSize(Integer.parseInt(environment.getProperty("database.minpoolsize")));
        comboPooledDataSource.setMaxIdleTime(Integer.parseInt(environment.getProperty("database.maxidletime")));

        return comboPooledDataSource;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(myDataSource());
        localSessionFactoryBean.setPackagesToScan(environment.getProperty("dbscanpackage"));
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        hibernateProperties.put("hibernate.connection.characterEncoding", environment.getProperty("hibernate.connection.characterEncoding"));
        hibernateProperties.put("hibernate.connection.useUnicode", environment.getProperty("hibernate.connection.useUnicode"));
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        return localSessionFactoryBean;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory.getObject());
        return hibernateTransactionManager;
    }
}
