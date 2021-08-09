package com.techelevator.controller;


import com.techelevator.dao.PrizeDao;
import com.techelevator.model.Prize;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        prizeDao.createPrize(newPrize.getPrizeName(), newPrize.getPrizeDescription(), newPrize.getMilestoneMinutes(), newPrize.getUserGroup(), newPrize.getMaxPrizes(), newPrize.getDateStart(), newPrize.getDateEnd());
    }
}
