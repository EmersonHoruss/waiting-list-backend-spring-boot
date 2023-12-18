package com.ownprojects.backend.DTOs.entities.patient;

import com.ownprojects.backend.DTOs.entities.BaseCreateDTO;
import com.ownprojects.backend.entities.Insurance;
import com.ownprojects.backend.entities.Patient;
import com.ownprojects.backend.entities.staticValues.EStaticValues;
import com.ownprojects.backend.entities.staticValues.StaticValues;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class PatientCreateDTO extends BaseCreateDTO<Patient> {
    @Size(min = 8, max = 8)
    private String document;

    @NotBlank
    @Size(max = 45)
    private String name;

    @NotNull
    private LocalDate birthdate;

    @Size(max = 6)
    private String clinicHistory;

    @Size(min = 1, max = 1)
    @NotBlank
    private String gender;

    @Size(min = 6, max = 6)
    private String phone;

    @Size(min = 9, max = 9)
    private String cell;

    @Size(max = 45)
    private String relationshipType;

    @Size(min = 3,max = 3)
    private String casAffiliation;

    @NotBlank
    @Size(max = 80)
    private String ubigeo;

    private Long idInsurance;
    @Override
    public Patient asEntity() {
        new StaticValues().validate(EStaticValues.GENDERS, this.getGender());
        new StaticValues().validate(EStaticValues.RELATIONSHIP_TYPES, this.getRelationshipType());
        Patient patient = new Patient();
        patient.setDocument(this.getDocument());
        patient.setBirthdate(this.getBirthdate());
        patient.setClinicHistory(this.getClinicHistory());
        patient.setGender(this.getGender());
        patient.setPhone(this.getPhone());
        patient.setCell(this.getCell());
        patient.setRelationshipType(this.getRelationshipType());
        patient.setCasAffiliation(this.getCasAffiliation());
        Insurance insurance = new Insurance();
        insurance.setId(this.getIdInsurance());
        patient.setInsurance(insurance);
        return patient;
    }
}
