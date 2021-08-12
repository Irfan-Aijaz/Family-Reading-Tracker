package com.techelevator.model;

public class PrizeClaimCounterDTO {

    private Long prizeId;
    //private Long claimId;
    private Long amountAvailable;

    public PrizeClaimCounterDTO() {
    }

    public PrizeClaimCounterDTO(Long prizeId, Long amountAvailable) {
        this.prizeId = prizeId;
        this.amountAvailable = amountAvailable;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }



    public Long getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Long amountAvailable) {
        this.amountAvailable = amountAvailable;
    }
}
