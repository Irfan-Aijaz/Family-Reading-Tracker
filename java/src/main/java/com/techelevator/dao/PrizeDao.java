package com.techelevator.dao;

import com.techelevator.model.ClaimedPrize;
import com.techelevator.model.MinutesInDateRangeDTO;
import com.techelevator.model.Prize;
import com.techelevator.model.PrizeClaimCounterDTO;

import java.time.LocalDate;
import java.util.List;

public interface PrizeDao {

    boolean createPrize(String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd, Long familyId);

    boolean updatePrize(Long prizeId, String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd);

    List<Prize> getPrizesForFamilyAndUserGroup(Long familyId, String userGroup);

    boolean removeInactivePrize(Long prizeId);

    boolean createClaimPrizeRequestForChild(Long prizeId, Long childId);

    Prize getPrizeByPrizeId(Long prizeId);

    List<ClaimedPrize> getPrizeClaimsByFamilyId(Long familyId);

    boolean updateClaimRequest(Long claimId, Long requestStatus);

    boolean updateClaimRequests(Long[] claimIds, Long requestStatus);

    Long getPrizeIdFromClaimId(Long claimId);

    List<PrizeClaimCounterDTO> getPrizeClaimCounterDTOs(Long familyId);

    List<Prize>getPrizesForFamily(Long familyId);

    boolean createClaimRequestForAdmin(Long prizeId, Long parentId);

    List<Prize> getPrizesForClaiming(Long familyId, String userGroup);

    List<ClaimedPrize> viewWonPrizesForUser(Long userId);

    List<ClaimedPrize> viewWonPrizesInFamily(Long familyId);

    List<ClaimedPrize> getPrizeClaimsByUserId(Long userId);









}
