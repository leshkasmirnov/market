package ru.asmirnov.market.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * Datasource configuration.
 *
 * @author Alexey Smirnov at 21/04/2018
 */
@Configuration
@Import(ApplicationConfig.class)
public class DatasourceConfig {

    private final String url;
    private final String username;
    private final String password;
    private final String connectionTestQuery;
    private final String poolName;
    private final int minIdle;
    private final int maxPoolSize;

    public DatasourceConfig(@Value("${market.datasource.url}") String url,
                            @Value("${market.datasource.username}") String username,
                            @Value("${market.datasource.password}") String password,
                            @Value("${market.datasource.test_query}") String connectionTestQuery,
                            @Value("${market.datasource.pool.name}") String poolName,
                            @Value("${market.datasource.pool.min_idle:3}") int minIdle,
                            @Value("${market.datasource.pool.max_size:10}") int maxPoolSize) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.connectionTestQuery = connectionTestQuery;
        this.poolName = poolName;
        this.minIdle = minIdle;
        this.maxPoolSize = maxPoolSize;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName(poolName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setConnectionTestQuery(connectionTestQuery);
        hikariConfig.setConnectionInitSql(connectionTestQuery);
        hikariConfig.setMinimumIdle(minIdle);
        hikariConfig.setMaximumPoolSize(maxPoolSize);

        return new HikariDataSource(hikariConfig);
    }
}
