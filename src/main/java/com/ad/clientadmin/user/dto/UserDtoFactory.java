package com.ad.clientadmin.user.dto;

import com.ad.core.model.user.domain.User;
import org.springframework.stereotype.Component;

/**
 * Factory methods for creating DTOs.
 * 
 * @author RD
 */
@Component
public class UserDtoFactory {

    public UserDto createUser(User domain) {
        //throw user not found exception
        UserDto dto = new UserDto();
        dto.setId(domain.getId());
        dto.setFullname(domain.getFirstName() + " " + domain.getLastName());
        return dto;
    }

}
