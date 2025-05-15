package com.Geek.dto.request;

//import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Address cannot be null")
    private String address;

    @NotNull(message = "PhoneNumber cannot be null")
    private String phonenumber;

    @NotNull(message = "PhoneNumber cannot be null")
    private String email;

    @NotNull(message = "Name cannot be null")
    private String name;

}