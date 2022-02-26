package com.geekbrains.spring.web.recommends.integrations;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient coreServiceWebClient;

    public List<ProductDto> getMostOrderedItems(Integer count){
        List<ProductDto> productDtoList = List.of(coreServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/orders/most-ordered-products")
                        .queryParam("count", String.valueOf(count))
                        .build())
                .retrieve()
                .bodyToMono(ProductDto[].class)
                .block());
        return productDtoList;
    }

}