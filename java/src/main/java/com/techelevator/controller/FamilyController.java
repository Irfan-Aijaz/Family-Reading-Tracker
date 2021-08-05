package com.techelevator.controller;


import com.techelevator.dao.UserDao;
import com.techelevator.model.FamilyDTO;
import com.techelevator.model.RegisterUserDTO;
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
        public List<String> users(@PathVariable Long id) {
            return userDao.getUsernamesByFamilyId(id);

    }
}
