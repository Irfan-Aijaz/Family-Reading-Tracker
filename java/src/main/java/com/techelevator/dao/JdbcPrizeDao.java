package com.techelevator.dao;

import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcPrizeDao implements PrizeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }


    @Override
    public boolean createPrize(String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd) {
        boolean prizeCreated = false;
        String insertPrize = "INSERT INTO prizes (prize_name, description, milestone_minutes, user_group, max_prizes, date_start, date_end) " +
                "VALUES (?,?,?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "prize_id";

        prizeCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertPrize, new String[]{id_column});
                    ps.setString(1, prizeName);
                    ps.setString(2, prizeDescription);
                    ps.setLong(3, milestoneMinutes);
                    ps.setString(4,userGroup);
                    ps.setLong(5,maxPrizes);
                    ps.setDate(6, Date.valueOf(dateStart));
                    ps.setDate(7, Date.valueOf(dateEnd));
                    return ps;
                }
                , keyHolder) == 1;

        return prizeCreated;
    }
}
