package com.sumang.rewards.demo.service;

import com.sumang.rewards.demo.Repository.CustomerRepository;
import com.sumang.rewards.demo.model.Customer;
import com.sumang.rewards.demo.model.Transactions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RewardServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    RewardService rewardService = new RewardServiceImpl();

    @Before
    public void setupMock(){
        when(customerRepository.findById(3)).thenReturn(Optional.ofNullable(null));
    }

    @Test
    public void getRewardsPerMonthPerUserNullReturnTest(){
        assertEquals(null,rewardService.getRewardsPerMonthPerUser(3));
    }

    @Test
    public void calculateRewardsPointsTest(){
        Set<Transactions> transactions = new HashSet<>();
        transactions.add(getListOfTransaction(LocalDateTime.now(), 1L, 120d));
        when(customerRepository.findById(103)).thenReturn(Optional.of(getCustomerTestData(103, "Suman", transactions)));
        assertEquals(90, rewardService.getRewardsPerMonthPerUser(103).getTotalRewardPoints().longValue());
    }

    @Test
    public void calculateRewardsPointsOneCustomerTest(){
        Set<Transactions> transactions = new HashSet<>();
        transactions.add(getListOfTransaction(LocalDateTime.now(), 1L, 120d));
        transactions.add(getListOfTransaction(LocalDateTime.now(), 2L, 120d));
        transactions.add(getListOfTransaction(LocalDateTime.now(), 3L, 120d));
        when(customerRepository.findById(103)).thenReturn(Optional.of(getCustomerTestData(103, "Prajwal", transactions)));
        assertEquals(270, rewardService.getRewardsPerMonthPerUser(103).getTotalRewardPoints().longValue());
    }

    @Test
    public void calculateRewardsPointsOneCustomerOneTransactionTest(){
        Set<Transactions> transactions = new HashSet<>();
        transactions.add(getListOfTransaction(LocalDateTime.now(), 1L, 59d));
        when(customerRepository.findById(102)).thenReturn(Optional.of(getCustomerTestData(102, "Prajwal", transactions)));
        assertEquals(9, rewardService.getRewardsPerMonthPerUser(102).getTotalRewardPoints().longValue());
    }

    private Customer getCustomerTestData(int id, String name, Set<Transactions> transactions ){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setTransactions(transactions);
        return customer;
    }

    private Transactions getListOfTransaction(LocalDateTime date, Long id, Double total) {
        Transactions transactions = new Transactions();
        transactions.setId(id);
        transactions.setTotal(total);
        transactions.setPurchaseDate(date);
        return transactions;
    }

}