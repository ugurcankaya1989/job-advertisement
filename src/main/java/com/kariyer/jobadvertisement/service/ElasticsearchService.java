package com.kariyer.jobadvertisement.service;

import com.kariyer.jobadvertisement.dto.JobAdvertisementDTO;

import java.util.List;

public interface ElasticsearchService {
    JobAdvertisementDTO pushDataToElasticIndex(JobAdvertisementDTO jobAdvertisementDTO);
    List<JobAdvertisementDTO> searchJobAdvByDate(String date);
    List<JobAdvertisementDTO> searchJobAdvByEmployerId(Long id);
}
