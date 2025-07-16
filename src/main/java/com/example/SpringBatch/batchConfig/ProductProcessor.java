package com.example.SpringBatch.batchConfig;


import com.example.SpringBatch.entity.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductProcessor implements ItemProcessor<Product, Product> {

    @Override
    public Product process(Product item) {

        if (item.getName() != null) {
            item.setName(item.getName().toUpperCase());
        }
        if (item.getColor() != null && !item.getColor().isEmpty()) {
            String color = item.getColor();
            item.setColor(color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase());
        }

        item.setPrice(Math.round(item.getPrice() * 100.0) / 100.0);

        item.setQuantity((int) Math.round(item.getQuantity()));

        double amount = item.getPrice() * item.getQuantity();
        item.setAmount(Math.round(amount * 100.0) / 100.0);

        if (item.getPrice() < 0 || item.getQuantity() < 0) {
            return null; 
        }

        return item;
    }
}


