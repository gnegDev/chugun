package com.gnegdev.chugun.repository;

import com.gnegdev.chugun.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<List<Transaction>> findAllByPartyRk(Integer partyRk);
}
