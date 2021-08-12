package com.kariyer.jobadvertisement.util;

import com.kariyer.jobadvertisement.dto.JobAdvertisementDTO;
import com.kariyer.jobadvertisement.service.ForbiddenWordService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class QualityCalculator {

    private final ForbiddenWordService forbiddenWordService;

    public QualityCalculator(ForbiddenWordService forbiddenWordService) {
        this.forbiddenWordService = forbiddenWordService;
    }

    public Integer calculateQuality(JobAdvertisementDTO jobAdvertisementDTO) {
        int quality = 0;
        if(Objects.nonNull(jobAdvertisementDTO.getWorkingType()))
            quality++;
        if (Objects.nonNull(jobAdvertisementDTO.getSalaryInfo()))
            quality++;
        if (Objects.nonNull(jobAdvertisementDTO.getFringeBenefits()))
            quality++;
        if (!forbiddenWordService.isForbiddenWordExist(jobAdvertisementDTO.getDescription()))
            quality += 2;
        return quality;
    }

}
