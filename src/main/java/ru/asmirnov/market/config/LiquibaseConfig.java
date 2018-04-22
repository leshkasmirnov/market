package ru.asmirnov.market.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Alexey Smirnov at 21/04/2018
 */
@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:ru/asmirnov/market/db/liquibase-changeLog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
