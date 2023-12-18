package com.ownprojects.backend.controllers;

import com.ownprojects.backend.DTOs.entities.appointment.AppointmentCreateDTO;
import com.ownprojects.backend.DTOs.entities.appointment.AppointmentShowDTO;
import com.ownprojects.backend.DTOs.entities.appointment.AppointmentUpdateDTO;
import com.ownprojects.backend.constants.Endpoints;
import com.ownprojects.backend.entities.Appointment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Endpoints.APPOINTMENTS)
public class AppointmentController extends BaseController<Appointment, AppointmentCreateDTO, AppointmentShowDTO, AppointmentUpdateDTO>{
}
