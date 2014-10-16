package com.ad.clientadmin.user.dto;

import com.ad.core.user.domain.Person;
import com.ad.core.user.domain.User;
import org.springframework.stereotype.Component;

/**
 * Factory methods for creating DTOs.
 * 
 * @author RD
 */
@Component
public class UserDtoFactory {

	/**
	 * Converts a domain entity to a dto.
	 * @param domain
	 * @return
	 */
	public UserDto createPerson(Person domain) {
		UserDto dto = new UserDto();
		dto.setId(domain.getId());
		dto.setFullname(domain.getFirstName() + " " + domain.getLastName());
		return dto;
	}

    public UserDto createUser(User domain) {
        UserDto dto = new UserDto();
        dto.setId(domain.getId());
        dto.setFullname(domain.getFirstName() + " " + domain.getLastName());
        return dto;
    }

}
