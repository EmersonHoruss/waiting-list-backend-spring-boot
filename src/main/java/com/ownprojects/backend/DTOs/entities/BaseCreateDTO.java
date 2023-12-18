package com.ownprojects.backend.DTOs.entities;

import com.ownprojects.backend.entities.Base;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseCreateDTO <E extends Base> implements BaseDTO<E>{
}
