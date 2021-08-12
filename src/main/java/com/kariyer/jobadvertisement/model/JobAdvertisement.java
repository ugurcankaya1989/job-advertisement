package com.kariyer.jobadvertisement.model;

import com.kariyer.jobadvertisement.constant.WorkingType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_advertisement")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String description;
    @Column
    private LocalDateTime airtime;
    @Column
    private int advQuality;
    @Column
    private String fringeBenefits;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private WorkingType workingType;
    @Column
    private String salaryInfo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
