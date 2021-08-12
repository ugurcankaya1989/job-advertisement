package com.kariyer.jobadvertisement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDTO {
    private long id;
    private String employerName;
    private String address;
    private String phoneNumber;
    private int jobAdvCount;
}
