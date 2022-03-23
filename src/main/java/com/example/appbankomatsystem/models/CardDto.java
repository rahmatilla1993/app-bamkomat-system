package com.example.appbankomatsystem.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
public class CardDto {

    @NotNull
    private String special_number;

    @NotNull
    private String CVVCode;

    private Date expire_date;

    @NotNull
    private Integer password;

    private String card_type;

    private Integer bank_id;

    private UUID user_id;
}
