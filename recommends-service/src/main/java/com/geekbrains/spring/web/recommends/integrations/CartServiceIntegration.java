package com.geekbrains.spring.web.recommends.integrations;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public List<ProductDto> getMostAddedToCartItems(Integer count){
        List<ProductDto> productDtoList = List.of(cartServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cart/most-added-items")
                        .queryParam("count", String.valueOf(count))
                        .build())
                .retrieve().bodyToMono(ProductDto[].class)
                .block());

        return productDtoList;
    }

}