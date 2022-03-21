package com.geekbrains.spring.web.core.controllers;

import com.geekbrains.spring.web.api.core.ProductCategoryDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.core.converters.OrderConverter;
import com.geekbrains.spring.web.api.core.OrderDetailsDto;
import com.geekbrains.spring.web.api.core.OrderDto;
import com.geekbrains.spring.web.core.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Method for operating with orders")
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @Operation(
            summary = "Request to create new order",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(
            @RequestHeader
            String username,
            @RequestBody OrderDetailsDto orderDetailsDto){
        orderService.createOrder(username, orderDetailsDto);
    }

    @Operation(
            summary = "Request to get the orders list of current user",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))
                    )
            }
    )
    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String username){
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Request to get the most ordered items",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
                    )
            }
    )
    @GetMapping("/most-ordered-products")
    public List<ProductDto> getMostOrderedItems(@RequestParam(name = "count", defaultValue = "5") Integer count){
        return orderService.getMostOrderedItems(count);
    }

}
