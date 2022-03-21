package com.example.appbankomatsystem.entity;

import com.example.appbankomatsystem.entity.enums.CardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CardName cardName;

    @ManyToMany
    @JoinTable(
            name = "bankomat_cardType",
            joinColumns = @JoinColumn(name = "cardType_id"),
            inverseJoinColumns = @JoinColumn(name = "bankomat_id")
    )
    private Set<Bankomat> bankomats;
}
