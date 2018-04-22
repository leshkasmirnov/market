package ru.asmirnov.market.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Alexey Smirnov at 21/04/2018
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    private final String hbm2ddlAuto;
    private final String dialect;
    private final String enableLazyLoadNoTrans;

    public HibernateConfig(@Value("${market.hibernate.hbm2ddl_auto}") String hbm2ddlAuto,
                           @Value("${market.hibernate.dialect}") String dialect,
                           @Value("${market.hibernate.enable_lazy_load_no_trans}") String enableLazyLoadNoTrans) {
        this.hbm2ddlAuto = hbm2ddlAuto;
        this.dialect = dialect;
        this.enableLazyLoadNoTrans = enableLazyLoadNoTrans;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("ru.asmirnov.market.db.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans", enableLazyLoadNoTrans);
        return hibernateProperties;
    }
}
