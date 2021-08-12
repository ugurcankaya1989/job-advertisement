package com.kariyer.jobadvertisement.service;

import com.kariyer.jobadvertisement.dto.EmployerDTO;

import java.util.List;

public interface EmployerService {
    EmployerDTO saveNewEmployer(EmployerDTO employerDTO);
    EmployerDTO getEmployerById(Long id);
    List<EmployerDTO> getAllEmployer();
    void updateJobAdvCountByEmployerId(Long id);
    int getAdvCountByEmployerId(Long id);
    boolean isPhoneNumberAvailable(String phoneNumber);
}
