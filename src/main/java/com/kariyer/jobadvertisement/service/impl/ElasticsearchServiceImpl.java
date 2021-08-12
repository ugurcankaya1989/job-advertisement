package com.kariyer.jobadvertisement.service.impl;

import com.kariyer.jobadvertisement.dto.JobAdvertisementDTO;
import com.kariyer.jobadvertisement.service.ElasticsearchService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//Elastic Service
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {


    @Override
    public JobAdvertisementDTO pushDataToElasticIndex(JobAdvertisementDTO jobAdvertisementDTO) {
        RestTemplate restTemplate = new RestTemplate();
        String elasticUrl = "http://localhost:8081/api/elasticsearch/save-job-advertisement";
        ResponseEntity<JobAdvertisementDTO> response = restTemplate.postForEntity(elasticUrl, jobAdvertisementDTO, JobAdvertisementDTO.class);
        return response.getBody();
    }

    @Override
    public List<JobAdvertisementDTO> searchJobAdvByDate(String date) {
        RestTemplate restTemplate = new RestTemplate();
        String elasticUrl = "http://localhost:8081/api/elasticsearch/search-by-date?date=" + date;
        ResponseEntity<List<JobAdvertisementDTO>> response = restTemplate.exchange(elasticUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return response.getBody();
    }

    @Override
    public List<JobAdvertisementDTO> searchJobAdvByEmployerId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String elasticUrl = "http://localhost:8081/api/elasticsearch/jobs-by-employer?employerId=" + id;
        ResponseEntity<List<JobAdvertisementDTO>> response = restTemplate.exchange(elasticUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return response.getBody();
    }
}