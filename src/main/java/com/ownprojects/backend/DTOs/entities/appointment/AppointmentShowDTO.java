package com.ownprojects.backend.DTOs.entities.appointment;

import com.ownprojects.backend.DTOs.entities.BaseShowDTO;
import com.ownprojects.backend.DTOs.entities.patient.PatientShowDTO;
import com.ownprojects.backend.entities.Appointment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentShowDTO extends BaseShowDTO<Appointment> {
    private String service;
    private String state;
    private PatientShowDTO patient;
}
