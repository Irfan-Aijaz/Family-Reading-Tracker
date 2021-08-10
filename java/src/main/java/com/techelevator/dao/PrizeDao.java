package com.techelevator.dao;

import com.techelevator.model.Prize;

import java.time.LocalDate;
import java.util.List;

public interface PrizeDao {

    boolean createPrize(String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd, Long familyId);

    boolean updatePrize(Long prizeId, String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd);

    List<Prize> getPrizesForFamilyAndUserGroup(Long familyId, String userGroup);


}
