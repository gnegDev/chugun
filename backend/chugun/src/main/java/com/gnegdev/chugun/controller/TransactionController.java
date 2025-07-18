package com.gnegdev.chugun.controller;

import com.gnegdev.chugun.entity.Transaction;
import com.gnegdev.chugun.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction response = transactionService.createTransaction(transaction);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/{party_rk}")
    public ResponseEntity<?> getTransaction(@PathVariable("party_rk") Integer partyRk) {
        Optional<List<Transaction>> response = transactionService.findByPartyRk(partyRk);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Transaction not found.", HttpStatus.NOT_FOUND);

    }
}
