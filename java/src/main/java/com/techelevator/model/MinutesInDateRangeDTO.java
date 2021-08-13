package com.techelevator.model;


import java.time.LocalDate;

public class MinutesInDateRangeDTO {

    private Long minutes;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long prizeId;

    public MinutesInDateRangeDTO() {
    }

    public MinutesInDateRangeDTO(Long minutes, LocalDate startDate, LocalDate endDate, Long prizeId) {
        this.minutes = minutes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prizeId = prizeId;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }



}
