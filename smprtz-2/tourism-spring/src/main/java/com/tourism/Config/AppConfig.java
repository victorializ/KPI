package com.tourism.Config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/tourism?serverTimezone=UTC");
        ds.setUsername("victoria");
        ds.setPassword("pASSWORD.1");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());

        return template;
    }
}
