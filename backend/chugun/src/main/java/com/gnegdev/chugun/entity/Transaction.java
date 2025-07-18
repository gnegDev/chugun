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
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_rk", updatable = false, nullable = false, unique = true)
    @JsonProperty("transaction_rk")
    private Integer transactionRk;

    @Column(name = "party_rk", updatable = false, nullable = false)
    @JsonProperty("party_rk")
    private Integer partyRk;

    @Column(name = "account_rk", nullable = false)
    @JsonProperty("account_rk")
    private Integer accountRk;

    @Column(name = "financial_account_type_cd", nullable = false)
    @JsonProperty("financial_account_type_cd")
    @Enumerated(EnumType.STRING)
    private FinancialAccountTypeCd financialAccountTypeCd;

    @Column(name = "financial_account_subtype_cd", nullable = false)
    @JsonProperty("financial_account_subtype_cd")
    @Enumerated(EnumType.STRING)
    private FinancialAccountTypeCd financialAccountSubtypeCd;

    @Column(name = "transaction_type_cd", nullable = false)
    @JsonProperty("transaction_type_cd")
    private String transactionTypeCd;

    @Column(name = "transaction_amt_rur", nullable = false)
    @JsonProperty("transaction_amt_rur")
    private Float transactionAmtRur;

    @Column(name = "real_transaction_dttm", nullable = false)
    @JsonProperty("real_transaction_dttm")
    private Date realTransactionDttm;

    @Column(name = "brand_nm", nullable = false)
    @JsonProperty("brand_nm")
    private String brandNm;

    @Column(name = "loyalty_cashback_category_nm", nullable = false)
    @JsonProperty("loyalty_cashback_category_nm")
    private String loyaltyCashbackCategoryNm;

    @Column(name = "loyalty_accrual_rub_amt", nullable = false)
    @JsonProperty("loyalty_accrual_rub_amt")
    private Float loyaltyAccrualRubAmt;

    @Column(name = "utilization_flg", nullable = false)
    @JsonProperty("utilization_flg")
    private Integer utilizationFlg;

    public enum FinancialAccountTypeCd {
        ACR,
        ALR,
        ASC,
        BIL,
        BNK,
        BNP,
        CAR,
        CCR,
        CLA,
        CLN,
        COR,
        CUR,
        DEP,
        GRN,
        IND,
        INS,
        LON,
        MOB,
        MTB,
        MTG,
        MTL,
        PHX,
        REF,
        SAV,
        SCL,
        SLN,
        TAX,
        TCN,
        VKR,
    }
}
