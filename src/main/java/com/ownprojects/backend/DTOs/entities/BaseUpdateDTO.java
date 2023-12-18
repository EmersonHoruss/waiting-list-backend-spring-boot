package com.ownprojects.backend.DTOs.entities;

import com.ownprojects.backend.entities.Base;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class BaseUpdateDTO <E extends Base> implements BaseDTO<E> {
    @NotNull
    private Long id;
}
