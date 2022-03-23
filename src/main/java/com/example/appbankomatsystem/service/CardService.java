package com.example.appbankomatsystem.service;

import com.example.appbankomatsystem.entity.Bank;
import com.example.appbankomatsystem.entity.Card;
import com.example.appbankomatsystem.entity.User;
import com.example.appbankomatsystem.entity.enums.CardName;
import com.example.appbankomatsystem.models.CardDto;
import com.example.appbankomatsystem.models.Result;
import com.example.appbankomatsystem.repository.BankRepository;
import com.example.appbankomatsystem.repository.CardRepository;
import com.example.appbankomatsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    UserRepository userRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Result getCardById(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.map(card -> new Result(true, card)).orElseGet(() -> new Result("Card not found", false));
    }

    public Result addCard(CardDto cardDto) {
        if (cardRepository.existsBySpecialNumber(cardDto.getSpecial_number())) {
            return new Result("This card already exists", false);
        }
        Optional<Bank> optionalBank = bankRepository.findById(cardDto.getBank_id());
        if (optionalBank.isEmpty()) {
            return new Result("Bank not found", false);
        }
        Optional<User> optionalUser = userRepository.findById(cardDto.getUser_id());
        if (optionalUser.isEmpty()) {
            return new Result("User not found", false);
        }
        User user = optionalUser.get();
        Bank bank = optionalBank.get();
        Card card = new Card();
        card.setBank(bank);
        card.setCVV_code(cardDto.getCVVCode());
        card.setCard_type(CardName.valueOf(cardDto.getCard_type()));
        card.setExpire_date(cardDto.getExpire_date());
        card.setPassword(cardDto.getPassword());
        card.setStatus(true);
        card.setUser(user);
        card.setSpecialNumber(cardDto.getSpecial_number());
        Card savedCard = cardRepository.save(card);
        return new Result("Card added", true, savedCard);
    }

    public Result deleteCardById(Integer id){
        Optional<Card> optionalCard = cardRepository.findById(id);
        if(optionalCard.isPresent()){
            cardRepository.delete(optionalCard.get());
            return new Result("Card deleted",true);
        }
        return new Result("Card not found",false);
    }

    public Result editCardById(Integer id,CardDto cardDto){
        Optional<Card> optionalCard = cardRepository.findById(id);
        if(optionalCard.isPresent()){
            Card editedCard = optionalCard.get();
            if (cardRepository.existsByIdIsNotAndSpecialNumber(id, cardDto.getSpecial_number())) {
                return new Result("Card already exists",false);
            }
            Optional<Bank> optionalBank = bankRepository.findById(cardDto.getBank_id());
            if (optionalBank.isEmpty()) {
                return new Result("Bank not found", false);
            }
            Optional<User> optionalUser = userRepository.findById(cardDto.getUser_id());
            if (optionalUser.isEmpty()) {
                return new Result("User not found", false);
            }
            User user = optionalUser.get();
            Bank bank = optionalBank.get();
            editedCard.setBank(bank);
            editedCard.setCVV_code(cardDto.getCVVCode());
            editedCard.setCard_type(CardName.valueOf(cardDto.getCard_type()));
            editedCard.setExpire_date(cardDto.getExpire_date());
            editedCard.setPassword(cardDto.getPassword());
            editedCard.setStatus(true);
            editedCard.setUser(user);
            editedCard.setSpecialNumber(cardDto.getSpecial_number());
            Card savedCard = cardRepository.save(editedCard);
            return new Result("Card edited successfully",true,savedCard);
        }
        return new Result("Card not found",false);
    }
}
