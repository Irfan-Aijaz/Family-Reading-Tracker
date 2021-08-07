package com.techelevator.dao;

import com.techelevator.model.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
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
    public boolean createSession(String isbn, Long userId, LocalDate daySession, LocalTime timeStart, LocalTime timeEnd, String format, String notes) {
        boolean sessionCreated = false;

        String insertSession = "insert into sessions (isbn, user_id, day_session, time_start, time_end, format, notes) values (?, ?, ?, ?, ?, ?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "session_id";
        sessionCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertSession, new String[]{id_column});
            ps.setString(1, isbn);
            ps.setLong(2, userId);
            ps.setDate(3, Date.valueOf(daySession));
            ps.setTime(4, Time.valueOf(timeStart));
            ps.setTime(5, Time.valueOf(timeEnd));
            ps.setString(6, format);
            ps.setString(7, notes);
            return ps;
        }
        , keyHolder)==1;

        return sessionCreated;
    }

    private Session mapRowToSession(SqlRowSet rowSet) {
        Session session = new Session();
        session.setSessionId(rowSet.getLong("session_id"));
        session.setUserId(rowSet.getLong("user_id"));
        session.setIsbn(rowSet.getString("isbn"));
        session.setDaySession(rowSet.getDate("day_session").toLocalDate());
        session.setTimeStart(rowSet.getTime("time_start").toLocalTime());
        session.setTimeEnd(rowSet.getTime("time_end").toLocalTime());
        session.setFormat(rowSet.getString("format"));
        session.setNotes(rowSet.getString("notes"));

        return session;
    }
}
