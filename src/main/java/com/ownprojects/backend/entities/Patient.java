package com.ownprojects.backend.entities;

import com.ownprojects.backend.DTOs.entities.patient.PatientShowDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Base<PatientShowDTO> {
    @Column(name = "document", nullable = false, unique = true, length = 8)
    private String document;

    @Column(name = "name", nullable = false, unique = true, length = 45)
    private String name;

    @Column(name = "birthdate", nullable = false, unique = false)
    private LocalDate birthdate;

    @Column(name = "clinicHistory", nullable = true, unique = false, length = 6)
    private String clinicHistory;

    @Column(name = "gender", nullable = true, unique = false, length = 1)
    private String gender;

    @Column(name = "phone", nullable = true, unique = false, length = 6)
    private String phone;

    @Column(name = "cell", nullable = true, unique = false, length = 9)
    private String cell;

    @Column(name = "relationshipType", nullable = true, unique = false, length = 45)
    private String relationshipType;

    @Column(name = "casAffiliation", nullable = true, unique = false, length = 3)
    private String casAffiliation;

    @Column(name = "ubigeo", nullable = true, unique = false, length = 80)
    private String ubigeo;

    @ManyToOne
    private Insurance insurance;

    @Override
    public PatientShowDTO asShowDTO() {
        PatientShowDTO patientShowDTO = new PatientShowDTO();
        patientShowDTO.setId(this.getId());
        patientShowDTO.setDocument(this.getDocument());
        patientShowDTO.setBirthdate(this.getBirthdate());
        patientShowDTO.setClinicHistory(this.getClinicHistory());
        patientShowDTO.setGender(this.getGender());
        patientShowDTO.setPhone(this.getPhone());
        patientShowDTO.setCell(this.getCell());
        patientShowDTO.setRelationshipType(this.getRelationshipType());
        patientShowDTO.setCasAffiliation(this.getCasAffiliation());
        patientShowDTO.setInsurance(this.getInsurance().asShowDTO());
        return patientShowDTO;
    }
}
