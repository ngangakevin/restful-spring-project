package com.example.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class CreateUserDTO {

    @NotBlank(message = "user name cannot be blank")
    private String name;

    @NotBlank(message = "mail cannot be blank")
    private String email;

    @NotBlank(message = "phone number cannot be blank")
    @Min(value = 10, message = "phone number must have more than 9 characters")
    @Max(value = 15, message = "phone number must have less than 16 characters")
    @Pattern(regexp = "^\\+?(254|1|44|91)[0-9]{7,12}$\n"
            ,message = "phone number must begin with a valid country code")
    private String phone;
}
