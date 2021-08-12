package com.kariyer.jobadvertisement.adapter;

import com.kariyer.jobadvertisement.dto.EmployerDTO;
import com.kariyer.jobadvertisement.model.Employer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmployerAdapter {

    public EmployerDTO mapToEmployerDTO(Employer Employer){
        EmployerDTO EmployerDTO = new EmployerDTO();
        BeanUtils.copyProperties(Employer,EmployerDTO);
        return EmployerDTO;
    }

    public Employer mapToEmployer(EmployerDTO EmployerDTO){
        Employer Employer = new Employer();
        BeanUtils.copyProperties(EmployerDTO,Employer);
        return Employer;
    }

    public List<EmployerDTO> mapToEmployerDTOList(List<Employer> EmployerList){
        List<EmployerDTO> EmployerDTOList = new ArrayList<>();
        for (Employer e:EmployerList) {
            EmployerDTO EmployerDTO = new EmployerDTO();
            BeanUtils.copyProperties(e,EmployerDTO);
            EmployerDTOList.add(EmployerDTO);
        }
        return EmployerDTOList;
    }
}
