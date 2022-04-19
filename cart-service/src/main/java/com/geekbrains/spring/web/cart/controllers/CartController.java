package com.geekbrains.spring.web.cart.controllers;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.dto.StringResponse;
import com.geekbrains.spring.web.cart.converters.CartConverter;
import com.geekbrains.spring.web.cart.models.Cart;
import com.geekbrains.spring.web.cart.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Methods for operating with cart")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @Operation(
            summary = "Request to receive current cart",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class))
                    )
            }
    )
    @GetMapping("/{uuid}")
    public CartDto getCart(
            @RequestHeader(required = false) String username,
            @PathVariable
            @Parameter(description = "uuid", example = "123e4567-e89b-12d3-a456-426614174000")
                    String uuid) {
        return cartConverter.modelToDto(cartService.getCurrentCart(getCurrentCartUuid(username, uuid)));
    }

    @Operation(
            summary = "Request to generate new cart",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @Operation(
            summary = "Request to add an item to cart with given uuid",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/add/{productId}")
    public void add(
            @RequestHeader(required = false) String username,
            @PathVariable @Parameter(description = "uuid", example = "123e4567-e89b-12d3-a456-426614174000") String uuid,
            @PathVariable @Parameter(description = "Product ID", example = "1") Long productId) {
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(
            summary = "Request to decrease the amount of item with given id in a cart with given uuid",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(
            @RequestHeader(required = false) String username,
            @PathVariable @Parameter(description = "uuid", example = "123e4567-e89b-12d3-a456-426614174000") String uuid,
            @PathVariable @Parameter(description = "Product ID", example = "1") Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(
            summary = "Request to remove an item with given id in a cart with given uuid",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(
            @RequestHeader(required = false) String username,
            @PathVariable @Parameter(description = "uuid", example = "123e4567-e89b-12d3-a456-426614174000") String uuid,
            @PathVariable @Parameter(description = "Product ID", example = "1") Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(
            summary = "Request to clear a cart with given uuid",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/clear")
    public void clear(
            @RequestHeader(required = false) String username,
            @PathVariable @Parameter(description = "uuid", example = "123e4567-e89b-12d3-a456-426614174000") String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @Operation(
            summary = "Request to merge a guest cart with a user cart",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/merge")
    public void merge(
            @RequestHeader(required = false) String username,
            @PathVariable @Parameter(description = "uuid", example = "123e4567-e89b-12d3-a456-426614174000") String uuid) {
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }

    @Operation(
            summary = "Request to get most added items",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
                    )
            }
    )
    @GetMapping("/most-added-items")
    public List<ProductDto> getMostAddedItems(
            @RequestParam(name = "count", defaultValue = "10") @Parameter(description = "count", example = "10")  Integer count
    ) {
        return cartService.getMostAddedItems(count);
    }
}
