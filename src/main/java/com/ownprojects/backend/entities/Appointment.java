package com.ownprojects.backend.entities;

import com.ownprojects.backend.DTOs.entities.appointment.AppointmentShowDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment extends Base<AppointmentShowDTO> {
    @Column(name = "service", nullable = false, unique = false, length = 45)
    private String service;

    @Column(name = "state", nullable = false, unique = false, length = 25)
    private String state;

    @ManyToOne(optional = false)
    private Patient patient;

    @Override
    public AppointmentShowDTO asShowDTO() {
        AppointmentShowDTO appointmentShowDTO = new AppointmentShowDTO();
        appointmentShowDTO.setId(this.getId());
        appointmentShowDTO.setService(this.getService());
        appointmentShowDTO.setState(this.getState());
        appointmentShowDTO.setPatient(this.getPatient().asShowDTO());
        return appointmentShowDTO;
    }
}
