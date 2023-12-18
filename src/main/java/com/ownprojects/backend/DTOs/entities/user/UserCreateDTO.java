package com.ownprojects.backend.DTOs.entities.user;

import com.ownprojects.backend.DTOs.entities.BaseCreateDTO;
import com.ownprojects.backend.entities.User;
import com.ownprojects.backend.entities.staticValues.EStaticValues;
import com.ownprojects.backend.entities.staticValues.StaticValues;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCreateDTO extends BaseCreateDTO<User> {
    @NotBlank
    @Size(max = 25)
    private String role;
    @NotBlank
    @Size(max = 45)
    private String name;
    @NotBlank
    @Size(max = 45)
    private String password;
    @Override
    public User asEntity() {
        new StaticValues().validate(EStaticValues.ROLES, this.getRole());
        User user = new User();
        user.setRole(this.getRole());
        user.setName(this.getName());
        user.setPassword(this.getPassword());
        return user;
    }
}
