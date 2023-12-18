package com.ownprojects.backend.DTOs.entities.insurance;

import com.ownprojects.backend.DTOs.entities.BaseShowDTO;
import com.ownprojects.backend.entities.Insurance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsuranceShowDTO extends BaseShowDTO<Insurance> {
    private String code;
    private String kind;
}
