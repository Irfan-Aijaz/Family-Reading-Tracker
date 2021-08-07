package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

	@Override
    public String getFamilyNameById(Long familyId){
        String sql = "SELECT family_name" +
                " FROM family " +
                "WHERE family_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        if(results.next()) {
            return results.getString("family_name");
        } else {
            throw new RuntimeException("userId "+familyId+" was not found.");
        }
    }

    @Override
    public List<UserDTO> getUserDTOsByFamilyId(Long familyId) {
        List<UserDTO> listOfUserDTOs = new ArrayList<>();
        String sql = "SELECT username, user_id, family_id FROM users WHERE family_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while (results.next()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(results.getLong("user_id"));
            userDTO.setFamilyId(results.getLong("family_id"));
            userDTO.setUsername(results.getString("username"));
            listOfUserDTOs.add(userDTO);

        }
        return listOfUserDTOs;
    }

    @Override
    public List<String> getUsernamesByFamilyId(Long familyId) {
        List<String> listOfUsernames = new ArrayList<>();
        String sql = "SELECT username " +
                "FROM users " +
                "WHERE family_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while(results.next()) {
            listOfUsernames.add(results.getString("username"));
        }

        return listOfUsernames;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public List<Long> getUserIdsByFamilyId(Long familyId) {
        List<Long> userIds = new ArrayList<>();
        String sql = "SELECT user_id FROM users WHERE family_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while(results.next()) {
            userIds.add(results.getLong("user_id"));
        }
        return userIds;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public Long createFamily(String familyName) {
        String insertFamily = "INSERT into family (family_name) values (?);";
        jdbcTemplate.update(insertFamily, familyName);
        String familyIdSelect = "SELECT family_id FROM family WHERE family_name = ?;";
        SqlRowSet results1 = jdbcTemplate.queryForRowSet(familyIdSelect, familyName);
        if (results1.next()) {
            return results1.getLong("family_id");
        }
        return Long.parseLong("0") ;
    }



    @Override
    public boolean create(String username, String password, String role, String familyName) {
        boolean userCreated = false;
        Long createdFamilyId = createFamily(familyName);
        // create user
        String insertUser = "insert into users (username,password_hash,role,family_id) values(?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    ps.setLong(4, createdFamilyId);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }

    @Override
    public boolean create(String username, String password, String role, Long familyId) {
        boolean userCreated = false;
        // create user
        String insertUser = "insert into users (username,password_hash,role,family_id) values(?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    ps.setLong(4, familyId);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setFamilyId(rs.getLong("family_id"));
        user.setActivated(true);
        return user;
    }
}
