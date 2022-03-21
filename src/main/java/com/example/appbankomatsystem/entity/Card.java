package com.example.appbankomatsystem.entity;

import com.example.appbankomatsystem.entity.enums.CardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String special_number;

    @Column(nullable = false)
    private String CVV_code;

    @Column(nullable = false)
    private Date expire_date;

    private boolean status=true;

    @Column(nullable = false)
    private Integer password;

    @Enumerated(EnumType.STRING)
    private CardName card_type;

    @ManyToOne(optional = false)
    private Bank bank;

    @ManyToOne(optional = false)
    private User user;
}
