package com.ownprojects.backend.DTOs.entities.user;

import com.ownprojects.backend.DTOs.entities.BaseShowDTO;
import com.ownprojects.backend.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShowDTO extends BaseShowDTO<User> {
    private String role;
    private String name;
    private String password;
}
