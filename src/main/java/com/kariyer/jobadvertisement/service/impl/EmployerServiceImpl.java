package com.kariyer.jobadvertisement.service.impl;

import com.kariyer.jobadvertisement.adapter.EmployerAdapter;
import com.kariyer.jobadvertisement.dto.EmployerDTO;
import com.kariyer.jobadvertisement.repository.EmployerRepository;
import com.kariyer.jobadvertisement.service.EmployerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;
    private final EmployerAdapter employerAdapter;

    public EmployerServiceImpl(EmployerRepository employerRepository, EmployerAdapter employerAdapter) {
        this.employerRepository = employerRepository;
        this.employerAdapter = employerAdapter;
    }

    @Override
    public EmployerDTO saveNewEmployer(EmployerDTO employerDTO) {
        return employerAdapter.mapToEmployerDTO(employerRepository.save(employerAdapter.mapToEmployer(employerDTO)));
    }

    @Override
    public EmployerDTO getEmployerById(Long id) {
        return employerAdapter.mapToEmployerDTO(employerRepository.getById(id));
    }

    @Override
    public List<EmployerDTO> getAllEmployer() {
        return employerAdapter.mapToEmployerDTOList(employerRepository.findAll());
    }

    @Override
    public void updateJobAdvCountByEmployerId(Long id) {
        employerRepository.decreaseJobAdvertisementByEmployer(id);
    }

    @Override
    public int getAdvCountByEmployerId(Long id) {
        return employerRepository.getJobAdvCountByEmployer(id);
    }

    @Override
    public boolean isPhoneNumberAvailable(String phoneNumber) {
        return Objects.nonNull(employerRepository.findEmployerByPhoneNumber(phoneNumber));
    }
}
