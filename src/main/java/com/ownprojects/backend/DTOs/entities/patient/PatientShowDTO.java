package com.ownprojects.backend.DTOs.entities.patient;

import com.ownprojects.backend.DTOs.entities.BaseShowDTO;
import com.ownprojects.backend.DTOs.entities.insurance.InsuranceShowDTO;
import com.ownprojects.backend.entities.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientShowDTO extends BaseShowDTO<Patient> {
    private String document;
    private String name;
    private LocalDate birthdate;
    private String clinicHistory;
    private String gender;
    private String phone;
    private String cell;
    private String relationshipType;
    private String casAffiliation;
    private InsuranceShowDTO insurance;
}
