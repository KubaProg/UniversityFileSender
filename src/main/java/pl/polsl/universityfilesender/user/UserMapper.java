package pl.polsl.universityfilesender.user;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.user.dto.UserDto;

@Component
public class UserMapper {


    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole().toString());
        return userDto;
    }



}
