package com.willvuong.bootstrapper.config;

import co.za.hendricks.dao.DataLoader;
import co.za.hendricks.dao.ProductDAO;
import co.za.hendricks.dao.UserDAO;
import co.za.hendricks.services.BasketService;
import co.za.hendricks.services.ProductService;
import co.za.hendricks.services.UserService;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 11/23/13
 * Time: 12:34 PM
 */
@Configuration
@EnableTransactionManagement
@Import(MetricsConfiguration.class)
public class AppConfiguration {
    
    @Bean
    public Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
        properties.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:onlinestore");
        properties.setProperty("hibernate.connection.autocommit", "true");
        properties.setProperty("hibernate.connection.username", "sa");
        properties.setProperty("hibernate.connection.password", "");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        return properties;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean result = new LocalSessionFactoryBean();
        result.setDataSource(dataSource());
        result.setPackagesToScan(new String[] { "co.za.hendricks"});
        result.setHibernateProperties(getHibernateProperties());
        return result;

    }

    @Bean
    public SessionFactory sessionFactory() {
        return sessionFactoryBean().getObject();
    } 

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager man = new HibernateTransactionManager();
        man.setSessionFactory(sessionFactory());
        return man;
    }
    
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).setName("onlinestore").build();
    }
    
    // Configure DAO
        
    @Bean
    public ProductDAO getProductDAO(){
        return new ProductDAO(sessionFactory());
    }
    
    @Bean
    public UserDAO getUserDAO(){
        return new UserDAO(sessionFactory());
    }
    
    
    // Configure Services
    
    @Bean
    public UserService getUserService(){
        return new UserService();
    }
    
    @Bean
    public ProductService getProductService(){
        return new ProductService();
    }
    
    @Bean
    public BasketService getBasketService(){
        return new BasketService();
    }
    
    @Bean
    public DataLoader getDataLoader(){
        return new DataLoader();
    }
}

    

