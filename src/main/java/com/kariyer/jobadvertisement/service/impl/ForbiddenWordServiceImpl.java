package com.kariyer.jobadvertisement.service.impl;

import com.kariyer.jobadvertisement.service.ForbiddenWordService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForbiddenWordServiceImpl implements ForbiddenWordService {
    @Override
    public Boolean isForbiddenWordExist(String description) {
        RestTemplate restTemplate = new RestTemplate();
        String elasticUrl = "http://localhost:8082/api/forbidden/is-forbidden-word-exist";
        ResponseEntity<Boolean> response = restTemplate.postForEntity(elasticUrl, description, Boolean.class);
        return response.getBody();
    }
}
