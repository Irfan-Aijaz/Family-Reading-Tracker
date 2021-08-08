package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionDetailsDTO {

    private String title;
    private Long sessionId;
    private Long userId;
    private String isbn;
    private LocalDate daySession;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Long pagesRead;
    private String format;
    private String notes;
    private Long minutesRead;

    public SessionDetailsDTO(String title, Long sessionId, Long userId, String isbn, LocalDate daySession, LocalTime timeStart, LocalTime timeEnd, Long pagesRead, String format, String notes, Long minutesRead) {
        this.title = title;
        this.sessionId = sessionId;
        this.userId = userId;
        this.isbn = isbn;
        this.daySession = daySession;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.pagesRead = pagesRead;
        this.format = format;
        this.notes = notes;
        this.minutesRead = minutesRead;
    }

    public SessionDetailsDTO() {
    }

    public Long getMinutesRead() {
        return minutesRead;
    }

    public void setMinutesRead(Long minutesRead) {
        this.minutesRead = minutesRead;
    }

    public String getTitle() {
        return title;
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

    public Long getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(Long pagesRead) {
        this.pagesRead = pagesRead;
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
