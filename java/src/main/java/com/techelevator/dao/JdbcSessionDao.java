package com.techelevator.dao;

import com.techelevator.model.Session;
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
import java.util.ArrayList;
import java.util.List;

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
        String checkReading = "SELECT user_id, isbn " +
                "FROM user_book " +
                "WHERE user_id = ? AND isbn = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(checkReading, userId, isbn);

        if (result.next()) {
            String updateAUserBook = "UPDATE user_book SET pages_read = pages_read + ?, minutes_read = minutes_read + ? " +
                    "WHERE user_id = ? AND isbn = ?";
            jdbcTemplate.update(updateAUserBook, pagesRead, minutesRead, userId, isbn);
            updatedUserBook = true;
        } else {
            String insertUserBook = "INSERT INTO user_book (user_id, isbn, pages_read, minutes_read, completed) values (?,?,?,?,?)";
            String id_column = "user_id";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            updatedUserBook = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(insertUserBook, new String[]{id_column});
                ps.setLong(1,userId);
                ps.setString(2,isbn);
                ps.setLong(3,pagesRead);
                ps.setLong(4,minutesRead);
                ps.setBoolean(5,false);
                return ps;
            }
            , keyHolder) == 1;
        }
        return updatedUserBook;

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
