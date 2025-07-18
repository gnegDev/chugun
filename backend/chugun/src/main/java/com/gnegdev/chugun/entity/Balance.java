package com.gnegdev.chugun.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_balances")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_rk", updatable = false, nullable = false, unique = true)
    @JsonProperty("account_rk")
    private Integer accountRk;

    @Column(name = "party_rk", updatable = false, nullable = false)
    @JsonProperty("party_rk")
    private Integer partyRk;

    @Column(name = "balance_amt", nullable = false)
    @JsonProperty("balance_amt")
    private Float balanceAmt;

    @Column(name = "effective_from_dttm", nullable = false)
    @JsonProperty("effective_from_dttm")
    private Date effectiveFromDttm;
}
