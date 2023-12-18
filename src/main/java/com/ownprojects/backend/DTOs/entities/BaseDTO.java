package com.ownprojects.backend.DTOs.entities;

import com.ownprojects.backend.entities.Base;

public interface BaseDTO<E extends Base> {
    public E asEntity();
}
