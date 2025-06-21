package com.example.dbmodels;

import com.example.dtos.CreateUserDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Length(message = "User name must be within character limit", min = 4, max = 32)
    @NotNull
    private String name;

    @NotNull
    @Email(message = "Must be valid email address")
    private String email;

//    @Pattern(message = "Must be a valid international phone number", regexp = "^\\\\+(?:[0-9]\u25CF?){6,14}[0-9]$")
    @NotNull
    private String phone;

    public static User fromDTO(CreateUserDTO createUser){
        return new User(null, createUser.getName(), createUser.getEmail(), createUser.getPhone());
    }
}
