package com.sumang.rewards.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Month;
import java.util.Map;

public class ResponseDto {

    Integer id;
    String name;
    Map<Month, Long> rewardPointsPerMonthMap;
    Long totalRewardPoints;
    @JsonIgnore
    CustomerDto customerDto;

    public ResponseDto(CustomerDto customerDto) {
        this.id = customerDto.id;
        this.name = customerDto.name;
        this.customerDto = customerDto;
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

    public Map<Month, Long> getRewardPointsPerMonthMap() {

        return rewardPointsPerMonthMap;
    }

    public void setRewardPointsPerMonthMap(Map<Month, Long> rewardPointsPerMonthMap) {
        this.rewardPointsPerMonthMap = rewardPointsPerMonthMap;
    }

    public Long getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(Long totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }
}
