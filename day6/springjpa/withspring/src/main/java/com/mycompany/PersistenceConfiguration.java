package com.mycompany;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan("com.mycompany")
@Configuration
public class PersistenceConfiguration{

    private static final Logger Log= LoggerFactory.getLogger(PersistenceConfiguration.class);

    @Autowired
    private ApplicationContext applicationContext;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.mycompany");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(ormProperties());
        return em;
    }


    @Bean
    public DataSource dataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            Environment environment = applicationContext.getEnvironment();
            String driver = environment.getProperty("spring.datasource.driver-class-name");
            dataSource.setDriverClass(driver);
            String url = environment.getProperty("spring.datasource.url");
            dataSource.setJdbcUrl(url);
            String user = environment.getProperty("spring.datasource.username");
            dataSource.setUser(user);
            String password = environment.getProperty("spring.datasource.password");
            dataSource.setPassword(password);
            return dataSource;
        } catch (Throwable e) {
            Log.error("exception in dataSource()", e);
            return null;
        }
    }

    Properties ormProperties() {
        Properties properties = new Properties();
        Environment environment = applicationContext.getEnvironment();
        String dialect = environment.getProperty("spring.jpa.properties.hibernate.dialect");
        String ddlAuto = environment.getProperty("spring.jpa.hibernate.ddl-auto");
       // String showSql = environment.getProperty("hibernate.show_sql");
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
       // properties.setProperty("hibernate.show_sql", showSql);
        //  properties.setProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation", "true");
        return properties;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }






}
