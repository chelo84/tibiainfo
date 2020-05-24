package com.tibiainfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    Environment env;

    @Bean
    @Qualifier("default")
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        return dataSource;
    }

    @Bean
    @Primary
    @Scope("singleton")
    public AbstractRoutingDataSource abstractRoutingDataSource(@Autowired @Qualifier("default") DataSource dataSource) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.addDataSource(RoutingDataSource.DEFAULT, dataSource);
        routingDataSource.setDefaultTargetDataSource(dataSource);
        return routingDataSource;
    }

}
