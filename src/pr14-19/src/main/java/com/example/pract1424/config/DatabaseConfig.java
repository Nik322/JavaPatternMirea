package com.example.pract1424.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatabaseConfig {

  @Bean
  public HikariDataSource dataSource() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:postgresql://localhost:5432/prac14");
    config.setUsername("postgres");
    config.setPassword("postgres");
    return new HikariDataSource(config);
  }

  @Bean(name = "entityManagerFactory")
  public LocalSessionFactoryBean factoryBean(DataSource dataSource) {
    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setPackagesToScan("com.example.pract1424");
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    sessionFactoryBean.setHibernateProperties(properties);
    return sessionFactoryBean;
  }
}
