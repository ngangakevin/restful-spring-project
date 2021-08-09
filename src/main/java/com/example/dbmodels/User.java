package com.example.dbmodels;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
