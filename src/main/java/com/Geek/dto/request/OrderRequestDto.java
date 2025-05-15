package com.Geek.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {

    @NotNull(message = "ProductId cannot be null")
    private String productId;

    @NotNull(message = "Size cannot be null")
    private String size;

    @NotNull(message = "Color cannot be null")
    private String color;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "User ID cannot be null")
    private String userId;

    @NotNull(message = "AddressId cannot be null")
    private String addressId;

    private String voucherId;
}