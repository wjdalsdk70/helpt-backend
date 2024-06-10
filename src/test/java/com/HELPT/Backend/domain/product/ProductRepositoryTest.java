package com.HELPT.Backend.domain.product;

import com.HELPT.Backend.domain.equipment.Equipment;
import com.HELPT.Backend.domain.equipment.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .productId(1L)
                .gymId(1L)
                .months(3)
                .price(90000)
                .build();
    }

    @Test
    @DisplayName("[Product] 제품 생성 테스트")
    void saveEquipment(){
        // given

        // when
        Product saveProduct = productRepository.save(product);

        // then
        assertThat(saveProduct).isNotNull();
        assertThat(saveProduct.getPrice()).isEqualTo(product.getPrice());
    }
}
