package com.example.appbankomatsystem.service;

import com.example.appbankomatsystem.models.BankDto;
import com.example.appbankomatsystem.models.Result;
import com.example.appbankomatsystem.entity.Bank;
import com.example.appbankomatsystem.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Result addBank(BankDto bankDto) {
        if (bankRepository.existsByName(bankDto.getName())) {
            return new Result("Bank already exists", false);
        }
        Bank bank = new Bank();
        bank.setName(bankDto.getName());
        Bank savedBank = bankRepository.save(bank);
        return new Result("Bank added", true, savedBank);
    }

    public Result deleteBankById(Integer id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            bankRepository.delete(optionalBank.get());
            return new Result("Bank deleted", true);
        }
        return new Result("Bank not found", false);
    }

    public Result editBankById(Integer id, BankDto bankDto) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            Bank editedBank = optionalBank.get();
            if (bankRepository.existsByIdIsNotAndName(id, bankDto.getName())) {
                return new Result("Bank already exists", false);
            }
            editedBank.setName(bankDto.getName());
            Bank savedBank = bankRepository.save(editedBank);
            return new Result("Bank edited", true, savedBank);
        }
        return new Result("Bank not found", false);
    }
}
