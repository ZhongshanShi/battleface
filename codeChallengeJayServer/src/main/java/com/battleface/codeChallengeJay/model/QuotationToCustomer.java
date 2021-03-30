package com.battleface.codeChallengeJay.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "quotations")
public class QuotationToCustomer {


    @Id
    private int quotationId;

    private BigDecimal totalPrice;

    private String currencyId;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(int quotationId) {
        this.quotationId = quotationId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

}
