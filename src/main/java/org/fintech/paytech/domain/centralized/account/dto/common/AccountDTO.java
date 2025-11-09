package org.fintech.paytech.domain.centralized.account.dto.common;

import java.math.BigDecimal;

public class AccountDTO {

    private String name;

    private String description;

    private String currency;

    private BigDecimal balance;

    private String type;

    public AccountDTO() {
    }

    public AccountDTO(String name, String description, String currency, BigDecimal balance, String type) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.balance = balance;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
