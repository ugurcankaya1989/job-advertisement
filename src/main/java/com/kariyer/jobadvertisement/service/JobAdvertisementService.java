package com.kariyer.jobadvertisement.service;

import com.kariyer.jobadvertisement.dto.JobAdvertisementDTO;

import java.util.List;

public interface JobAdvertisementService {
    JobAdvertisementDTO saveNewJobAdvertisement(JobAdvertisementDTO jobAdvertisementDTO);
    JobAdvertisementDTO getJobAdvertisementById(Long id);
    List<JobAdvertisementDTO> getAllJobAdvertisement();
    boolean isEmployerHaveRightToPostAnAdv(Long id);
}
