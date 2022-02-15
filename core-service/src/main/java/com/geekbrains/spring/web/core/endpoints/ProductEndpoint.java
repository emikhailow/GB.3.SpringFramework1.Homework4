package com.geekbrains.spring.web.core.endpoints;

import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.core.services.ProductsService;

import com.geekbrains.spring.web.core.soap.products.GetAllProductsResponse;
import com.geekbrains.spring.web.core.soap.products.GetProductByIdRequest;
import com.geekbrains.spring.web.core.soap.products.GetProductByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.ewm.com/spring/ws/products";
    private final ProductsService productsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productsService.findById(request.getId())
                .map(ProductsService.functionEntityToSoap)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", request.getId()))));
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts() {
        GetAllProductsResponse response = new GetAllProductsResponse();

        productsService.findAll()
                .stream()
                .map(ProductsService.functionEntityToSoap)
                .forEach(response.getProducts()::add);

        return response;
    }
}
