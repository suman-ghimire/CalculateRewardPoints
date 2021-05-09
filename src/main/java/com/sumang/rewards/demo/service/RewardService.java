package com.sumang.rewards.demo.service;

import com.sumang.rewards.demo.dto.ResponseDto;
import com.sumang.rewards.demo.model.Customer;

import java.util.List;


public interface RewardService {

    List<Customer> getCustomerAll();

    Customer getCustomerById(Integer customerId);

    ResponseDto getRewardsPerMonthPerUser(Integer customerId);

    List<ResponseDto> getRewardResponsePerMonthForAllUser();

}
