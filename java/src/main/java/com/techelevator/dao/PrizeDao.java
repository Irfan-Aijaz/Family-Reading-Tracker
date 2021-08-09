package com.techelevator.dao;

import java.time.LocalDate;

public interface PrizeDao {

    boolean createPrize(String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd);

    boolean updatePrize(Long prizeId, String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd);


}
