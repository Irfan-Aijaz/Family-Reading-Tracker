package com.techelevator.controller;

import com.techelevator.dao.SessionDao;
import com.techelevator.model.SessionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

@RestController
@CrossOrigin
public class SessionController {

    private SessionDao sessionDao;

    public SessionController (SessionDao sessionDao) {this.sessionDao = sessionDao;}

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create_session", method = RequestMethod.POST)
    public void createSession(@Valid @RequestBody SessionDTO newSession) {
            sessionDao.createSession(newSession.getIsbn(), newSession.getUserId(), newSession.getDaySession(), newSession.getTimeStart(), newSession.getTimeEnd(), newSession.getPagesRead(), newSession.getFormat(), newSession.getNotes());
    }

    @RequestMapping(value = "/update_user_book", method = RequestMethod.PUT)
    public void updateUserBook(@Valid @RequestBody SessionDTO newSession) {
        Long minutesRead = (MINUTES.between(newSession.getTimeStart(),newSession.getTimeEnd()));

        sessionDao.updateUserBook(newSession.getUserId(), newSession.getIsbn(), newSession.getPagesRead(), minutesRead);
    }
}
