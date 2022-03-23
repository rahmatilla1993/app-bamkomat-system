package com.example.appbankomatsystem.controller;

import com.example.appbankomatsystem.entity.Card;
import com.example.appbankomatsystem.models.CardDto;
import com.example.appbankomatsystem.models.Result;
import com.example.appbankomatsystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping
    public HttpEntity<?> getAllCards() {
        List<Card> cardList = cardService.getAllCards();
        return ResponseEntity.ok(cardList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCardById(@PathVariable Integer id) {
        Result result = cardService.getCardById(id);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(result);
    }

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody CardDto cardDto) {
        Result result = cardService.addCard(cardDto);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_ACCEPTABLE).body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCardById(@PathVariable Integer id) {
        Result result = cardService.deleteCardById(id);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(result);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCardById(@PathVariable Integer id, @RequestBody CardDto cardDto) {
        Result result = cardService.editCardById(id, cardDto);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_ACCEPTABLE).body(result);
    }
}
