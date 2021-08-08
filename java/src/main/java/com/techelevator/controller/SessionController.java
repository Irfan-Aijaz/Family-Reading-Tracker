package com.techelevator.controller;

import com.techelevator.dao.SessionDao;
import com.techelevator.model.Session;
import com.techelevator.model.SessionDTO;
import com.techelevator.model.SessionDetailsDTO;
import com.techelevator.model.SessionListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

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

    @RequestMapping(value = "/get_sessions_by_user_id/{user_id}", method = RequestMethod.GET)
    public List<SessionListDTO> getSessionsByUserId(@PathVariable("user_id") Long userId) {
        return sessionDao.getSessionsListByUserId(userId);
    }

    @RequestMapping(value = "/get_session_details_by_session_id/{session_id}", method=RequestMethod.GET)
    public SessionDetailsDTO getSessionDetailsBySessionId(@PathVariable("session_id") Long sessionId) {
        return sessionDao.getSessionDetailsBySessionId(sessionId);
    }
}
