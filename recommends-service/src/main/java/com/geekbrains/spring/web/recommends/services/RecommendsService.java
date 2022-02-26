package com.geekbrains.spring.web.recommends.services;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.recommends.configs.RecommendsConfig;
import com.geekbrains.spring.web.recommends.integrations.CartServiceIntegration;
import com.geekbrains.spring.web.recommends.integrations.CoreServiceIntegration;
import com.geekbrains.spring.web.recommends.models.Recommends;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendsService {
    private final Recommends recommends;
    private final CoreServiceIntegration coreServiceIntegration;
    private final CartServiceIntegration cartServiceIntegration;
    @Value("${scheduled-tasks.most-ordered-items.max-count}")
    private Integer mostOrderedItemsMax;
    @Value("${scheduled-tasks.most-added-to-cart-items.max-count}")
    private Integer mostAddedToCartItemsMax;

    public List<ProductDto> getMostOrderedItems(Integer count) {
        return recommends.getMostOrderedItems(count);
    }

    public List<ProductDto> getMostAddedToCartItems(Integer count) {
        return recommends.getMostAddedToCartItems(count);
    }

    @Scheduled(fixedDelayString = "${scheduled-tasks.most-ordered-items.fixed-delay}")
    private void updateMostOrderedItems(){
        recommends.setMostOrderedItems(coreServiceIntegration.getMostOrderedItems(mostOrderedItemsMax));
    }

    @Scheduled(fixedDelayString = "${scheduled-tasks.most-added-to-cart-items.fixed-delay}")
    private void updateMostAddedToCartItems(){
        recommends.setMostAddedToCartItems(cartServiceIntegration.getMostAddedToCartItems(mostAddedToCartItemsMax));
    }
}
