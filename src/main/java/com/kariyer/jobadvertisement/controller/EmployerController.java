package com.kariyer.jobadvertisement.controller;

import com.kariyer.jobadvertisement.dto.EmployerDTO;
import com.kariyer.jobadvertisement.exception.PhoneNumberRecordedBeforeException;
import com.kariyer.jobadvertisement.exception.RecordNotFoundException;
import com.kariyer.jobadvertisement.service.EmployerService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/employer")
public class EmployerController {
    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<EmployerDTO> saveNewEmployer(@RequestBody EmployerDTO employerDTO){
        if(employerService.isPhoneNumberAvailable(employerDTO.getPhoneNumber()))
            throw new PhoneNumberRecordedBeforeException("Phone number used before : " + employerDTO.getPhoneNumber());
        EmployerDTO savedEmployerDTO = employerService.saveNewEmployer(employerDTO);
        return ResponseEntity.ok(savedEmployerDTO);
    }

    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<EmployerDTO> getEmployerById(@PathVariable("id") long id){
        EmployerDTO employerDTO = employerService.getEmployerById(id);
        if(Objects.isNull(employerDTO))
            throw new RecordNotFoundException("Employer not found id : " + id);
        return ResponseEntity.ok(employerDTO);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<EmployerDTO>> getEmployerList(){
        List<EmployerDTO> employerDTOList = employerService.getAllEmployer();
        if(CollectionUtils.isEmpty(employerDTOList))
            throw new RecordNotFoundException("There is no Employer record found in DB");
        return ResponseEntity.ok(employerDTOList);
    }

}
