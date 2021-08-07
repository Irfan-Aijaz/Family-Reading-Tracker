package com.techelevator.dao;

import com.techelevator.model.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SessionDao {

    boolean createSession(String isbn, Long userId, LocalDate dayOfSession, LocalTime startTime, LocalTime endTime, Long pagesRead, String format, String notes);

    List<Session> findAllSessionsInFamily(Long familyId);

}
