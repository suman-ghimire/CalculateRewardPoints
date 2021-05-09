package com.sumang.rewards.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Transactions extends Rewards{
    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Customer customer;
    private Double total;

    private LocalDateTime purchaseDate;

    public Transactions() {
        super();
    }

    public Transactions(Long id, Customer customer, Double total, LocalDateTime purchaseDate) {
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public Long getRewardPoints() {
        this.points = 0l;

        if (this.total > 50 && this.total <= 100) {
            this.points += (this.total.intValue() - 50) * 1;

        }

        if (this.total > 100) {
            this.points += 50;
            this.points += (this.total.intValue() - 100) * 2;
        }

        return this.points;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", customer=" + customer +
                ", total=" + total +
                ", purchase_date=" + purchaseDate +
                '}';
    }
}
