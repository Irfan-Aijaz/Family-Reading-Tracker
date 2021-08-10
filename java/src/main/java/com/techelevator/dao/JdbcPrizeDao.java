package com.techelevator.dao;

import com.techelevator.model.Prize;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcPrizeDao implements PrizeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }


    @Override
    public boolean createPrize(String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd, Long familyId) {
        boolean prizeCreated = false;
        String insertPrize = "INSERT INTO prizes (prize_name, description, milestone_minutes, user_group, max_prizes, date_start, date_end, family_id) " +
                "VALUES (?,?,?,?,?,?,?,?);";
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
                    ps.setLong(8, familyId);
                    return ps;
                }
                , keyHolder) == 1;

        return prizeCreated;
    }

    @Override
    public boolean updatePrize(Long prizeId, String prizeName, String prizeDescription, Long milestoneMinutes, String userGroup, Long maxPrizes, LocalDate dateStart, LocalDate dateEnd) {
        boolean prizeUpdated = false;

        String sql = "UPDATE prizes SET prize_name = ?, description = ?, milestone_minutes = ?, user_group = ?, max_prizes = ?, date_start = ?, date_end = ? " +
                "WHERE prize_id = ?;";
        jdbcTemplate.update(sql, prizeName, prizeDescription, milestoneMinutes, userGroup, maxPrizes, dateStart, dateEnd, prizeId);
        prizeUpdated = true;

        return prizeUpdated;
    }
    @Override
    public List<Prize> getPrizesForFamilyAndUserGroup(Long familyId, String userGroup){
        List<Prize> prizes = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM prizes " +
                     "WHERE family_id = ? AND user_group = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId, userGroup);
        while (results.next()) {
            prizes.add(mapRowToPrize(results));
        }
        return prizes;

    }

    private Prize mapRowToPrize(SqlRowSet rowSet) {
        Prize prize = new Prize();
        prize.setPrizeId(rowSet.getLong("prize_id"));
        prize.setPrizeName(rowSet.getString("prize_name"));
        prize.setPrizeDescription(rowSet.getString("description"));
        prize.setMilestoneMinutes(rowSet.getLong("milestone_minutes"));
        prize.setUserGroup(rowSet.getString("user_group"));
        prize.setMaxPrizes(rowSet.getLong("max_prizes"));
        prize.setDateStart(rowSet.getDate("date_start").toLocalDate());
        prize.setDateEnd(rowSet.getDate("date_end").toLocalDate());
        prize.setFamilyId(rowSet.getLong("family_id"));

        return prize;
    }
}
