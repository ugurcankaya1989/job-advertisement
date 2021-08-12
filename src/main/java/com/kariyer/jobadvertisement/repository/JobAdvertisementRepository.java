package com.kariyer.jobadvertisement.repository;

import com.kariyer.jobadvertisement.model.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement,Long> {
}
