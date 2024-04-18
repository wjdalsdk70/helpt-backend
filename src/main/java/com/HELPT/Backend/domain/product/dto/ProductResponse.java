package com.HELPT.Backend.domain.product.dto;

import com.HELPT.Backend.domain.product.Product;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponse {
    private int day;
    private int price;
    public ProductResponse(Product product) {
        this.day = product.getDay();
        this.price = product.getPrice();
    }
}
