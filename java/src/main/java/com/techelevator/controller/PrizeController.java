package com.techelevator.controller;


import com.techelevator.dao.PrizeDao;
import com.techelevator.model.ClaimedPrize;
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

    @RequestMapping(value = "/get_prizes_for_family_and_user_group", method = RequestMethod.GET)
    public List<Prize> getPrizesForUserGroup(@RequestParam Long familyId, @RequestParam String userGroup) {
        return prizeDao.getPrizesForFamilyAndUserGroup(familyId, userGroup);
    }

    @RequestMapping(value = "/delete_inactive_prize/{prize_id}", method = RequestMethod.DELETE)
    public void deleteInactivePrize(@PathVariable("prize_id") Long prizeId) {
        prizeDao.removeInactivePrize(prizeId);
    }

    @RequestMapping(value = "/claim_prize_request_child", method = RequestMethod.PUT)
    public void createClaimRequestForChild(@RequestParam Long prizeId, @RequestParam Long childId) {
        prizeDao.createClaimPrizeRequestForChild(prizeId, childId);
    }

    @RequestMapping(value = "/get_claims_for_family/{family_id}", method = RequestMethod.GET)
    public List<ClaimedPrize> getClaimsForFamilyId(@PathVariable("family_id") Long familyId) {
        return prizeDao.getPrizeClaimsByFamilyId(familyId);
    }

    @RequestMapping(value = "/update_claim_requests", method=RequestMethod.POST)
    public void updateClaimRequest(@RequestBody Long[] claimIds, @RequestParam Long claimStatusId) {
        prizeDao.updateClaimRequests(claimIds, claimStatusId);
    }
}
