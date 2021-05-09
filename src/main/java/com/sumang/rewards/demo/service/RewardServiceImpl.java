package com.sumang.rewards.demo.service;

import com.sumang.rewards.demo.Repository.CustomerRepository;
import com.sumang.rewards.demo.dto.CustomerDto;
import com.sumang.rewards.demo.dto.ResponseDto;
import com.sumang.rewards.demo.model.Customer;
import com.sumang.rewards.demo.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Service
@Transactional
public class RewardServiceImpl implements RewardService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElse(null);
    }

    @Override
    public ResponseDto getRewardsPerMonthPerUser(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return null;
        CustomerDto customerDto = new CustomerDto(customer.getTransactions(), customer.getId(), customer.getName());
        ResponseDto responseDto = new ResponseDto(customerDto);
        responseDto.setTotalRewardPoints(customerDto.getTotalRewardPoints());
        responseDto.setRewardPointsPerMonthMap(getRewardsPerMonth(customerDto));
        return responseDto;
    }

    @Override
    public List<ResponseDto> getRewardResponsePerMonthForAllUser() {
        List<Customer> customers = getCustomerAll();
        List<ResponseDto> responseDtos = new ArrayList<>();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer c : customers) {
            CustomerDto customerDto = new CustomerDto(c.getTransactions(), c.getId(), c.getName());
            customerDtos.add(customerDto);
            ResponseDto responseDto = new ResponseDto(customerDto);
            responseDto.setTotalRewardPoints(customerDto.getTotalRewardPoints());
            responseDto.setRewardPointsPerMonthMap(getRewardsPerMonth(customerDto));
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    private Map<Month, Long> getRewardsPerMonth(CustomerDto customerDto) {
        Set<Transactions> transactionList = customerDto.getTransactions();
        Map<Month, Long> monthWithTotalReward = new TreeMap<>();

        for (Transactions t : transactionList) {
            LocalDateTime transactionDate = t.getPurchaseDate();
            Month month = transactionDate.getMonth();

            if (monthWithTotalReward.containsKey(month)) {
                Long currentRewardTotal = monthWithTotalReward.get(month);
                currentRewardTotal += t.getRewardPoints();
                monthWithTotalReward.put(month, currentRewardTotal);
            } else {
                monthWithTotalReward.put(month, t.getRewardPoints());
            }
        }
        return monthWithTotalReward;
    }
}
