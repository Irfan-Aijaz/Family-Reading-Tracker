package com.techelevator.model;

import java.time.LocalDate;

public class ClaimedPrize {

    private Long claimedPrizeId;
    private Long prizeId;
    private Long claimPrizeRequestStatusId;
    private Long userId;
    private String description;
    private Long milestoneMinutes;
    private LocalDate dateClaimed;
    private Long familyId;
    private LocalDate dateApprovedRejected;
    private String username;

    public ClaimedPrize() {
    }

    public ClaimedPrize(Long claimedPrizeId, Long prizeId, Long claimPrizeRequestStatusId, Long userId, String description, Long milestoneMinutes, LocalDate dateClaimed, Long familyId, LocalDate dateApprovedRejected, String username) {
        this.claimedPrizeId = claimedPrizeId;
        this.prizeId = prizeId;
        this.claimPrizeRequestStatusId = claimPrizeRequestStatusId;
        this.userId = userId;
        this.description = description;
        this.milestoneMinutes = milestoneMinutes;
        this.dateClaimed = dateClaimed;
        this.familyId = familyId;
        this.dateApprovedRejected = dateApprovedRejected;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public LocalDate getDateApprovedRejected() {
        return dateApprovedRejected;
    }

    public void setDateApprovedRejected(LocalDate dateApprovedRejected) {
        this.dateApprovedRejected = dateApprovedRejected;
    }

    public Long getClaimedPrizeId() {
        return claimedPrizeId;
    }

    public void setClaimedPrizeId(Long claimedPrizeId) {
        this.claimedPrizeId = claimedPrizeId;
    }

    public Long getClaimPrizeRequestStatusId() {
        return claimPrizeRequestStatusId;
    }

    public void setClaimPrizeRequestStatusId(Long claimPrizeRequestStatusId) {
        this.claimPrizeRequestStatusId = claimPrizeRequestStatusId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMilestoneMinutes() {
        return milestoneMinutes;
    }

    public void setMilestoneMinutes(Long milestoneMinutes) {
        this.milestoneMinutes = milestoneMinutes;
    }

    public LocalDate getDateClaimed() {
        return dateClaimed;
    }

    public void setDateClaimed(LocalDate dateClaimed) {
        this.dateClaimed = dateClaimed;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
}
