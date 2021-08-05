package com.techelevator.controller;

import com.techelevator.dao.SessionDao;
import com.techelevator.model.Session;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class SessionController {

    private SessionDao sessionDao;

    public SessionController (SessionDao sessionDao) {this.sessionDao = sessionDao;}

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create_session", method = RequestMethod.POST)
    public void createSession(@Valid @RequestBody Session newSession) {
            sessionDao.createSession(newSession.getIsbn(), newSession.getUserId(), newSession.getDaySession(), newSession.getStartTime(), newSession.getEndTime(), newSession.getFormat(), newSession.getNotes());
    }
}
