package com.ownprojects.backend.controllers;

import com.ownprojects.backend.DTOs.entities.insurance.InsuranceCreateDTO;
import com.ownprojects.backend.DTOs.entities.insurance.InsuranceShowDTO;
import com.ownprojects.backend.DTOs.entities.insurance.InsuranceUpdateDTO;
import com.ownprojects.backend.constants.Endpoints;
import com.ownprojects.backend.entities.Insurance;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Endpoints.INSURANCES)
public class InsuranceController extends BaseController<Insurance, InsuranceCreateDTO, InsuranceShowDTO, InsuranceUpdateDTO> {
}
