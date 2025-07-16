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
	            .sql("""
	                INSERT INTO product (id, name, color, price, quantity, amount)
	                VALUES (:id, :name, :color, :price, :quantity, :amount)
	                ON DUPLICATE KEY UPDATE 
	                    name = VALUES(name),
	                    color = VALUES(color),
	                    price = VALUES(price),
	                    quantity = VALUES(quantity),
	                    amount = VALUES(amount)
	                """)
	            .dataSource(dataSource)
	            .beanMapped()
	            .build();
	}

}
