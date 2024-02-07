package com.nha.miniProject.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "orderEntityManagerFactory", basePackages = {"com.nha.miniProject.repository.order"})
public class OrderDatasource {

	public static final String PREFIXTABLE  = "order";
	public static final String SCEMAS 		= "orderadm";

	@Primary
    @Bean(name = "orderDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.order")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

	@Primary
    @Bean(name = "orderDataSource")
    @ConfigurationProperties("spring.datasource.order.hikari")
    public DataSource dataSource(@Qualifier("orderDataSourceProperties") DataSourceProperties orderDataSourceProperties) {

        return orderDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

	@Primary
    @Bean(name = "orderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("orderDataSource") DataSource orderDataSource) {
        return builder.dataSource(orderDataSource).packages("com.nha.miniProject.models.hibernate.order").persistenceUnit("postgres").build();
    }

	@Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("orderEntityManagerFactory") EntityManagerFactory orderEntityManagerFactory) {
        return new JpaTransactionManager(orderEntityManagerFactory);
    }
}
