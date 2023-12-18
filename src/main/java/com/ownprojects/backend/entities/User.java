package com.ownprojects.backend.entities;

import com.ownprojects.backend.DTOs.entities.user.UserShowDTO;
import com.ownprojects.backend.constants.EntityConstraints;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
        name = "user",
        uniqueConstraints = @UniqueConstraint(name = EntityConstraints.UK + "name", columnNames = "name")
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends Base<UserShowDTO> {
    @Column(name = "role", nullable = false, unique = false, length = 45)
    private String role;
    @Column(name = "name", nullable = false, unique = true, length = 45)
    private String name;
    @Column(name = "password", nullable = false, unique = false, length = 45)
    private String password;

    @Override
    public UserShowDTO asShowDTO() {
        UserShowDTO userShowDTO = new UserShowDTO();
        userShowDTO.setId(this.getId());
        userShowDTO.setRole(this.getRole());
        userShowDTO.setName(this.getName());
        userShowDTO.setPassword(this.getPassword());
        return userShowDTO;
    }
}
