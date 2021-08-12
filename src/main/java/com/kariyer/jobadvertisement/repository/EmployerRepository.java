package com.kariyer.jobadvertisement.repository;

import com.kariyer.jobadvertisement.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EmployerRepository extends JpaRepository<Employer,Long> {
    @Modifying
    @Query("update Employer e set e.jobAdvCount = e.jobAdvCount - 1  where e.id = :employer_id")
    void decreaseJobAdvertisementByEmployer(@Param("employer_id") Long employer_id);
    @Query("select e.jobAdvCount from Employer e where e.id = :employer_id")
    int getJobAdvCountByEmployer(@Param("employer_id") Long employer_id);
    Employer findEmployerByPhoneNumber(String phoneNumber);
}
