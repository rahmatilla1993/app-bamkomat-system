package com.example.appbankomatsystem.controller;

import com.example.appbankomatsystem.models.BankDto;
import com.example.appbankomatsystem.models.Result;
import com.example.appbankomatsystem.entity.Bank;
import com.example.appbankomatsystem.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping
    public HttpEntity<?> getAllBanks() {
        List<Bank> bankList = bankService.getAllBanks();
        return ResponseEntity.ok(bankList);
    }

    @PostMapping
    public HttpEntity<?> addBank(@RequestBody BankDto bankDto) {
        Result result = bankService.addBank(bankDto);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteBankById(@PathVariable Integer id) {
        Result result = bankService.deleteBankById(id);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(result);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editBankById(@PathVariable Integer id, @RequestBody BankDto bankDto) {
        Result result = bankService.editBankById(id, bankDto);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_ACCEPTABLE).body(result);
    }
}
