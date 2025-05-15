package com.Geek.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotNull(message = "Store ID cannot be null")
    private String storeId;

    @Min(value = 0, message = "CurrentPrice must be at least 0")
    private Double currentPrice;

    private String name;

}
