package com.Geek.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreRequestDto {
    @NotNull(message = "User ID cannot be null")
    private String userId;
    private String name;
    private String addressId;
}