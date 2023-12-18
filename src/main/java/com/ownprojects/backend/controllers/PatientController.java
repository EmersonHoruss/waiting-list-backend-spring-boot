package com.ownprojects.backend.controllers;

import com.ownprojects.backend.DTOs.entities.patient.PatientCreateDTO;
import com.ownprojects.backend.DTOs.entities.patient.PatientShowDTO;
import com.ownprojects.backend.DTOs.entities.patient.PatientUpdateDTO;
import com.ownprojects.backend.constants.Endpoints;
import com.ownprojects.backend.entities.Patient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Endpoints.PATIENTS)
public class PatientController extends BaseController<Patient, PatientCreateDTO, PatientShowDTO, PatientUpdateDTO> {
}
