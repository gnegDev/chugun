package com.gnegdev.chugun.service;

import com.gnegdev.chugun.entity.Balance;
import com.gnegdev.chugun.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {
    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;

    }

    public Optional<List<Balance>> findByPartyRk(Integer partyRk) {
        return balanceRepository.findAllByPartyRk(partyRk);
    }

    public Balance createBalance(Balance balance) {
        balance = balanceRepository.save(balance);
        return balance;
    }
}
