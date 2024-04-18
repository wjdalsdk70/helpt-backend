package com.HELPT.Backend.domain.product;

import com.HELPT.Backend.domain.manager.Manager;
import com.HELPT.Backend.domain.manager.dto.ManagerResponse;
import com.HELPT.Backend.domain.product.dto.ProductRequest;
import com.HELPT.Backend.domain.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    @Transactional(readOnly = true)
    public List<ProductResponse> findProducts(Long gymId) {

        List<Product> products = productRepository.findAllByGymId(gymId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        return  products.stream()
                    .map(product -> new ProductResponse(product))
                    .collect(Collectors.toList());

    }
}
