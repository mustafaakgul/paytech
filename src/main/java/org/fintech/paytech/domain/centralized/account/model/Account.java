package org.fintech.paytech.domain.centralized.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.fintech.budget.domain.core.transaction.model.Transaction;
import org.fintech.budget.domain.core.user.model.User;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String currency;

    private BigDecimal balance;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Transaction> transactions;

    public Account() {
    }

    public Account(String name, String description, String currency, BigDecimal balance, String type) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.balance = balance;
        this.type = type;
    }

    public Account(Long id, String name, String description, String currency, BigDecimal balance, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.balance = balance;
        this.type = type;
    }

    /*public Account(Long id, String name, String description, String currency, BigDecimal balance, String type, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.balance = balance;
        this.type = type;
        this.transactions = transactions;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(description, account.description) && Objects.equals(currency, account.currency) && Objects.equals(balance, account.balance) && Objects.equals(type, account.type) && Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, currency, balance, type, transactions);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                ", transactions=" + transactions +
                '}';
    }*/
}
