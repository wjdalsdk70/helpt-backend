package com.HELPT.Backend.domain.product;

import com.HELPT.Backend.domain.product.dto.ProductResponse;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Slf4j
class ProductServiceTest_ {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .productId(1L)
                .gymId(1L)
                .months(5)
                .price(10000)
                .build();
    }

    @Test
    @DisplayName("Id검색")
    public void findById() {
        //given
        List<Product> pList = new LinkedList<>();
        pList.add(product);
        when(productRepository.findAllByGymId(1L)).thenReturn(Optional.of(pList));

        //when
        List<ProductResponse> findList = productService.findAllProducts(1L);


        //then
        ProductResponse findRes = findList.stream().findFirst().get();
        assertThat(findRes.getPrice()).isEqualTo(10000);
        assertThat(findRes.getMonths()).isEqualTo(5);
        


    }

    /*
    @Test
    public void testFindEntityById_NotFound() {
        when(myRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            myService.findEntityById(1L);
        });
    }
*/

}