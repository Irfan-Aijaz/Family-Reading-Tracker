package com.techelevator.controller;


import com.techelevator.dao.PrizeDao;
import com.techelevator.model.Prize;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class PrizeController {

    private PrizeDao prizeDao;

    public PrizeController(PrizeDao prizeDao) {
        this.prizeDao = prizeDao;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create_prize", method = RequestMethod.POST)
    public void createPrize(@Valid @RequestBody Prize newPrize) {
        prizeDao.createPrize(newPrize.getPrizeName(), newPrize.getPrizeDescription(), newPrize.getMilestoneMinutes(), newPrize.getUserGroup(), newPrize.getMaxPrizes(), newPrize.getDateStart(), newPrize.getDateEnd(), newPrize.getFamilyId());
    }

    @RequestMapping(value = "/update_prize", method = RequestMethod.PUT)
    public void editPrize(@Valid @RequestBody Prize prizeToUpdate) {
        prizeDao.updatePrize(prizeToUpdate.getPrizeId(), prizeToUpdate.getPrizeName(), prizeToUpdate.getPrizeDescription(), prizeToUpdate.getMilestoneMinutes(), prizeToUpdate.getUserGroup(), prizeToUpdate.getMaxPrizes(), prizeToUpdate.getDateStart(), prizeToUpdate.getDateEnd());
    }

    @RequestMapping(value = "/get_prizes_for_user_group", method = RequestMethod.GET)
    public List<Prize> getPrizesForUserGroup(@RequestParam String userGroup, @RequestParam Long familyId) {
        return prizeDao.getPrizesForFamilyAndUserGroup(familyId, userGroup);
    }
}