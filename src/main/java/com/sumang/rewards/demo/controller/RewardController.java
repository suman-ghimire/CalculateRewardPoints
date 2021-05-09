package com.sumang.rewards.demo.controller;

import com.sumang.rewards.demo.dto.CustomerDto;
import com.sumang.rewards.demo.dto.ResponseDto;
import com.sumang.rewards.demo.model.Customer;
import com.sumang.rewards.demo.service.RewardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RewardController {

    @Autowired
    private RewardServiceImpl rewardService;

    @GetMapping("/customers")
    public List<Customer> findCustomerAll() {
        return rewardService.getCustomerAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer id) {
        Customer customer = rewardService.getCustomerById(id);
        if (customer == null) return new ResponseEntity<CustomerDto>(HttpStatus.NOT_FOUND);
        CustomerDto customerDto = new CustomerDto(customer.getTransactions(), customer.getId(), customer.getName());

        return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
    }

    @GetMapping("/customers/rewardpoints")
    public List<ResponseDto> findCustomerDtoAll() {
        return rewardService.getRewardResponsePerMonthForAllUser();
    }

    @GetMapping("/customers/rewardpoints/{id}")
    public ResponseDto getRewardPointsById(@PathVariable Integer id) {
        return rewardService.getRewardsPerMonthPerUser(id);
    }
}
