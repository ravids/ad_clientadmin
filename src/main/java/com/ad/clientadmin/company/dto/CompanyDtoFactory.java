package com.ad.clientadmin.company.dto;

import com.ad.core.model.company.domain.Company;
import org.springframework.stereotype.Component;

/**
 * Factory methods for creating DTOs.
 * 
 * @author RD
 */
@Component
public class CompanyDtoFactory {

    public CompanyDto createCompany(Company domain) {
        //throw user not found exception
        CompanyDto dto = new CompanyDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        return dto;
    }

}
