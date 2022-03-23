package com.example.appbankomatsystem.repository;

import com.example.appbankomatsystem.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    boolean existsBySpecialNumber(String special_number);

    boolean existsByIdIsNotAndSpecialNumber(Integer id,String special_number);
}
