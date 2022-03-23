package com.example.appbankomatsystem.repository;

import com.example.appbankomatsystem.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,Integer> {

    boolean existsByName(String name);

    boolean existsByIdIsNotAndName(Integer id, String name);
}
