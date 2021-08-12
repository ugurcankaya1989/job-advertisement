package com.kariyer.jobadvertisement.controller;

import com.kariyer.jobadvertisement.dto.JobAdvertisementDTO;
import com.kariyer.jobadvertisement.exception.DontHaveTheRightToPostAnAdvException;
import com.kariyer.jobadvertisement.exception.NotSavedException;
import com.kariyer.jobadvertisement.exception.RecordNotFoundException;
import com.kariyer.jobadvertisement.service.ElasticsearchService;
import com.kariyer.jobadvertisement.service.ForbiddenWordService;
import com.kariyer.jobadvertisement.service.JobAdvertisementService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/job-advertisement")
public class JobAdvertisementController {
    private final JobAdvertisementService jobAdvertisementService;
    private final ElasticsearchService elasticsearchService;

    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService, ElasticsearchService elasticsearchService) {
        this.jobAdvertisementService = jobAdvertisementService;
        this.elasticsearchService = elasticsearchService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<JobAdvertisementDTO> saveNewEmployer(@RequestBody JobAdvertisementDTO jobAdvertisementDTO){
        if(!jobAdvertisementService.isEmployerHaveRightToPostAnAdv(jobAdvertisementDTO.getEmployerId()))
            throw new DontHaveTheRightToPostAnAdvException("Do not have the right to post an advertisement!");
        //Save data to DB
        JobAdvertisementDTO savedJobAdvertisementDTO = jobAdvertisementService.saveNewJobAdvertisement(jobAdvertisementDTO);
        //Push data to elastic index
        elasticsearchService.pushDataToElasticIndex(savedJobAdvertisementDTO);
        if(Objects.isNull(savedJobAdvertisementDTO))
            throw new NotSavedException("Not saved!");
        return ResponseEntity.ok(savedJobAdvertisementDTO);
    }

    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<JobAdvertisementDTO> getEmployerById(@PathVariable("id") long id){
        JobAdvertisementDTO jobAdvertisementDTO = jobAdvertisementService.getJobAdvertisementById(id);
        if(Objects.isNull(jobAdvertisementDTO))
            throw new RecordNotFoundException("Record Not Found with id : " + id);
        return ResponseEntity.ok(jobAdvertisementDTO);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<JobAdvertisementDTO>> getEmployerList(){
        List<JobAdvertisementDTO> jobAdvertisementDTOList = jobAdvertisementService.getAllJobAdvertisement();
        if(CollectionUtils.isEmpty(jobAdvertisementDTOList))
            throw new RecordNotFoundException("There is no record found!");
        return ResponseEntity.ok(jobAdvertisementDTOList);
    }

    @GetMapping("/search-by-date")
    public ResponseEntity<List<JobAdvertisementDTO>> searchJobAdvByDate(@RequestParam("date") String date){
        List<JobAdvertisementDTO> jobAdvertisementDTOList = elasticsearchService.searchJobAdvByDate(date);
        if(CollectionUtils.isEmpty(jobAdvertisementDTOList))
            throw new RecordNotFoundException("There is no record found in elastic index!");
    return ResponseEntity.ok(jobAdvertisementDTOList);
    }

    @GetMapping("/search-by-employer")
    public ResponseEntity<List<JobAdvertisementDTO>> searchJobAdvByDate(@RequestParam("id") Long id){
        List<JobAdvertisementDTO> jobAdvertisementDTOList = elasticsearchService.searchJobAdvByEmployerId(id);
        if(CollectionUtils.isEmpty(jobAdvertisementDTOList))
            throw new RecordNotFoundException("There is no record found in elastic index!");
        return ResponseEntity.ok(jobAdvertisementDTOList);
    }
}
