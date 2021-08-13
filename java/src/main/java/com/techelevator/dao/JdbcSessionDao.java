package com.techelevator.dao;

import com.techelevator.model.MinutesInDateRangeDTO;
import com.techelevator.model.Session;
import com.techelevator.model.SessionDetailsDTO;
import com.techelevator.model.SessionListDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class JdbcSessionDao implements SessionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Session> findAllSessionsInFamily(Long familyId) {
        List<Session> sessions = new ArrayList<>();
        String sql = "SELECT * FROM sessions " +
                     "INNER JOIN users ON users.user_id = sessions.user_id" +
                     "WHERE users.family_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while (results.next()) {
            sessions.add(mapRowToSession(results));
        }
        return sessions;
    }
    @Override
    public List<SessionListDTO> getSessionsListByUserId(Long userId) {
        List<SessionListDTO> sessions = new ArrayList<>();
        String sql = "SELECT books.title, sessions.session_id, sessions.time_start, sessions.time_end, sessions.day_session, users.username " +
                     "FROM sessions " +
                     "INNER JOIN books ON books.isbn = sessions.isbn " +
                     "INNER JOIN users ON users.user_id = sessions.user_id " +
                     "WHERE sessions.user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            sessions.add(mapRowToSessionListDTO(results));
        }
        return sessions;
    }

    @Override
    public SessionDetailsDTO getSessionDetailsBySessionId(Long sessionId){
        SessionDetailsDTO sessionDetailsDTO = new SessionDetailsDTO();
        String sql = "SELECT books.title, sessions.user_id, sessions.session_id, sessions.isbn, sessions.day_session, sessions.time_start, sessions.time_end, sessions.pages_read, sessions.format, sessions.notes " +
                     "FROM sessions " +
                     "INNER JOIN books ON books.isbn = sessions.isbn " +
                     "WHERE sessions.session_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sessionId);
        if (results.next()) {
            sessionDetailsDTO = mapRowToSessionDetailsDTO(results);
        }
        return sessionDetailsDTO;
    }

    @Override
    public MinutesInDateRangeDTO getMinutesInDateRangeDTO(LocalDate startDate, LocalDate endDate, Long userId){
        MinutesInDateRangeDTO minutesInDateRangeDTO = new MinutesInDateRangeDTO();
        Long minutesRead = Long.valueOf(0);
        String sql = "SELECT time_end, time_start " +
                     "FROM sessions " +
                     "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Long minutesInSession = results.getTime("time_end").toLocalTime().until(results.getTime("time_start").toLocalTime(), MINUTES);
            minutesRead -= minutesInSession;
        }
        minutesInDateRangeDTO.setEndDate(endDate);
        minutesInDateRangeDTO.setStartDate(startDate);
        minutesInDateRangeDTO.setMinutes(minutesRead);

        return minutesInDateRangeDTO;
    }

    @Override
    public List<SessionListDTO> getSessionsListByFamilyId(Long familyId){
        List<SessionListDTO> sessions = new ArrayList<>();
        String sql = "SELECT books.title, sessions.session_id, sessions.time_start, sessions.time_end, sessions.day_session, users.username " +
                     "FROM sessions " +
                     "INNER JOIN books ON books.isbn = sessions.isbn " +
                     "INNER JOIN users ON users.user_id = sessions.user_id " +
                     "WHERE users.family_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while (results.next()) {
            sessions.add(mapRowToSessionListDTO(results));
        }
        return sessions;


    }

    @Override
    public boolean createSession(String isbn, Long userId, LocalDate daySession, LocalTime timeStart, LocalTime timeEnd, Long pagesRead, String format, String notes) {
        boolean sessionCreated = false;

        String insertSession = "insert into sessions (isbn, user_id, day_session, time_start, time_end, pages_read, format, notes) values (?, ?, ?, ?, ?, ?, ?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "session_id";
        sessionCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertSession, new String[]{id_column});
            ps.setString(1, isbn);
            ps.setLong(2, userId);
            ps.setDate(3, Date.valueOf(daySession));
            ps.setTime(4, Time.valueOf(timeStart));
            ps.setTime(5, Time.valueOf(timeEnd));
            ps.setLong(6, pagesRead);
            ps.setString(7, format);
            ps.setString(8, notes);
            return ps;
        }
        , keyHolder)==1;

        return sessionCreated;
    }


    @Override
    public boolean updateUserBook(Long userId, String isbn, Long pagesRead, Long minutesRead) {
        boolean updatedUserBook = false;

        String checkPagesTotal = "SELECT pages_total FROM books WHERE isbn = ?;";
        SqlRowSet totalPages = jdbcTemplate.queryForRowSet(checkPagesTotal, isbn);

        String checkUserBookPages = "SELECT pages_read " +
                "FROM user_book " +
                "WHERE user_id = ? AND isbn = ?;";
        SqlRowSet userBookPages = jdbcTemplate.queryForRowSet(checkUserBookPages, userId, isbn);


        boolean completedStatusTemp = false;
        if (totalPages.next()) {
            if (userBookPages.next()) {
                completedStatusTemp = (pagesRead+userBookPages.getLong("pages_read")) >= totalPages.getLong("pages_total");
            }
        }

        final boolean completedStatus = completedStatusTemp;

        String checkUserBookIdIsbn = "SELECT user_id, isbn " +
                "FROM user_book " +
                "WHERE user_id = ? AND isbn = ?;";
        SqlRowSet userBookFound = jdbcTemplate.queryForRowSet(checkUserBookIdIsbn, userId, isbn);

        if (userBookFound.next()) {
            String updateAUserBook = "UPDATE user_book SET pages_read = pages_read + ?, minutes_read = minutes_read + ?, completed = ? " +
                    "WHERE user_id = ? AND isbn = ?";
            if (totalPages.next()) {
                if (userBookPages.next()) {
                    if (pagesRead+userBookPages.getLong("pages_read")>= totalPages.getLong("pages_total")) {
                        pagesRead = totalPages.getLong("pages_total");
                    }
                }
            }
            jdbcTemplate.update(updateAUserBook, pagesRead, minutesRead, completedStatus, userId, isbn);
            updatedUserBook = true;
        } else {
            String insertUserBook = "INSERT INTO user_book (user_id, isbn, pages_read, minutes_read, completed) values (?,?,?,?,?)";
            String id_column = "user_id";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            Long finalPagesRead = pagesRead;
            updatedUserBook = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(insertUserBook, new String[]{id_column});
                ps.setLong(1,userId);
                ps.setString(2,isbn);
                ps.setLong(3, finalPagesRead);
                ps.setLong(4,minutesRead);
                ps.setBoolean(5,completedStatus);
                return ps;
            }
            , keyHolder) == 1;
        }
        return updatedUserBook;

    }

    private SessionListDTO mapRowToSessionListDTO(SqlRowSet rowSet) {
        SessionListDTO sessionListDTO = new SessionListDTO();
        sessionListDTO.setSessionId(rowSet.getLong("session_id"));
        sessionListDTO.setMinutesRead(MINUTES.between(rowSet.getTime("time_start").toLocalTime(),rowSet.getTime("time_end").toLocalTime()));
        sessionListDTO.setTitle(rowSet.getString("title"));
        sessionListDTO.setDaySession(rowSet.getDate("day_session").toLocalDate());
        sessionListDTO.setUsername(rowSet.getString("username"));
        return sessionListDTO;
    }

    private SessionDetailsDTO mapRowToSessionDetailsDTO(SqlRowSet rowSet) {
        SessionDetailsDTO sessionDetailsDTO = new SessionDetailsDTO();
        sessionDetailsDTO.setMinutesRead(MINUTES.between(rowSet.getTime("time_start").toLocalTime(),rowSet.getTime("time_end").toLocalTime()));
        sessionDetailsDTO.setTitle(rowSet.getString("title"));
        sessionDetailsDTO.setSessionId(rowSet.getLong("session_id"));
        sessionDetailsDTO.setUserId(rowSet.getLong("user_id"));
        sessionDetailsDTO.setIsbn(rowSet.getString("isbn"));
        sessionDetailsDTO.setDaySession(rowSet.getDate("day_session").toLocalDate());
        sessionDetailsDTO.setTimeStart(rowSet.getTime("time_start").toLocalTime());
        sessionDetailsDTO.setTimeEnd(rowSet.getTime("time_end").toLocalTime());
        sessionDetailsDTO.setPagesRead(rowSet.getLong("pages_read"));
        sessionDetailsDTO.setFormat(rowSet.getString("format"));
        sessionDetailsDTO.setNotes(rowSet.getString("notes"));

        return sessionDetailsDTO;
    }

    private Session mapRowToSession(SqlRowSet rowSet) {
        Session session = new Session();
        session.setSessionId(rowSet.getLong("session_id"));
        session.setUserId(rowSet.getLong("user_id"));
        session.setIsbn(rowSet.getString("isbn"));
        session.setDaySession(rowSet.getDate("day_session").toLocalDate());
        session.setTimeStart(rowSet.getTime("time_start").toLocalTime());
        session.setTimeEnd(rowSet.getTime("time_end").toLocalTime());
        session.setPagesRead(rowSet.getLong("pages_read"));
        session.setFormat(rowSet.getString("format"));
        session.setNotes(rowSet.getString("notes"));

        return session;
    }
}
