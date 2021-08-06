package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Session {
    private Long sessionId;
    private Long userId;
    private String isbn;
    private LocalDate daySession;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private String format;
    private String notes;

    public Session(){};

    public Session(Long sessionId, Long userId, String isbn, LocalDate daySession, LocalTime timeStart, LocalTime timeEnd, String format, String notes) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.isbn = isbn;
        this.daySession = daySession;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.format = format;
        this.notes = notes;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDaySession() {
        return daySession;
    }

    public void setDaySession(LocalDate daySession) {
        this.daySession = daySession;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
