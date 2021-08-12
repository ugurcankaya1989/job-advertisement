package com.kariyer.jobadvertisement.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Employer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String employerName;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private int jobAdvCount;
    @OneToMany(mappedBy = "employer" ,fetch = FetchType.LAZY)
    private List<JobAdvertisement> jobAdvertisementList;
}
