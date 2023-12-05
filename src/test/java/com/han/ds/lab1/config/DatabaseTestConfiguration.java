package com.han.ds.lab1.config;

import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.Driver;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class DatabaseTestConfiguration {
    private final String POSTGRES_IMAGE = "postgres:13-alpine";
    private final String DATABASE_NAME = "test_db";
    private final String USERNAME = "program";
    private final String PASSWORD = "test";

    @Bean(destroyMethod = "close")
    public PostgreSQLContainer postgres() {
        var postgres = new PostgreSQLContainer(POSTGRES_IMAGE)
                .withUsername(USERNAME)
                .withPassword(PASSWORD)
                .withDatabaseName(DATABASE_NAME);
        postgres.start();
        return postgres;
    }

    @Primary
    @DependsOn("postgres")
    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource(){
        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(postgres().getJdbcUrl());
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setDriverClassName(Driver.class.getCanonicalName());
        return dataSource;
    }
}

