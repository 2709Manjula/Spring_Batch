package com.example.SpringBatch.batchConfig;


import com.example.SpringBatch.entity.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product product) {
        return product;
    }
}

