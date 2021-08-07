package com.techelevator.model;

public class UserDTO {
    private String username;
    private Long userId;
    private Long familyId;

    public UserDTO() {
    }

    public UserDTO(String username, Long userId, Long familyId) {
        this.username = username;
        this.userId = userId;
        this.familyId = familyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
}
