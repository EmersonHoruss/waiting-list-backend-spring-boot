package com.ownprojects.backend.DTOs.entities.appointment;

import com.ownprojects.backend.DTOs.entities.BaseCreateDTO;
import com.ownprojects.backend.entities.Appointment;
import com.ownprojects.backend.entities.Patient;
import com.ownprojects.backend.entities.staticValues.EStaticValues;
import com.ownprojects.backend.entities.staticValues.StaticValues;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentCreateDTO extends BaseCreateDTO<Appointment> {
    private String service;
    private String state;
    private Long idPatient;
    public Appointment asEntity() {
        new StaticValues().validate(EStaticValues.SERVICES, this.getService());
        new StaticValues().validate(EStaticValues.APPOINTMENT_STATES, this.getState());
        Appointment appointment = new Appointment();
        appointment.setService(this.getService());
        appointment.setState(this.getState());
        Patient patient = new Patient();
        patient.setId(this.getIdPatient());
        appointment.setPatient(patient);
        return appointment;
    }
}
