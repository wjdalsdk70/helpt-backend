package com.HELPT.Backend.domain.product;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class ProductServiceTest {

    @Autowired
    private ProductRepository userRepository;

    @Test
    public void saveTest() {

        Product saveProduct = Product.builder()
                .gymId(1L)
                .day(20)
                .price(10000)
                .build();

        Product product = userRepository.save(saveProduct);

        assertEquals(20, product.getDay());
        assertEquals(10000, product.getPrice());
    }

}