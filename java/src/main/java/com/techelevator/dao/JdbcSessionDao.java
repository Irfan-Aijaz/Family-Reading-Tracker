package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class JdbcSessionDao implements SessionDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcSessionDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean createSession(String isbn, Long userId, LocalDate dayOfSession, LocalTime startTime, LocalTime endTime, String format, String notes) {
        boolean sessionCreated = false;

        String insertSession = "insert into sessions (isbn, user_id, day_session, time_start, time_end, format, notes) values (?, ?, ?, ?, ?, ?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "session_id";
        sessionCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertSession, new String[]{id_column});
            ps.setString(1, isbn);
            ps.setLong(2, userId);
            ps.setDate(3, Date.valueOf(dayOfSession));
            ps.setTime(4, Time.valueOf(startTime));
            ps.setTime(5, Time.valueOf(endTime));
            ps.setString(6, format);
            ps.setString(7, notes);
            return ps;
        }
        , keyHolder)==1;

        return sessionCreated;
    }
}
