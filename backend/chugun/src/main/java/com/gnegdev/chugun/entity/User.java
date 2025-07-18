package com.gnegdev.chugun.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "party_rk", updatable = false, nullable = false, unique = true)
    @JsonProperty("party_rk")
    private Integer partyRk;

    @Column(name = "gender_cd", nullable = false)
    @JsonProperty("gender_cd")
    @Enumerated(EnumType.STRING)
    private GenderCd genderCd;

    @Column(name = "citizenship_country_nm ", nullable = false)
    @JsonProperty("citizenship_country_nm")
    private String citizenshipCountryNm;

    @Column(name = "monthly_income_amt", nullable = false)
    @JsonProperty("monthly_income_amt")
    private Float monthlyIncomeAmt;

    @Column(name = "first_bank_product_date", nullable = false)
    @JsonProperty("first_bank_product_date")
    private Date firstBankProductDate;

    @Column(name = "first_session_dttm", nullable = false)
    @JsonProperty("first_session_dttm")
    private Date firstSessionDttm;

    @Column(name = "lvn_state_nm", nullable = false)
    @JsonProperty("lvn_state_nm")
    private String lvnStateNm;

    @Column(name = "risk_level_cd", nullable = false)
    @JsonProperty("risk_level_cd")
    @Enumerated(EnumType.STRING)
    private RiskLevelCd riskLevelCd;

    @Column(name = "group_level")
    @JsonProperty("group_level")
    private Integer groupLevel;

    @Column(name = "net_salary")
    @JsonProperty("net_salary")
    private Float netSalary;

    @OneToMany
    @JoinColumn(name = "party_rk")
    private List<Balance> balances;

    @OneToMany
    @JoinColumn(name = "party_rk")
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public enum GenderCd {
        F,
        M
    }
    public enum RiskLevelCd {
        LWR,
        MED,
        HGH
    }
}

