package com.techelevator.dao;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SessionDao {

    boolean createSession(String isbn, Long userId, LocalDate dayOfSession, LocalTime startTime, LocalTime endTime, String format, String notes);

}
