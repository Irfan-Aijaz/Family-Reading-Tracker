package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.UserDTO;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, String familyName);

    boolean create(String username, String password, String role, Long familyId);

    Long createFamily(String familyName);

    String getFamilyNameById(Long familyId);

    List<String> getUsernamesByFamilyId(Long familyId);

    List<Long> getUserIdsByFamilyId(Long familyId);

    List<UserDTO> getUserDTOsByFamilyId(Long familyId);

    UserDTO getUserDTOByUserId(Long userId);
}
