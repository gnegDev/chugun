package com.gnegdev.chugun.controller;

import com.gnegdev.chugun.entity.Balance;
import com.gnegdev.chugun.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {
    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBalance(@RequestBody Balance balance) {
        try {
            Balance response = balanceService.createBalance(balance);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/{party_rk}")
    public ResponseEntity<?> getBalance(@PathVariable("party_rk") Integer partyRk) {
        Optional<List<Balance>> response = balanceService.findByPartyRk(partyRk);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Balance not found.", HttpStatus.NOT_FOUND);

    }
}
