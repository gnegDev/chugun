package com.gnegdev.chugun.service;

import com.gnegdev.chugun.entity.Transaction;
import com.gnegdev.chugun.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

    }

    public Optional<List<Transaction>> findByPartyRk(Integer partyRk) {
        return transactionRepository.findAllByPartyRk(partyRk);
    }

    public Transaction createTransaction(Transaction transaction) {
        transaction = transactionRepository.save(transaction);
        return transaction;
    }
}
