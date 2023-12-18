package com.ownprojects.backend.DTOs.entities.insurance;

import com.ownprojects.backend.DTOs.entities.BaseCreateDTO;
import com.ownprojects.backend.entities.Insurance;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InsuranceCreateDTO extends BaseCreateDTO<Insurance> {
    @NotBlank
    @Size(min = 1, max = 3)
    private String code;

    @NotBlank
    @Size(max = 45)
    private String Kind;
    @Override
    public Insurance asEntity() {
        Insurance insurance = new Insurance();
        insurance.setCode(this.getCode());
        insurance.setKind(this.getKind());
        return insurance;
    }
}
