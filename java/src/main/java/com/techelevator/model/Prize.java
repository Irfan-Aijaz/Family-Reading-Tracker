package com.techelevator.model;

import java.time.LocalDate;

public class Prize {

    private Long prizeId;
    private String prizeName;
    private String prizeDescription;
    private Long milestoneMinutes;
    private String userGroup;
    private Long maxPrizes;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public Prize() {
    }

    public Prize(Long prizeId, String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd) {
        this.prizeId = prizeId;
        this.prizeName = prizeName;
        this.prizeDescription = prizeDescription;
        this.milestoneMinutes = milestoneMinutes;
        this.userGroup = userGroup;
        this.maxPrizes = maxPrizes;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeDescription() {
        return prizeDescription;
    }

    public void setPrizeDescription(String prizeDescription) {
        this.prizeDescription = prizeDescription;
    }

    public Long getMilestoneMinutes() {
        return milestoneMinutes;
    }

    public void setMilestoneMinutes(Long milestoneMinutes) {
        this.milestoneMinutes = milestoneMinutes;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public Long getMaxPrizes() {
        return maxPrizes;
    }

    public void setMaxPrizes(Long maxPrizes) {
        this.maxPrizes = maxPrizes;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
