package io.swagger.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSources {
    @Bean(name = "registryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.registry")
    public DataSource registryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "registryJdbcTemplate")
    public JdbcTemplate registryJdbcTemplate(
        @Qualifier("registryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "viewerDataSource")
    @ConfigurationProperties(prefix="spring.datasource.viewer")
    public DataSource viewerDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "viewerJdbcTemplate")
    public JdbcTemplate viewerJdbcTemplate(
        @Qualifier("viewerDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}