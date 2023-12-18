package com.ownprojects.backend.DTOs.entities;

import com.ownprojects.backend.entities.Base;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseShowDTO <E extends Base> {
    private Long id;
}
