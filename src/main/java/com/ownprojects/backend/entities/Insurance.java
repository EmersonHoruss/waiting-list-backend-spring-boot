package com.ownprojects.backend.entities;

import com.ownprojects.backend.DTOs.entities.insurance.InsuranceShowDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "insurance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Insurance extends Base<InsuranceShowDTO> {
    @Column(name = "code", nullable = false, unique = true, length = 3)
    private String code;

    @Column(name = "kind", nullable = false, unique = true, length = 45)
    private String kind;

    @Override
    public InsuranceShowDTO asShowDTO() {
        InsuranceShowDTO insuranceShowDTO = new InsuranceShowDTO();
        insuranceShowDTO.setId(this.getId());
        insuranceShowDTO.setCode(this.getCode());
        insuranceShowDTO.setKind(this.getKind());
        return insuranceShowDTO;
    }
}
