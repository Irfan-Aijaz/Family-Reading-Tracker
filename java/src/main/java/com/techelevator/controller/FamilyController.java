package com.techelevator.controller;


import com.techelevator.dao.UserDao;
import com.techelevator.model.UserDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class FamilyController {

    private UserDao userDao;

    public FamilyController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/retrieve_family/{id}", method = RequestMethod.GET)
        public List<String> usernamesByFamilyId(@PathVariable Long id) {
            return userDao.getUsernamesByFamilyId(id);

    }

    @RequestMapping(value = "/retrieve_user_ids/{family_id}", method = RequestMethod.GET)
    public List<Long> userIdsByFamilyId(@PathVariable Long familyId) {
        return userDao.getUserIdsByFamilyId(familyId);
    }

    @RequestMapping(value = "/retrieve_user_dtos/{family_id}", method = RequestMethod.GET)
    public List<UserDTO> userDTOsByFamilyId(@PathVariable("family_id") Long familyId) {
        return userDao.getUserDTOsByFamilyId(familyId);
    }


}
