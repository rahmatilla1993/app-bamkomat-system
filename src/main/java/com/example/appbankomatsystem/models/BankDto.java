package com.example.appbankomatsystem.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BankDto {

    @NotNull
    private String name;
}
