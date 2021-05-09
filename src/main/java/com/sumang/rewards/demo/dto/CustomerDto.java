package com.sumang.rewards.demo.dto;

import com.sumang.rewards.demo.model.Transactions;

import java.util.Set;

public class CustomerDto {
    Integer id;
    String name;
    Set<Transactions> transactions;
    Long totalRewardPoints;


    public CustomerDto(Set<Transactions> transactions, Integer id, String name) {
        this.id = id;
        this.name = name;
        this.transactions = transactions;

    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalRewardPoints() {

        if (transactions == null || transactions.isEmpty()) return 0l;

        return transactions.stream()
                .map(x -> x.getRewardPoints().intValue())
                .reduce(0, (a, b) -> a + b).longValue();
    }


    public void setTotalRewardPoints(Long totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }
}
