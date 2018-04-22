package ru.asmirnov.market.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Alexey Smirnov at 21/04/2018
 */
@Configuration
@ComponentScan("ru.asmirnov.market")
public class ApplicationConfig {

    @Bean
    public YamlPropertiesFactoryBean yamlPropertiesFactoryBean() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("market.yml"));
        return yamlPropertiesFactoryBean;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setProperties(yamlPropertiesFactoryBean().getObject());
        return configurer;
    }
}
