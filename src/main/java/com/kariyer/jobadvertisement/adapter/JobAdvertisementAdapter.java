package com.kariyer.jobadvertisement.adapter;

import com.kariyer.jobadvertisement.dto.JobAdvertisementDTO;
import com.kariyer.jobadvertisement.model.Employer;
import com.kariyer.jobadvertisement.model.JobAdvertisement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobAdvertisementAdapter {

    public JobAdvertisementDTO mapToJobAdvertisementDTO(JobAdvertisement jobAdvertisement){
        JobAdvertisementDTO jobAdvertisementDTO = new JobAdvertisementDTO();
        BeanUtils.copyProperties(jobAdvertisement,jobAdvertisementDTO);
        jobAdvertisementDTO.setEmployerId(jobAdvertisement.getEmployer().getId());
        return jobAdvertisementDTO;
    }

    public JobAdvertisement mapToJobAdvertisement(JobAdvertisementDTO jobAdvertisementDTO){
        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        BeanUtils.copyProperties(jobAdvertisementDTO,jobAdvertisement);
        jobAdvertisement.setEmployer(Employer.builder().id(jobAdvertisementDTO.getEmployerId()).build());
        return jobAdvertisement;
    }

    public List<JobAdvertisementDTO> mapToJobAdvertisementDTOList(List<JobAdvertisement> jobAdvertisementList){
        List<JobAdvertisementDTO> jobAdvertisementDTOList = new ArrayList<>();
        for (JobAdvertisement ja:jobAdvertisementList) {
            JobAdvertisementDTO jobAdvertisementDTO = new JobAdvertisementDTO();
            BeanUtils.copyProperties(ja,jobAdvertisementDTO);
            jobAdvertisementDTOList.add(jobAdvertisementDTO);
        }
        return jobAdvertisementDTOList;
    }
}
