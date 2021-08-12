package com.kariyer.jobadvertisement.service.impl;

import com.kariyer.jobadvertisement.adapter.JobAdvertisementAdapter;
import com.kariyer.jobadvertisement.dto.JobAdvertisementDTO;
import com.kariyer.jobadvertisement.model.JobAdvertisement;
import com.kariyer.jobadvertisement.repository.JobAdvertisementRepository;
import com.kariyer.jobadvertisement.service.EmployerService;
import com.kariyer.jobadvertisement.service.ForbiddenWordService;
import com.kariyer.jobadvertisement.service.JobAdvertisementService;
import com.kariyer.jobadvertisement.util.QualityCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final JobAdvertisementAdapter jobAdvertisementAdapter;
    private final EmployerService employerService;
    private final QualityCalculator qualityCalculator;


    public JobAdvertisementServiceImpl(JobAdvertisementRepository jobAdvertisementRepository, JobAdvertisementAdapter jobAdvertisementAdapter, EmployerService employerService, QualityCalculator qualityCalculator) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.jobAdvertisementAdapter = jobAdvertisementAdapter;
        this.employerService = employerService;
        this.qualityCalculator = qualityCalculator;
    }

    @Override
    public JobAdvertisementDTO saveNewJobAdvertisement(JobAdvertisementDTO jobAdvertisementDTO) {
        jobAdvertisementDTO.setAdvQuality(qualityCalculator.calculateQuality(jobAdvertisementDTO));
        jobAdvertisementDTO.setAirtime(LocalDateTime.now());
        JobAdvertisement jobAdvertisement = jobAdvertisementRepository.save(jobAdvertisementAdapter.mapToJobAdvertisement(jobAdvertisementDTO));
        if(Objects.nonNull(jobAdvertisement))
            employerService.updateJobAdvCountByEmployerId(jobAdvertisement.getEmployer().getId());
        return jobAdvertisementAdapter.mapToJobAdvertisementDTO(jobAdvertisement);
    }

    @Override
    public JobAdvertisementDTO getJobAdvertisementById(Long id) {
        return jobAdvertisementAdapter.mapToJobAdvertisementDTO(jobAdvertisementRepository.findById(id).get());
    }

    @Override
    public List<JobAdvertisementDTO> getAllJobAdvertisement() {
        return jobAdvertisementAdapter.mapToJobAdvertisementDTOList(jobAdvertisementRepository.findAll());
    }

    @Override
    public boolean isEmployerHaveRightToPostAnAdv(Long id) {
        return employerService.getAdvCountByEmployerId(id) != 0;
    }
}
