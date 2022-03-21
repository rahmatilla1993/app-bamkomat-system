package com.example.appbankomatsystem.entity;

import com.example.appbankomatsystem.entity.Address;
import com.example.appbankomatsystem.entity.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bankomat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double max_amount;

    @Column(nullable = false)
    private Double min_amount;

    @ManyToMany(mappedBy = "bankomats")
    private Set<CardType> card_types;

    //bankomatga biriktirilgan mas'ul xodim
    @OneToOne
    private User user;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    private Address address;
}
