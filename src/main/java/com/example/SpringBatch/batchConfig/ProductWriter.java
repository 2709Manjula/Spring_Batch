package com.example.SpringBatch.batchConfig;

import com.example.SpringBatch.entity.Product;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ProductWriter {

    @Bean
    public JdbcBatchItemWriter<Product> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Product>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO product (id, name, color, quantity, price) " +
                        "VALUES (:id, :name, :color, :quantity, :price) " +
                        "ON DUPLICATE KEY UPDATE name=:name, color=:color, quantity=:quantity, price=:price")
                .dataSource(dataSource)
                .build();
    }

}
