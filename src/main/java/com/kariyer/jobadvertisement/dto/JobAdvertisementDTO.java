package com.kariyer.jobadvertisement.dto;

import com.kariyer.jobadvertisement.constant.WorkingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementDTO {
    private long id;
    private String description;
    private LocalDateTime airtime;
    private int advQuality;
    private String fringeBenefits;
    private WorkingType workingType;
    private String salaryInfo;
    private long employerId;
}
