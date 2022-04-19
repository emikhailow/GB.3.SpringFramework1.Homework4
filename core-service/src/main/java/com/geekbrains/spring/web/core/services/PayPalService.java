package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayPalService {
    private final OrderService orderService;

    @Transactional
    public OrderRequest createOrderRequest(Long orderId) {
        com.geekbrains.spring.web.core.entities.Order order = orderService.findById(
                orderId).orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id %d is not found", orderId)));

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("Spring Web Market")
                .landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId(orderId.toString())
                .description("Spring Web Market Order")
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("RUB").value(String.valueOf(order.getTotalPrice()))
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("RUB").value(String.valueOf(order.getTotalPrice())))))
                .items(order.getItems().stream()
                        .map(orderItem -> new Item()
                                .name(orderItem.getProduct().getTitle())
                                .unitAmount(new Money().currencyCode("RUB").value(String.valueOf(orderItem.getPrice())))
                                .quantity(String.valueOf(orderItem.getQuantity())))
                        .collect(Collectors.toList()))
                .shippingDetail(new ShippingDetail().name(new Name().fullName(order.getUsername()))
                        .addressPortable(new AddressPortable()
                                        .addressLine1(order.getAddressLine1())
                                        .addressLine2(order.getAddressLine2())
                                        .adminArea2(order.getAdminArea2())
                                        .adminArea1(order.getAdminArea1())
                                        .postalCode(order.getPostalCode())
                                        .countryCode(order.getCountryCode())
                                //.addressLine1("123 Townsend St").addressLine2("Floor 6")
                                //.adminArea2("San Francisco").adminArea1("CA").postalCode("94107").countryCode("US")
                        ));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

}
