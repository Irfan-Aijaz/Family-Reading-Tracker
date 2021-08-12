package com.techelevator.dao;

import com.techelevator.model.ClaimedPrize;
import com.techelevator.model.Prize;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcPrizeDao implements PrizeDao {

    private JdbcTemplate jdbcTemplate;
    private JdbcUserDao jdbcUserDao;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate, JdbcUserDao jdbcUserDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcUserDao = jdbcUserDao;
    }


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
    public boolean createClaimPrizeRequestForChild(Long prizeId, Long childId){
        boolean claimCreated = false;
        Prize prize = getPrizeByPrizeId(prizeId);
        String sql = "INSERT INTO claimed_prizes (prize_id, claim_prize_request_status_id, user_id, description, milestone_minutes, date_claimed, family_id, username) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "prize_claim_id";

        claimCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{id_column});
            ps.setLong(1, prizeId);
            ps.setLong(2, 1);
            ps.setLong(3, childId);
            ps.setString(4, prize.getPrizeDescription());
            ps.setLong(5, prize.getMilestoneMinutes());
            ps.setDate(6, Date.valueOf(LocalDateTime.now().toLocalDate()));
            ps.setLong(7, prize.getFamilyId());
            ps.setString(8, jdbcUserDao.getUserDTOByUserId(childId).getUsername());
            return ps;

        }
        , keyHolder) == 1;

        return claimCreated;

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
    public Prize getPrizeByPrizeId(Long prizeId){
        Prize prize = new Prize();
        String sql = "SELECT * " +
                     "FROM prizes " +
                     "WHERE prize_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, prizeId);
        if (results.next()) {
            prize = mapRowToPrize(results);
        }
        return prize;
    }

    @Override
    public List<Prize> getPrizesForFamilyAndUserGroup(Long familyId, String userGroup){
        List<Prize> prizes = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM prizes " +
                     "WHERE family_id = ? AND (user_group = ? OR user_group = 'BOTH');";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId, userGroup);
        while (results.next()) {
            prizes.add(mapRowToPrize(results));
        }
        return prizes;

    }

    @Override
    public List<ClaimedPrize> getPrizeClaimsByFamilyId(Long familyId){
        List<ClaimedPrize> claims = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM claimed_prizes " +
                     "WHERE family_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while (results.next()) {
            claims.add(mapRowToClaim(results));
        }
        return claims;
    }

    @Override
    public boolean removeInactivePrize(Long prizeId) {
        boolean prizeRemoved = false;

        String sql = "DELETE FROM prizes " +
                "WHERE prize_id = ?;";
        int result = jdbcTemplate.update(sql,prizeId);

        if (result==1) {
            prizeRemoved = true;
        }

        return prizeRemoved;
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

    private ClaimedPrize mapRowToClaim(SqlRowSet rowSet) {
        ClaimedPrize claim = new ClaimedPrize();
        claim.setClaimedPrizeId(rowSet.getLong("prize_claim_id"));
        claim.setPrizeId(rowSet.getLong("prize_id"));
        claim.setClaimPrizeRequestStatusId(rowSet.getLong("claim_prize_request_status_id"));
        claim.setUserId(rowSet.getLong("user_id"));
        claim.setDescription(rowSet.getString("description"));
        claim.setMilestoneMinutes(rowSet.getLong("milestone_minutes"));
        claim.setDateClaimed(rowSet.getDate("date_claimed").toLocalDate());
        claim.setFamilyId(rowSet.getLong("family_id"));
        claim.setUsername(rowSet.getString("username"));
        if (rowSet.getDate("date_approved_rejected") != null ) {
            claim.setDateApprovedRejected(rowSet.getDate("date_approved_rejected").toLocalDate());

        }
        return claim;
    }
}
