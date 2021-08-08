package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionListDTO {

    private String title;
    private Long sessionId;
    private LocalDate daySession;
    private Long minutesRead;

    public SessionListDTO() {
    }

    public SessionListDTO(String title, Long sessionId, LocalDate daySession, Long minutesRead) {
        this.title = title;
        this.sessionId = sessionId;
        this.daySession = daySession;
        this.minutesRead = minutesRead;
    }

    public String getTitle() {
        return title;
    }

    public Long getMinutesRead() {
        return minutesRead;
    }

    public void setMinutesRead(Long minutesRead) {
        this.minutesRead = minutesRead;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDate getDaySession() {
        return daySession;
    }

    public void setDaySession(LocalDate daySession) {
        this.daySession = daySession;
    }

}
