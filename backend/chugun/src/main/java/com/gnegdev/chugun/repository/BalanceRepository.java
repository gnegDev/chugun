package com.gnegdev.chugun.repository;

import com.gnegdev.chugun.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    Optional<List<Balance>> findAllByPartyRk(Integer partyRk);

}
